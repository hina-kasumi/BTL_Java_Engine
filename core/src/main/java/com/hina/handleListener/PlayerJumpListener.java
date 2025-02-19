package com.hina.handleListener;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.hina.entities.Player.Hero;

import static com.hina.constant.GameConst.GROUND_TAG;

public class PlayerJumpListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        // Kiểm tra nếu một trong hai fixture là nhân vật và một là mặt đất
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        Body bodyA = fixtureA.getBody();
        Body bodyB = fixtureB.getBody();

        // Lấy normal
        Vector2 normal = contact.getWorldManifold().getNormal();
        var userDataA = bodyA.getUserData();
        var userDataB = bodyB.getUserData();

        if (((userDataA instanceof Hero && GROUND_TAG == userDataB) ||
            (userDataB instanceof Hero && GROUND_TAG == userDataA)) &&
            Math.abs(normal.y) > 0.5) {

            Hero hero = (Hero) (userDataA instanceof Hero ? userDataA : userDataB);
            hero.setOnGround(true);
        }
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
