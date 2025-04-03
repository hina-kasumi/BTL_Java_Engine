package com.hina.handleListener;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.hina.entities.enemy.BasicEnemy.BasicEnemy;

import static com.hina.constant.GameConst.GROUND_TAG;


public class EnemyCollisionListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        var userDataA = fixtureA.getBody().getUserData();
        var userDataB = fixtureB.getBody().getUserData();

        Vector2 normal = contact.getWorldManifold().getNormal();

        if (userDataA instanceof BasicEnemy && GROUND_TAG.equals(userDataB) ||
            userDataB instanceof BasicEnemy && GROUND_TAG.equals(userDataA)) {
//            if (Math.abs(normal.x) > 0.5f) {
                BasicEnemy entity = (BasicEnemy) ((userDataA instanceof BasicEnemy) ? userDataA : userDataB);
                entity.setMovingRight(!entity.isMovingRight());
//            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
