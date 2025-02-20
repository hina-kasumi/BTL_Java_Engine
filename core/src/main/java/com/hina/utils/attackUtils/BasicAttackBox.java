package com.hina.utils.attackUtils;

import com.badlogic.gdx.physics.box2d.*;

public class BasicAttackBox extends AttackAbstract{
    public BasicAttackBox(Body entity) {
        super(entity);
    }

    public void createHitBox(Body entity, float x, float y, float width, float height, float damage) {
        this.entity = entity;
        this.damage = damage;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
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
}
