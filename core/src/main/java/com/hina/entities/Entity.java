package com.hina.entities;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Entity {
    protected final float scale = 5f;
    protected final Body body;
    protected final float entityWidth;
    protected final float entityHeight;

    public Entity(World world, float x, float y, float entityWidth, float entityHeight, float density) {
        this.entityWidth = entityWidth;
        this.entityHeight = entityHeight;

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

    public abstract void dispose();
}
