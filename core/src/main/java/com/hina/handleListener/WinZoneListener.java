package com.hina.handleListener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;

import static com.hina.constant.GameConst.WIN_ZONE_TAG;


public class WinZoneListener implements InvisibleZoneContact {
    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {
        visibleContact(contact, WIN_ZONE_TAG);
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
