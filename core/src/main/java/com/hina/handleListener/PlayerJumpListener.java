package com.hina.handleListener;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.hina.entities.Player.Player;

public class PlayerJumpListener implements ContactListener {
    private Player player;

    public PlayerJumpListener(Player player) {
        this.player = player;
    }

    @Override
    public void beginContact(Contact contact) {
        // Kiểm tra nếu một trong hai fixture là nhân vật và một là mặt đất
        if (isPlayerOnGround(contact)) {
            player.setOnGround(true);
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    private boolean isPlayerOnGround(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Body bodyA = fixtureA.getBody();
        Body bodyB = fixtureB.getBody();

        // Lấy normal
        Vector2 normal = contact.getWorldManifold().getNormal();

        if ("player" == bodyA.getUserData() && Math.abs(normal.y) > 0.5)
            return true;
        return "player" == bodyB.getUserData() && Math.abs(normal.y) > 0.5;
    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
