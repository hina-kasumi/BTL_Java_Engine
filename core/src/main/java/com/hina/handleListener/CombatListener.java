package com.hina.handleListener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.hina.entities.Entity;
import com.hina.entities.Player.Hero;
import com.hina.utils.BasicAttackBox;

public class CombatListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        var userDataA = fixtureA.getBody().getUserData();
        var userDataB = fixtureB.getBody().getUserData();

        if ((userDataA instanceof BasicAttackBox && userDataB instanceof Entity) ||
            (userDataB instanceof BasicAttackBox && userDataA instanceof Entity)) {
            BasicAttackBox basicAttackBox = (BasicAttackBox) (userDataA instanceof BasicAttackBox ? userDataA : userDataB);
            Entity entity = (Entity) (userDataA instanceof Entity ? userDataA : userDataB);

            if (basicAttackBox.getEntity() != entity.getBody() &&
                !(basicAttackBox.getEntity().getUserData() instanceof Hero && entity instanceof Hero)) {
                entity.takeDamage(basicAttackBox.getDamage());
            }
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
