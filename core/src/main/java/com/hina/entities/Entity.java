package com.hina.entities;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Entity {
    protected float scale = 5f;
    protected final Body body;
    protected final float entityWidth;
    protected final float entityHeight;
    protected final float maxHeath;
    protected boolean movingRight;
    protected float stateTime;

    public Entity(World world, float x, float y, float entityWidth, float entityHeight, float maxHeath, float density) {
        this.entityWidth = entityWidth;
        this.entityHeight = entityHeight;
        this.maxHeath = maxHeath;
        this.movingRight = true;
        this.stateTime = 0;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        bodyDef.fixedRotation = true;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(entityWidth, entityHeight);

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;
        fixtureDef.friction = 0f;
        body.createFixture(fixtureDef);

        shape.dispose();
    }

    public abstract void update(float delta);

    public abstract void draw(SpriteBatch batch);

    protected void flip(TextureRegion currentFrame) {
        if (!movingRight && !currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        } else if (movingRight && currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        }
    }

    public abstract void dispose();

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public Body getBody() {
        return body;
    }
}
