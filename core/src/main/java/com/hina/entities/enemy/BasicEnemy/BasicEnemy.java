package com.hina.entities.enemy.BasicEnemy;

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

import static com.hina.constant.BasicMonsterConst.*;
import static com.hina.constant.GameConst.PPM;

public abstract class BasicEnemy extends Entity {
    protected final HeroManager heroManager;
    protected final Vector2 bornPosition;
    protected Animation<TextureRegion> idleAnimation;
    protected Animation<TextureRegion> attackAnimation;
    protected Animation<TextureRegion> runAnimation;
    protected Animation<TextureRegion> takeHitAnimation;
    protected Animation<TextureRegion> deathAnimation;
    protected int startAttackAt;
    protected int endAttackAt;
    protected HealthBar healthBar;
    private float attackAreaWidth;
    private TextureRegion prevFrame;

    public BasicEnemy(World world, HeroManager heroManager, float x, float y, float maxHeath) {
        super(world, x, y, BASIC_ENEMY_WIDTH, BASIC_ENEMY_HEIGHT, maxHeath, BASIC_ENEMY_DENSITY);

        this.heroManager = heroManager;
        this.bornPosition = new Vector2(x, y);
        this.startAttackAt = 0;
        this.endAttackAt = 0;
        this.healthBar = new HealthBar(this);

        body.setGravityScale(5);
        body.setUserData(this);
    }

    protected void setAttackAreaWidth(float attackAreaWidth) {
        this.attackAreaWidth = attackAreaWidth;
    }

    @Override
    public void update(float delta) {
        deathUpdate();
        if (isDeath) {
            return;
        }

        healthBar.update();
        float distantToPlayer = heroManager.getPosition().x - body.getPosition().x;
        float bornToPlayer = heroManager.getPosition().x - bornPosition.x;
        boolean ableAttackPlayer = heroManager.getPosition().dst(body.getPosition()) <= 5;
        boolean prevMoveRight = movingRight;

        runUpdate(bornToPlayer, distantToPlayer, ableAttackPlayer);
        attackUpdate(prevMoveRight, distantToPlayer, ableAttackPlayer);
        takeHitUpdate(prevMoveRight);
    }

    protected void runUpdate(float bornToPlayer, float distantToPlayer, boolean ableAttackPlayer) {
        final float activeArea = 5f;
        final float speed = 3f;

        float dst = body.getPosition().x - bornPosition.x;

        if (Math.abs(bornToPlayer) <= activeArea && ableAttackPlayer) {
            if ((distantToPlayer < 0 && movingRight) || (distantToPlayer >= 0 && !movingRight)) {
                movingRight = !movingRight;
            }
        }
        if ((dst < -activeArea && !movingRight) || (dst > activeArea && movingRight)) {
            movingRight = !movingRight;
        }

        animationPriority.add(AnimationState.RUN);
        setMovement(speed * ((movingRight) ? 1 : -1));
    }

    protected void attackUpdate(boolean prevMoveRight, float distantToPlayer, boolean ableAttackPlayer) {
        if (Math.abs(distantToPlayer) <= attackAreaWidth && ableAttackPlayer && !takingHit) {
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
                    body.getPosition().x + BASIC_MONSTER_ATTACK_BOX_WIDTH * (movingRight ? 1 : -1),
                    body.getPosition().y,
                    BASIC_MONSTER_ATTACK_BOX_WIDTH,
                    BASIC_MONSTER_ATTACK_BOX_HEIGHT,
                    BASIC_MONSTER_DAMAGE);
            }
            if (attackAnimation.isAnimationFinished(stateTime)) {
                attacking = false;
                basicAttackBox.destroyAttackSensor();
            }
            blockMoving();
            animationPriority.add(AnimationState.ATTACK);
        }
    }

    protected void takeHitUpdate(boolean prevMoveRight) {
        if (takingHit) {
            if (takeHitAnimation.isAnimationFinished(stateTime)) {
                stateTime = 0;
                takingHit = false;
            } else if (prevMoveRight != movingRight) {
                movingRight = prevMoveRight;
            }
            attacking = false;
            basicAttackBox.destroyAttackSensor();
            animationPriority.add(AnimationState.TAKE_HIT);
            blockMoving();
        }
    }

    protected void deathUpdate() {
        if (isDeath) {
            if (!isBodyNull())
                blockMoving();
            if (deathAnimation.isAnimationFinished(stateTime)) {
                death();
            }
            animationPriority.add(AnimationState.DEATH);
        }
    }

    private void setMovement(float movingSpeed) {
        body.setLinearVelocity(movingSpeed, body.getLinearVelocity().y);
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

        flip(currentFrame);

        batch.draw(currentFrame,
            body.getPosition().x - scale * currentFrame.getRegionWidth() / 2 / PPM,
            body.getPosition().y - scale * currentFrame.getRegionHeight() / 2 / PPM,
            currentFrame.getRegionWidth() * scale / PPM,
            currentFrame.getRegionHeight() * scale / PPM
        );
    }

    public void renderHealthBar(ShapeRenderer shapeRenderer) {
        if (!isDeath)
            healthBar.render(shapeRenderer);
    }


    // những cái này nên sử dụng sau khi tạo animation
    protected void setAttackTime(int startAttackAt, int endAttackAt) {
        if (startAttackAt > endAttackAt)
            return;
        this.startAttackAt = startAttackAt - 1;
        this.endAttackAt = endAttackAt - 1;
    }

    protected void setAttackTime(int startAttackAt) {
        this.startAttackAt = startAttackAt - 1;
    }
}
