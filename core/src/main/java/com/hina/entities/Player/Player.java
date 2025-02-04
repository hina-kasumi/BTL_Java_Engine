package com.hina.entities.Player;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.hina.entities.Entity;

import java.util.ArrayList;
import java.util.List;

// nếu như thay ảnh nhân vật thì đổi cả chiều cao chiều rộng, offset, nguồn file, số frame
public class Player extends Entity {
    private final Vector2 velocity;
    private List<Animation<TextureRegion>> animation;
    private PlayerState playerState;
    private final float frameDuration = 0.05f;
    private boolean movingRight;
    private boolean isAttacking = false;
    private boolean isJump = false;

    public Player(float x, float y) {
        super(x, y, 19, 45, 89, 78, 2f);

        this.playerState = PlayerState.IDLE;
        this.velocity = new Vector2(0, 0);
        this.movingRight = true;
        initAnimation();
    }

    /*
        muốn thêm animation nào thì phải thêm vào tempTexture và PlayerState và animation
        thêm điều kiện để thư hiện animation đó
    */
    private void initAnimation() {
        List<TextureRegion[][]> tempTexture = new ArrayList<>();
        loadTempTextureRegion(tempTexture, new Texture(PlayerState.IDLE.getFileName()), PlayerState.IDLE.getFrameNumber());
        loadTempTextureRegion(tempTexture, new Texture(PlayerState.RUNNING.getFileName()), PlayerState.RUNNING.getFrameNumber());
        loadTempTextureRegion(tempTexture, new Texture(PlayerState.ATTACK.getFileName()), PlayerState.ATTACK.getFrameNumber());
        loadTempTextureRegion(tempTexture, new Texture(PlayerState.JUMP.getFileName()), PlayerState.JUMP.getFrameNumber());
        loadTempTextureRegion(tempTexture, new Texture(PlayerState.FALL.getFileName()), PlayerState.FALL.getFrameNumber());

        List<Array<TextureRegion>> frames = new ArrayList<>();

        for (int i = 0; i < tempTexture.size(); i++) {
            Array<TextureRegion> frame = new Array<>();
            for (var x : tempTexture.get(i)[0])
                frame.add(x);
            frames.add(frame);
        }

        animation = new ArrayList<>();
        animation.add(new Animation<>(frameDuration, frames.get(0), Animation.PlayMode.LOOP));
        animation.add(new Animation<>(frameDuration, frames.get(1), Animation.PlayMode.LOOP));
        animation.add(new Animation<>(frameDuration, frames.get(2), Animation.PlayMode.NORMAL));
        animation.add(new Animation<>(frameDuration, frames.get(3), Animation.PlayMode.LOOP));
        animation.add(new Animation<>(frameDuration, frames.get(4), Animation.PlayMode.LOOP));
    }

    private void loadTempTextureRegion(List<TextureRegion[][]> tempTexture, Texture texture, int frameNumber) {
        tempTexture.add(
            TextureRegion.split(texture,
                texture.getWidth() / frameNumber,
                texture.getHeight())
        );
    }

    @Override
    public void update(float delta) {
        stateTime += delta;

        jumpUpdate(); //xử lý nhảy
        horizontalMoveUpdate(); // xử lý di chuyển ngang
        attackUpdate(); // xử lý tấn công
        updateAnimation(); // xử lý cập nhật animation

        position.add(velocity.x * delta, 0);
        hitbox.setPosition(position.x, position.y); // Cập nhật vị trí hitbox
    }

    private void jumpUpdate (){
        final float jumpForce = 15f;
        if (Gdx.input.isKeyPressed(Input.Keys.K) && !isJump) {
            isJump = true;
            velocity.y = jumpForce;
        }

        if (isJump) {
            position.y += velocity.y;
            velocity.y += gravity;
            if (position.y < 0) {
                isJump = false;
                position.y = 0;
            }
        }
    }

    private void horizontalMoveUpdate() {
        float speed = 150f;
        velocity.x = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            velocity.x -= speed;
            movingRight = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            movingRight = true;
            velocity.x += speed;
        }
    }

    private void attackUpdate() {
        if (Gdx.input.isKeyPressed(Input.Keys.J)) {
            isAttacking = true;
            if (animation.get(2).isAnimationFinished(stateTime)) {
                stateTime = 0;
            }
        }

        if (isAttacking) {
            if (animation.get(2).isAnimationFinished(stateTime)) {
                isAttacking = false;
            }
            if (!isJump)
                velocity.x = 0;
        }
    }

    private void updateAnimation() {
        if (isAttacking) {
            playerState = PlayerState.ATTACK;
            return;
        }
        if (isJump) {
            if (velocity.y > 0) {
                playerState = PlayerState.JUMP;
            } else {
                playerState = PlayerState.FALL;
            }
            return;
        }
        if (velocity.x == 0)
            playerState = PlayerState.IDLE;
        else playerState = PlayerState.RUNNING;
    }

    @Override
    public void draw(SpriteBatch batch) {
        TextureRegion currentFrame;

        switch (playerState) {
            case ATTACK -> currentFrame = animation.get(2).getKeyFrame(stateTime, false);
            case RUNNING -> currentFrame = animation.get(1).getKeyFrame(stateTime, true);
            case JUMP -> currentFrame = animation.get(3).getKeyFrame(stateTime, true);
            case FALL -> currentFrame = animation.get(4).getKeyFrame(stateTime, true);
            default -> currentFrame = animation.get(0).getKeyFrame(stateTime, true);
        }


        if (!movingRight && !currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        } else if (movingRight && currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        }
        batch.draw(currentFrame,
            position.x - xOffset * scale,
            position.y - yOffset * scale,
            currentFrame.getRegionWidth() * scale,
            currentFrame.getRegionHeight() * scale);
    }

    @Override
    public void dispose() {
    }
}
