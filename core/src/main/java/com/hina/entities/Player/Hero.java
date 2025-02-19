package com.hina.entities.Player;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hina.entities.AnimationState;
import com.hina.entities.Entity;

import static com.hina.constant.PlayerConst.*;
import static com.hina.constant.GameConst.*;
import static com.hina.GameManager.isGameStop;
import static com.hina.utils.ImportTextureUtil.newImportAnimation;

public abstract class Hero extends Entity {
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> movingAnimation;
    private Animation<TextureRegion> jumpAnimation;
    private Animation<TextureRegion> fallAnimation;
    private Animation<TextureRegion> attackAnimation;
    private Animation<TextureRegion> takeHitAnimation;
    private Animation<TextureRegion> deathAnimation;
    private Animation<TextureRegion> specialAnimation;
    private Animation<TextureRegion> rollAnimation;
    private Animation<TextureRegion> defendAnimation;
    private Animation<TextureRegion> airAttackAnimation;
    private PlayerHealthBar playerHealthBar;
    private boolean onGround;
    private boolean attacking;
    private float attackBoxWidth;
    private float attackBoxHeight;
    private int startAttackAt;
    private int endAttackAt;


    public Hero(World world, Vector2 bornPosition, float maxHealth, String heroSrc) {
        super(world, bornPosition.x, bornPosition.y, 0.5f, 1f, maxHealth, 1.5f);

        body.setGravityScale(5);
        body.setUserData(this);

        this.scale = PLAYER_SCALE;
        this.playerHealthBar = new PlayerHealthBar(this);

        createAnimation(heroSrc);
    }


    private void createAnimation(String heroSrc) {
        idleAnimation = newImportAnimation(heroSrc + HeroState.IDLE.getFileName());
        attackAnimation = newImportAnimation(heroSrc + HeroState.ATTACK.getFileName(), 0.05f);
        movingAnimation = newImportAnimation(heroSrc + HeroState.RUNNING.getFileName());
        jumpAnimation = newImportAnimation(heroSrc + HeroState.JUMP.getFileName());
        fallAnimation = newImportAnimation(heroSrc + HeroState.FALL.getFileName());
        takeHitAnimation = newImportAnimation(heroSrc + HeroState.TAKE_HIT.getFileName());
        specialAnimation = newImportAnimation(heroSrc + HeroState.SPECIAL_ATTACK.getFileName());
        rollAnimation = newImportAnimation(heroSrc + HeroState.ROLL.getFileName());
        defendAnimation = newImportAnimation(heroSrc + HeroState.DEFEND.getFileName());
        airAttackAnimation = newImportAnimation(heroSrc + HeroState.AIR_ATTACK.getFileName());
        deathAnimation = newImportAnimation(heroSrc + HeroState.DEATH.getFileName(), 0.15f);
    }


