package com.hina.utils;

import com.badlogic.gdx.physics.box2d.*;
import static com.hina.utils.Bin.*;

public class AttackBox {
    private Body hitBox;
    private Body entity;
    private final World world;
    private float damage;

    public AttackBox(Body entity) {
        this.world = entity.getWorld();
    }

    public void createHitBox(Body entity, float width, float height, float damage, boolean moveRight) {
        this.entity = entity;
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
        hitBox.setUserData(this);

        attackShape.dispose();
    }

    public void destroyAttackSensor() {
        if (hitBox != null) {
            bodiesToDestroy.add(hitBox);
            hitBox = null;
        }
    }

    public boolean isDestroyed() {
        return hitBox == null;
    }

    public Body getEntity() {
        return entity;
    }

    public float getDamage() {
        return damage;
    }
}
