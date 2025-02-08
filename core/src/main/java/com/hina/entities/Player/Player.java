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

public class Player extends Entity {
    private final Body body;
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> movingAnimation;
    private Animation<TextureRegion> jumpAnimation;
    private Animation<TextureRegion> fallAnimation;
    private Animation<TextureRegion> attackAnimation;
    private float state = 0;
    private final float PPM = 100f;
    private final float scale = 2f;
    private final float playerWidth = 0.5f;
    private final float playerHeight = 1f;
    private boolean onGround = false;
    private boolean attacking;
    private PlayerState playerState;


    public Player(World world) {
        BodyDef playerBodyDef = new BodyDef();
        playerBodyDef.type = BodyDef.BodyType.DynamicBody;
        playerBodyDef.position.set(0, 10);
        playerBodyDef.fixedRotation = true;

        body = world.createBody(playerBodyDef);
        body.setGravityScale(5);
        body.setUserData("player");

        PolygonShape playerShape = new PolygonShape();
        playerShape.setAsBox(playerWidth, playerHeight);

        FixtureDef playerFixtureDef = new FixtureDef();
        playerFixtureDef.shape = playerShape;
        playerFixtureDef.density = Math.max(playerHeight, playerWidth) * 1.5f;

        createAnimation();
        body.createFixture(playerFixtureDef);

        playerShape.dispose();
    }

    private void createAnimation() {
        idleAnimation = importAnimation(PlayerState.IDLE);
        attackAnimation = importAnimation(PlayerState.ATTACK);
        movingAnimation = importAnimation(PlayerState.RUNNING);
        jumpAnimation = importAnimation(PlayerState.JUMP);
        fallAnimation = importAnimation(PlayerState.FALL);

        state = 0;
    }

    private Animation<TextureRegion> importAnimation(PlayerState playerState) {
        Texture texture = new Texture(playerState.getFileName());
        TextureRegion[][] textureRegions = TextureRegion
            .split(texture, texture.getWidth() / playerState.getFrameNumber(), texture.getHeight());

        Array<TextureRegion> array = new Array<>();
        for (int i = 0; i < textureRegions[0].length; i++) {
            array.add(textureRegions[0][i]);
        }

        return new Animation<>(0.1f, array, Animation.PlayMode.LOOP);
    }

    @Override
    public void update(float delta) {
        final float speed = 10f;
        final float jumpStrength = Math.min(playerHeight, playerWidth) * 100;
        float movingSpeed = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            movingSpeed += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            movingSpeed -= speed;
        }

        body.setLinearVelocity(movingSpeed, body.getLinearVelocity().y);

        if (Gdx.input.isKeyPressed(Input.Keys.K) && onGround) {
            body.applyLinearImpulse(new Vector2(0, jumpStrength), body.getWorldCenter(), true);
            onGround = false;
        }
    }


    public void draw(SpriteBatch batch) {
        state += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = idleAnimation.getKeyFrame(state, true);


        Vector2 playerPos = body.getPosition();

        System.out.println((playerPos.x - 0.5f) + " " + (playerPos.y - 0.5f));
        batch.draw(currentFrame,
            (float) Gdx.graphics.getWidth() / 2 - scale * currentFrame.getRegionWidth() / 2,
            (float) Gdx.graphics.getHeight() / 2 - scale * currentFrame.getRegionHeight() / 2,
            currentFrame.getRegionWidth() * scale,
            currentFrame.getRegionHeight() * scale
        );
    }

    @Override
    public void dispose() {
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }
}
