package com.hina.handleListener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.hina.entities.Entity;

import static com.hina.constant.GameConst.DEATH_ZONE_TAG;

public class DeathZoneListener implements InvisibleZoneContact {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        var userDataA = fixtureA.getBody().getUserData();
        var userDataB = fixtureB.getBody().getUserData();

        if (userDataA instanceof Entity && DEATH_ZONE_TAG.equals(userDataB) ||
            userDataB instanceof Entity && DEATH_ZONE_TAG.equals(userDataA)) {
        Entity entity = (Entity) ((userDataA instanceof Entity) ? userDataA : userDataB);
            deathZoneProcess(entity);
        }
    }

    private void deathZoneProcess(Entity entity) {
        entity.takeDamage(entity.getCurHealth());
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {
        visibleContact(contact, DEATH_ZONE_TAG);
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {
    }
}
