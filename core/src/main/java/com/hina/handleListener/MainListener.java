package com.hina.handleListener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.hina.entities.Player.Player;

public class MainListener implements ContactListener {
    private final PlayerJumpListener playerJumpListener;

    public MainListener(Player player) {
        playerJumpListener = new PlayerJumpListener(player);
    }

    @Override
    public void beginContact(Contact contact) {
        playerJumpListener.beginContact(contact);
    }

    @Override
    public void endContact(Contact contact) {
        playerJumpListener.endContact(contact);
    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
