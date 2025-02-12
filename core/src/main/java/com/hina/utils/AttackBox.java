package com.hina.utils;

import com.badlogic.gdx.physics.box2d.*;

public class AttackBox {
    private Body hitBox;
    private final Body entity;
    private final World world;
    private float damage;

    public AttackBox(Body entity) {
        this.entity = entity;
        this.world = entity.getWorld();
    }

    public void createHitBox(float width, float height, float damage, boolean moveRight) {
        this.damage = damage;

        float offsetX = width * (moveRight ? 1 : -1);

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(
            entity.getPosition().x + offsetX,
            entity.getPosition().y
        );
        bodyDef.type = BodyDef.BodyType.KinematicBody;

        hitBox = this.world.createBody(bodyDef);

        PolygonShape attackShape = new PolygonShape();
        attackShape.setAsBox(width, height); // Kích thước hitbox

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = attackShape;
        fixtureDef.isSensor = true;
        hitBox.createFixture(fixtureDef);
        hitBox.setUserData(this.entity);

        attackShape.dispose();
    }

    public void destroyAttackSensor() {
        if (hitBox != null) {
            world.destroyBody(hitBox);
            hitBox = null;
        }
    }

    public boolean isDestroyed() {
        return hitBox == null;
    }

    public Body getEntity() {
        return entity;
    }
}
