package com.hina.handleListener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.hina.entities.Entity;

public interface InvisibleZoneContact extends ContactListener {
    default void visibleContact(Contact contact, String tag) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        var userDataA = fixtureA.getBody().getUserData();
        var userDataB = fixtureB.getBody().getUserData();

        Entity entity = (Entity) ((userDataA instanceof Entity) ? userDataA : userDataB);
        if (entity == userDataA && tag.equals(userDataB) ||
            entity == userDataB && tag.equals(userDataA)) {
            contact.setEnabled(false);
        }
    }
}