    @Override
    public void update(float delta) {
        deathUpdate();
        playerHealthBar.update();
        if (isDeath) {
            return;
        }

        final float speed = 10f;
        final float jumpStrength = Math.min(entityHeight, entityWidth) * 100;
        float movingSpeed = 0;
        boolean prevMoveRight = movingRight;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            movingSpeed -= speed;
            movingRight = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            movingSpeed += speed;
            movingRight = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.J)) {
            if (!attacking) {
                resetStateTime();
            }
            attacking = true;
        }

        if (attacking && !takingHit) {
            if (prevMoveRight != movingRight) {
                movingRight = prevMoveRight;
            }
            if (Math.abs(body.getLinearVelocity().y) < 0.1) {
                movingSpeed = 0;
            }

            int keyFrameIndex = attackAnimation.getKeyFrameIndex(stateTime);
            if (attackBox.isDestroyed() && keyFrameIndex == startAttackAt) {
                attackBox.createHitBox(body, attackBoxWidth, attackBoxHeight, ATTACK_DAMAGE, movingRight);
            }
            if (endAttackAt != 0 && keyFrameIndex == endAttackAt) {
                attackBox.destroyAttackSensor();
            }

            if (attackAnimation.isAnimationFinished(stateTime)) {
                attacking = false;
                attackBox.destroyAttackSensor();
            }
        }

        if (takingHit) {
            if (takeHitAnimation.isAnimationFinished(stateTime)) {
                resetStateTime();
                takingHit = false;
            }
            attacking = false;
            attackBox.destroyAttackSensor();
            animationPriority.add(AnimationState.TAKE_HIT);
            blockMoving();
        }

        body.setLinearVelocity(movingSpeed, body.getLinearVelocity().y);
        updateAnimation();

        if (Gdx.input.isKeyPressed(Input.Keys.K) && onGround) {
            body.applyLinearImpulse(new Vector2(0, jumpStrength), body.getWorldCenter(), true);
            onGround = false;
        }
    }

    void deathUpdate() {
        if (isDeath) {
            blockMoving();
            if (deathAnimation.isAnimationFinished(stateTime)) {
                isGameStop = true;
            }
            animationPriority.add(AnimationState.DEATH);
        }
    }

    public boolean isIdle() {
        return !isDeath && !attacking && onGround && !takingHit;
    }


    private void updateAnimation() {
        animationPriority.add(AnimationState.IDLE);
        if (attacking) {
            animationPriority.add(AnimationState.ATTACK);
            if (!onGround)
                animationPriority.add(AnimationState.AIR_ATTACK);
            return;
        }
        if (body.getLinearVelocity().x != 0) {
            animationPriority.add(AnimationState.RUN);
        }
        if (body.getLinearVelocity().y >= 0.1) {
            animationPriority.add(AnimationState.JUMP);
        } else if (body.getLinearVelocity().y <= -0.1) {
            animationPriority.add(AnimationState.FALL);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame;

        switch (animationPriority.get()) {
            case null -> {
                return;
            }
            case RUN -> currentFrame = movingAnimation.getKeyFrame(stateTime, true);
            case ATTACK -> currentFrame = attackAnimation.getKeyFrame(stateTime, false);
            case JUMP -> currentFrame = jumpAnimation.getKeyFrame(stateTime, true);
            case FALL -> currentFrame = fallAnimation.getKeyFrame(stateTime, true);
            case DEATH -> currentFrame = deathAnimation.getKeyFrame(stateTime, false);
            case TAKE_HIT -> currentFrame = takeHitAnimation.getKeyFrame(stateTime, false);
            case AIR_ATTACK -> currentFrame = airAttackAnimation.getKeyFrame(stateTime, false);
            case SPECIAL_ATTACK -> currentFrame = specialAnimation.getKeyFrame(stateTime, false);
            case ROLL -> currentFrame = rollAnimation.getKeyFrame(stateTime, false);
            case DEFEND -> currentFrame = defendAnimation.getKeyFrame(stateTime, false);
            default -> currentFrame = idleAnimation.getKeyFrame(stateTime, true);
        }

        flip(currentFrame);

        batch.draw(currentFrame,
            body.getPosition().x - scale * currentFrame.getRegionWidth() / 2 / PPM,
            body.getPosition().y - entityHeight - 0.001f,
            currentFrame.getRegionWidth() * scale / PPM,
            currentFrame.getRegionHeight() * scale / PPM
        );
    }

    public void renderPlayerHealthBar(SpriteBatch batch, Camera camera, FitViewport viewport) {
        playerHealthBar.render(batch, camera, viewport);
    }

    protected void setAttackAt(int start, int end) {
        if (start > end)
            return;
        this.startAttackAt = start;
        this.endAttackAt = end;
    }

    public void setAttackBoxSize(float width, float height) {
        this.attackBoxWidth = width;
        this.attackBoxHeight = height;
    }

    protected void setAttackAt(int start) {
        this.startAttackAt = start;
    }

    public void setPosition(Vector2 position) {
        body.setTransform(position, body.getAngle());
    }

    @Override
    public void dispose() {
        playerHealthBar.dispose();
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
}
