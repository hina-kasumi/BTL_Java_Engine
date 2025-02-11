package com.hina.handleListener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;

public interface ContactListener {
    void beginContact(Contact contact);

    void endContact(Contact contact);

    void preSolve(Contact contact, Manifold manifold);

    void postSolve(Contact contact, ContactImpulse contactImpulse);
}
