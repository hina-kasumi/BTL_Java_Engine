package com.hina.handleListener;


import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import static com.hina.constant.GameConst.ENEMY_TAG;
import static com.hina.constant.GameConst.PLAYER_TAG;

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

        var userDataA = fixtureA.getBody().getUserData();
        var userDataB = fixtureB.getBody().getUserData();

        if ((userDataA == PLAYER_TAG && userDataB == ENEMY_TAG) ||
            (userDataB == PLAYER_TAG && userDataA == ENEMY_TAG)) {
            contact.setEnabled(false); // Tắt va chạm giữa player và enemy
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
