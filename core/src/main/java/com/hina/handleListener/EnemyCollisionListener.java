package com.hina.handleListener;

import com.badlogic.gdx.physics.box2d.*;

import static com.hina.constant.GameConst.*;

public class EnemyCollisionListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        var userDataA = fixtureA.getBody().getUserData();
        var userDataB = fixtureB.getBody().getUserData();

        if (check(fixtureA, fixtureB, ENEMY_TAG) && check(fixtureA, fixtureB, GROUND_TAG)) {
            Fixture enemy = (userDataA == ENEMY_TAG) ? fixtureA : fixtureB;
            Fixture ground = (enemy == userDataA) ? fixtureB : fixtureA;

            Body enemyBody = enemy.getBody();

        }

    }

    private boolean check(Fixture fixtureA, Fixture fixtureB, Object value) {
        var userDataA = fixtureA.getBody().getUserData();
        var userDataB = fixtureB.getBody().getUserData();

        return userDataA == value || userDataB == value;
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
