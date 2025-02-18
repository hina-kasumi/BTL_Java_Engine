package com.hina.entities;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.hina.utils.AnimationPriority;
import com.hina.utils.AttackBox;

import static com.hina.utils.Bin.bodiesToDestroy;

public abstract class Entity {
    protected World world;
    protected Body body;
    protected final AttackBox attackBox;
    protected float scale;
    protected final float entityWidth;
    protected final float entityHeight;
    protected final float maxHeath;
    protected boolean movingRight;
    protected boolean attacking;
    protected boolean takingHit;
    protected boolean isDeath;
    protected AnimationPriority animationPriority;
    protected float stateTime;
    protected float curHealth;

    public Entity(World world, float x, float y, float entityWidth, float entityHeight, float maxHeath, float density) {
        this.world = world;
        this.entityWidth = entityWidth;
        this.entityHeight = entityHeight;
        this.maxHeath = maxHeath;
        this.curHealth = maxHeath;
        this.movingRight = true;
        this.stateTime = 0;
        this.animationPriority = new AnimationPriority();
        this.attacking = false;
        this.takingHit = false;
        this.isDeath = false;
        this.scale = 5f;

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

    protected Animation<TextureRegion> importAnimation(String fileName, float frameDuration) {
        Texture texture = new Texture(fileName);

        TextureRegion[][] textureRegions = TextureRegion
            .split(texture, texture.getHeight(), texture.getHeight());

        Array<TextureRegion> array = new Array<>();
        for (int i = 0; i < textureRegions[0].length; i++) {
            array.add(textureRegions[0][i]);
        }

        return new Animation<>(frameDuration, array, Animation.PlayMode.NORMAL);
    }

    protected Animation<TextureRegion> importAnimation(String fileName) {
        return importAnimation(fileName, 0.1f);
    }

    protected void flip(TextureRegion currentFrame) {
        if (!movingRight && !currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        } else if (movingRight && currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        }
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public Body getBody() {
        return body;
    }

    public void takeDamage(float damage) {
        if (isDeath)
            return;
        if (curHealth > 0) {
            curHealth -= damage;
            takingHit = true;

            if (curHealth == 0) {
                isDeath = true;
                takingHit = false;
            }
            resetStateTime();
        }
        attackBox.destroyAttackSensor();
        attacking = false;
    }

    protected void death() {
        if (body != null) {
            bodiesToDestroy.add(body);
            attackBox.destroyAttackSensor();
            body = null;
        }
    }

    public float getCurHealth() {
        return curHealth;
    }

    public float getMaxHeath() {
        return maxHeath;
    }

    public float getEntityWidth(){
        return entityWidth;
    }

    public float getEntityHeight() {
        return entityHeight;
    }

    public boolean isBodyNull() {
        return body == null;
    }

    protected void resetStateTime() {
        stateTime = 0;
    }

    protected void blockMoving() {
        body.setLinearVelocity(0, body.getLinearVelocity().y);
    }

    public abstract void update(float delta);

    public abstract void draw(SpriteBatch batch);

    public abstract void dispose();
}
