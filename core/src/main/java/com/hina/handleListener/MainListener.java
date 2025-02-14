package com.hina.handleListener;


import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;

import java.util.ArrayList;
import java.util.List;

public class MainListener implements com.badlogic.gdx.physics.box2d.ContactListener {
    private final List<ContactListener> contactListeners;

    public MainListener() {
        contactListeners = new ArrayList<>();
        contactListeners.add(new PlayerJumpListener());
        contactListeners.add(new PlayerContactEnemyListener());
        contactListeners.add(new CombatListener());
        contactListeners.add(new EnemyCollisionListener());
    }

    @Override
    public void beginContact(Contact contact) {
        contactListeners.forEach((contactListener ->
            contactListener.beginContact(contact)));
    }

    @Override
    public void endContact(Contact contact) {
        contactListeners.forEach((contactListener ->
            contactListener.endContact(contact)));
    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {
        contactListeners.forEach((contactListener ->
            contactListener.preSolve(contact, manifold)));
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {
        contactListeners.forEach((contactListener ->
            contactListener.postSolve(contact, contactImpulse)));
    }
}
