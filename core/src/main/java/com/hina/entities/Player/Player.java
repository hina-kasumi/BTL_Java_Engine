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

public class Player extends Entity {
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> movingAnimation;
    private Animation<TextureRegion> jumpAnimation;
    private Animation<TextureRegion> fallAnimation;
    private Animation<TextureRegion> attackAnimation;
    private Animation<TextureRegion> takeHitAnimation;
    private Animation<TextureRegion> deathAnimation;
    private PlayerHealthBar playerHealthBar;
    private boolean onGround = false;
    private boolean attacking;
    private int startAttackAt;
    private int endAttackAt;


    public Player(World world) {
        super(world, 0, 10, 0.5f, 1f, PLAYER_MAX_HEALTH, 1.5f);

        body.setGravityScale(5);
        body.setUserData(this);

        this.scale = PLAYER_SCALE;

        this.startAttackAt = 4;
        this.endAttackAt = 5;
        this.playerHealthBar = new PlayerHealthBar(this);

        createAnimation();
    }


    private void createAnimation() {
        idleAnimation = newImportAnimation(PlayerState.IDLE.getFileName());
        attackAnimation = newImportAnimation(PlayerState.ATTACK.getFileName(), 0.05f);
        movingAnimation = newImportAnimation(PlayerState.RUNNING.getFileName());
        jumpAnimation = newImportAnimation(PlayerState.JUMP.getFileName());
        fallAnimation = newImportAnimation(PlayerState.FALL.getFileName());
        takeHitAnimation = newImportAnimation(PlayerState.TAKE_HIT.getFileName());
        deathAnimation = newImportAnimation(PlayerState.DEATH.getFileName(), 0.15f);
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
                attackBox.createHitBox(ATTACK_BOX_WIGHT, ATTACK_BOX_HEIGHT, ATTACK_DAMAGE, movingRight);
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


    private void updateAnimation() {
        animationPriority.add(AnimationState.IDLE);
        if (attacking) {
            animationPriority.add(AnimationState.ATTACK);
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

    @Override
    public void dispose() {
        playerHealthBar.dispose();
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
}
