package com.hina.utils.attackUtils;

import com.badlogic.gdx.physics.box2d.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MultiHitAttack extends AttackAbstract {
    private List<Integer> turn;
    private float prevFrame;

    public MultiHitAttack(Body entity) {
        super(entity);
    }

    private void createHitBox(Body entity, float x, float y, float width, float height, float damage) {
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

    public void attack(Body entity, float x, float y, float width, float height, float damage, int currentTurn) {
        if (turn.contains(currentTurn) && this.prevFrame != currentTurn) {
            createHitBox(entity, x, y, width, height, damage);
            destroyAttackSensor();
        }
        this.prevFrame = currentTurn;
    }

    public void setTurn(int[] turn) {
        this.turn = Arrays.stream(turn).boxed().collect(Collectors.toList());
    }
}
