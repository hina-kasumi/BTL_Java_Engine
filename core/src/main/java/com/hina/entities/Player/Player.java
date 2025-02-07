package com.hina.entities.Player;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.hina.entities.Entity;

public class Player extends Entity {
    private final Body body;
    private boolean onGround = false;


    public Player(World world) {
        BodyDef playerBodyDef = new BodyDef();
        playerBodyDef.type = BodyDef.BodyType.DynamicBody;
        playerBodyDef.position.set(10, 10);
        playerBodyDef.fixedRotation = true;

        body = world.createBody(playerBodyDef);
        body.setGravityScale(5);

        PolygonShape playerShape = new PolygonShape();
        playerShape.setAsBox(1, 2);

        FixtureDef playerFixtureDef = new FixtureDef();
        playerFixtureDef.shape = playerShape;
        playerFixtureDef.density = 1.5f;
        body.createFixture(playerFixtureDef);

        playerShape.dispose();
    }

    @Override
    public void update(float delta) {
        final float speed = 5f;
        final float jumpStrength = 100f;
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
        }
    }

    @Override
    public void draw() {
    }

    @Override
    public void dispose() {
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
        System.out.println(onGround);
    }
}
