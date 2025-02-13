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
    protected AnimationPriority animationPriority;
    protected boolean attacking;
    protected boolean takingHit;
    protected boolean isDeath;

    public Entity(World world, float x, float y, float entityWidth, float entityHeight, float maxHeath, float density) {
        this.world = world;
        this.entityWidth = entityWidth;
        this.entityHeight = entityHeight;
        this.maxHeath = maxHeath;
        this.curHeath = maxHeath;
        this.movingRight = true;
        this.stateTime = 0;
        this.animationPriority = new AnimationPriority();
        this.attacking = false;
        this.takingHit = false;
        this.isDeath = false;

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
        if (isDeath)
            return;
        if (curHeath > 0) {
            curHeath -= damage;
            takingHit = true;

            if (curHeath == 0) {
                isDeath = true;
                takingHit = false;
            }
            stateTime = 0;
        }
        attacking = false;
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
        return body == null;
    }
}
