package com.hina.handleListener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.hina.entities.Entity;
import com.hina.entities.Player.Hero;
import com.hina.entities.enemy.BasicEnemy.BasicEnemy;
import com.hina.utils.attackUtils.AttackAbstract;

import static com.hina.constant.CoinConst.UP_COIN;
import static com.hina.manager.CoinManager.*;

public class CombatListener implements ContactListener {
    private Entity prevEntity = null;

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        var userDataA = fixtureA.getBody().getUserData();
        var userDataB = fixtureB.getBody().getUserData();

        if ((userDataA instanceof AttackAbstract && userDataB instanceof Entity) ||
            (userDataB instanceof AttackAbstract && userDataA instanceof Entity)) {
            AttackAbstract attackAbstract = (AttackAbstract) (userDataA instanceof AttackAbstract ? userDataA : userDataB);
            Entity entity = (Entity) (userDataA instanceof Entity ? userDataA : userDataB);

            if (attackAbstract.getEntity() != entity.getBody() &&
                !(attackAbstract.getEntity().getUserData() instanceof Hero && entity instanceof Hero)) {
                entity.takeDamage(attackAbstract.getDamage());

                if (entity instanceof BasicEnemy) {
                    if (entity.isDeath() && prevEntity != entity) {
                        upCoin(UP_COIN);
                        System.out.println(getCoin());
                        prevEntity = entity;
                    }
                }
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
