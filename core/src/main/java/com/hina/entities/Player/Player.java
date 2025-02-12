package com.hina.entities.Player;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.hina.entities.Entity;
import com.hina.utils.AttackBox;

import static com.hina.constant.GameConst.*;

public class Player extends Entity {
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> movingAnimation;
    private Animation<TextureRegion> jumpAnimation;
    private Animation<TextureRegion> fallAnimation;
    private Animation<TextureRegion> attackAnimation;
    private PlayerState playerState;
    private boolean onGround = false;
    private boolean attacking;
    private final AttackBox attackBox;


    public Player(World world) {
        super(world, 0, 10, 0.5f, 1f, 100f, 1.5f);

        body.setGravityScale(5);
        body.setUserData(this);

        attackBox = new AttackBox(this.body);

        createAnimation();
    }


    private void createAnimation() {
        idleAnimation = importAnimation(PlayerState.IDLE);
        attackAnimation = importAnimation(PlayerState.ATTACK);
        movingAnimation = importAnimation(PlayerState.RUNNING);
        jumpAnimation = importAnimation(PlayerState.JUMP);
        fallAnimation = importAnimation(PlayerState.FALL);
    }

    private Animation<TextureRegion> importAnimation(PlayerState playerState) {
        Texture texture = new Texture(playerState.getFileName());
        TextureRegion[][] textureRegions = TextureRegion
            .split(texture, texture.getWidth() / playerState.getFrameNumber(), texture.getHeight());

        Array<TextureRegion> array = new Array<>();
        for (int i = 0; i < textureRegions[0].length; i++) {
            array.add(textureRegions[0][i]);
        }

        return new Animation<>(0.1f, array, Animation.PlayMode.NORMAL);
    }

    @Override
    public void update(float delta) {
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
            attacking = true;
        }

        if (attacking) {
            if (attackBox.isDestroyed()) {
                attackBox.createHitBox(2, 2, 10f, movingRight);
            }
            if (attackAnimation.isAnimationFinished(stateTime)) {
                stateTime = 0;
                attacking = false;
                attackBox.destroyAttackSensor();
            } else if (prevMoveRight != movingRight) {
                movingRight = prevMoveRight;
            }
            if (body.getLinearVelocity().y == 0) {
                movingSpeed = 0;
            }
        }

        body.setLinearVelocity(movingSpeed, body.getLinearVelocity().y);
        updateAnimation();

        if (Gdx.input.isKeyPressed(Input.Keys.K) && onGround) {
            body.applyLinearImpulse(new Vector2(0, jumpStrength), body.getWorldCenter(), true);
            onGround = false;
        }
    }


    private void updateAnimation() {
        if (attacking) {
            playerState = PlayerState.ATTACK;
            return;
        }
        playerState = PlayerState.IDLE;
        if (body.getLinearVelocity().x != 0) {
            playerState = PlayerState.RUNNING;
        }
        if (body.getLinearVelocity().y >= 0.1) {
            playerState = PlayerState.JUMP;
        } else if (body.getLinearVelocity().y <= -0.1) {
            playerState = PlayerState.FALL;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame;
        TextureRegion attackFrame = attackAnimation.getKeyFrame(stateTime, false);

        switch (playerState) {
            case RUNNING -> currentFrame = movingAnimation.getKeyFrame(stateTime, true);
            case ATTACK -> currentFrame = attackFrame;
            case JUMP -> currentFrame = jumpAnimation.getKeyFrame(stateTime, true);
            case FALL -> currentFrame = fallAnimation.getKeyFrame(stateTime, true);
            default -> currentFrame = idleAnimation.getKeyFrame(stateTime, true);
        }

        flip(currentFrame);

        batch.draw(currentFrame,
            body.getPosition().x - scale * currentFrame.getRegionWidth() / 2 / PPM,
            body.getPosition().y - scale * currentFrame.getRegionHeight() / 2 / PPM,
            currentFrame.getRegionWidth() * scale / PPM,
            currentFrame.getRegionHeight() * scale / PPM
        );
    }

    @Override
    public void dispose() {
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
}
