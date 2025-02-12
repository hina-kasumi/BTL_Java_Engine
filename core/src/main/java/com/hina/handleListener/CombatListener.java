package com.hina.handleListener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.hina.entities.Entity;
import com.hina.utils.AttackBox;

public class CombatListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        var userDataA = fixtureA.getBody().getUserData();
        var userDataB = fixtureB.getBody().getUserData();

        if ((userDataA instanceof AttackBox && userDataB instanceof Entity) ||
            (userDataB instanceof AttackBox && userDataA instanceof Entity)) {
            AttackBox attackBox = (AttackBox) (userDataA instanceof AttackBox ? userDataA : userDataB);
            Entity entity = (Entity) (userDataA instanceof Entity ? userDataA : userDataB);

            if (attackBox.getEntity() != entity.getBody()) {
                entity.takeDamage(attackBox.getDamage());
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
