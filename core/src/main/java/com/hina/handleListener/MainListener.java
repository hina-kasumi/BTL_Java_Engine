package com.hina.handleListener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.hina.entities.Player.Player;

public class MainListener implements ContactListener {
    private final PlayerJumpListener playerJumpListener;
    private final PlayerContactEnemyListener playerContactEnemyListener;

    public MainListener(Player player) {
        playerJumpListener = new PlayerJumpListener(player);
        playerContactEnemyListener = new PlayerContactEnemyListener();
    }

    @Override
    public void beginContact(Contact contact) {
        playerJumpListener.beginContact(contact);
        playerContactEnemyListener.beginContact(contact);
    }

    @Override
    public void endContact(Contact contact) {
        playerJumpListener.endContact(contact);
        playerContactEnemyListener.endContact(contact);
    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {
        playerJumpListener.preSolve(contact, manifold);
        playerContactEnemyListener.preSolve(contact, manifold);
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {
        playerJumpListener.postSolve(contact, contactImpulse);
        playerContactEnemyListener.postSolve(contact, contactImpulse);
    }
}
