package com.hina.entities.enemy.BossEnemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.hina.entities.AnimationState;
import com.hina.entities.Entity;
import com.hina.manager.HeroManager;
import com.hina.utils.HealthBar;

import static com.hina.constant.BossMonsterConst.*;
import static com.hina.constant.GameConst.PPM;

public abstract class BossEnemy extends Entity {
    protected final HeroManager heroManager;
    protected Animation<TextureRegion> idleAnimation;
    protected Animation<TextureRegion> attackAnimation;
    protected Animation<TextureRegion> runAnimation;
    protected Animation<TextureRegion> takeHitAnimation;
    protected Animation<TextureRegion> deathAnimation;
    protected int startAttackAt;
    protected int endAttackAt;
    protected HealthBar healthBar;
    protected final Vector2 bornPosition;
    private float basicAttackDamage;
    private float basicAttackBoxWidth;
    private float basicAttackBoxHeight;
    private float attackArea;
    private TextureRegion prevFrame;
    private float excessPixels = 0;

    public BossEnemy(World world, HeroManager heroManager, float x, float y, float width, float height, float maxHealth) {
        super(world, x, y, width, height, maxHealth, BOSS_DENSITY);

        this.heroManager = heroManager;
        body.setGravityScale(5);
        body.setUserData(this);

        this.bornPosition = new Vector2(x, y);
        this.healthBar = new HealthBar(this);
        this.healthBar.setGap(100f);
    }


    @Override
    public void update(float delta) {
        deathUpdate();
        if (isDeath)
            return;

        healthBar.update();
        boolean prevMoveRight = movingRight;
        float distantToPlayer = heroManager.getPosition().x - body.getPosition().x;
        boolean ableAttackPlayer = heroManager.getPosition().dst(body.getPosition()) <= 5;
        animationPriority.add(AnimationState.IDLE);

        movingRight = distantToPlayer >= 0;

        runUpdate();
        attackUpdate(prevMoveRight, distantToPlayer, ableAttackPlayer);
    }

    private void runUpdate() {
        final float speed = 3f;

        animationPriority.add(AnimationState.RUN);
        body.setLinearVelocity(speed * ((movingRight) ? 1 : -1), body.getLinearVelocity().y);
    }

    private void deathUpdate() {
        if (isDeath) {
            if (!isBodyNull())
                blockMoving();
            if (deathAnimation.isAnimationFinished(stateTime)) {
                death();
            }
            animationPriority.add(AnimationState.DEATH);
        }
    }

    public void setExcessPixels(float excessPixels) {
        this.excessPixels = excessPixels;
    }

    protected void setAttackArea(float attackArea) {
        this.attackArea = attackArea;
    }

    protected void setBasicAttackDamage(float basicAttackDamage) {
        this.basicAttackDamage = basicAttackDamage;
    }

    protected void setBasicAttackBoxSize(float width, float height) {
        this.basicAttackBoxWidth = width;
        this.basicAttackBoxHeight = height;
    }

    protected void attackUpdate(boolean prevMoveRight, float distantToPlayer, boolean ableAttackPlayer) {
        if (Math.abs(distantToPlayer) <= attackArea && ableAttackPlayer) {
            if (!attacking)
                stateTime = 0;
            attacking = true;
        }
        if (attacking) {
            if (prevMoveRight != movingRight && !attackAnimation.isAnimationFinished(stateTime)) {
                movingRight = prevMoveRight;
            }

            int keyFrameIndex = attackAnimation.getKeyFrameIndex(stateTime);
            if (endAttackAt != 0 && keyFrameIndex == endAttackAt) {
                basicAttackBox.destroyAttackSensor();
            }
            if (basicAttackBox.isDestroyed() && keyFrameIndex == startAttackAt) {
                basicAttackBox.createHitBox(body,
                    body.getPosition().x + basicAttackBoxWidth * (movingRight ? 1 : -1),
                    body.getPosition().y,
                    basicAttackBoxWidth,
                    basicAttackBoxHeight,
                    basicAttackDamage);
            }
            if (attackAnimation.isAnimationFinished(stateTime)) {
                attacking = false;
                basicAttackBox.destroyAttackSensor();
            }
            blockMoving();
            animationPriority.add(AnimationState.ATTACK);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (isBodyNull())
            return;
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame;

        switch (animationPriority.get()) {
            case null -> {
                currentFrame = prevFrame;
                blockMoving();
            }
            case RUN -> currentFrame = runAnimation.getKeyFrame(stateTime, true);
            case ATTACK -> currentFrame = attackAnimation.getKeyFrame(stateTime, false);
            case TAKE_HIT -> currentFrame = takeHitAnimation.getKeyFrame(stateTime, false);
            case DEATH -> currentFrame = deathAnimation.getKeyFrame(stateTime, false);
            default -> currentFrame = idleAnimation.getKeyFrame(stateTime, true);
        }
        prevFrame = currentFrame;

        flip(currentFrame, false);

        batch.draw(currentFrame,
            body.getPosition().x - scale * currentFrame.getRegionWidth() / 2 / PPM,
            body.getPosition().y - entityHeight - excessPixels / PPM * scale,
            currentFrame.getRegionWidth() * scale / PPM,
            currentFrame.getRegionHeight() * scale / PPM
        );
    }

    protected void setAttackTime(int startAttackAt, int endAttackAt) {
        if (startAttackAt > endAttackAt)
            return;
        this.startAttackAt = startAttackAt - 1;
        this.endAttackAt = endAttackAt - 1;
    }

    protected void setAttackTime(int startAttackAt) {
        this.startAttackAt = startAttackAt - 1;
    }

    @Override
    public void takeDamage(float damage) {
        if (isDeath)
            return;
        if (immortal)
            return;
        if (curHealth > 0) {
            curHealth -= damage;
            takingHit = true;

            if (curHealth <= 0) {
                resetStateTime();
                isDeath = true;
                takingHit = false;
            }
        }
    }

    public void renderHealthBar(ShapeRenderer shapeRenderer) {
        if (!isDeath)
            healthBar.render(shapeRenderer);
    }

    @Override
    public void dispose() {
    }

}
