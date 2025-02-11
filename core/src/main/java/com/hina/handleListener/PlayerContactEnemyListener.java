package com.hina.handleListener;


import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class PlayerContactEnemyListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if ((fixtureA.getBody().getUserData() == "player" && fixtureB.getBody().getUserData() == "enemy") ||
            (fixtureB.getBody().getUserData() == "player" && fixtureA.getBody().getUserData() == "enemy")) {
            contact.setEnabled(false); // Tắt va chạm giữa player và enemy
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
