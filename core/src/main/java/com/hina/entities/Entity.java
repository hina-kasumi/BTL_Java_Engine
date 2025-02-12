package com.hina.entities;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.hina.utils.AttackBox;

public abstract class Entity {
    protected float scale = 5f;
    protected Body body;
    protected final float entityWidth;
    protected final float entityHeight;
    protected final float maxHeath;
    protected boolean movingRight;
    protected float stateTime;
    protected float curHeath;
    protected World world;
    protected final AttackBox attackBox;

    public Entity(World world, float x, float y, float entityWidth, float entityHeight, float maxHeath, float density) {
        this.world = world;
        this.entityWidth = entityWidth;
        this.entityHeight = entityHeight;
        this.maxHeath = maxHeath;
        this.curHeath = maxHeath;
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

        this.attackBox = new AttackBox(this.body);

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

    public void takeDamage(float damage) {
        if (damage > 0)
            curHeath -= damage;
        System.out.println(curHeath);
    }

    protected void death() {
        if (body != null) {
            world.destroyBody(body);
            attackBox.destroyAttackSensor();
            body = null;
        }
    }

    public boolean isDead() {
        return body == null || curHeath <= 0;
    }
}
