package com.hina.handleListener;

import com.badlogic.gdx.physics.box2d.*;


public class EnemyCollisionListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {

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
