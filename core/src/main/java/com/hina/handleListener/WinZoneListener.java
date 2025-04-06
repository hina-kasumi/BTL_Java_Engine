package com.hina.handleListener;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.hina.entities.Player.Hero;
import com.hina.screens.GameScreen.BossGameScreen;
import com.hina.screens.GameScreen.GameScreen;
import com.hina.screens.GameScreen.NormalGameScreen;

import static com.hina.constant.GameConst.WIN_ZONE_TAG;


public class WinZoneListener implements InvisibleZoneContact {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        var userDataA = fixtureA.getBody().getUserData();
        var userDataB = fixtureB.getBody().getUserData();

        if (userDataA instanceof Hero && WIN_ZONE_TAG.equals(userDataB) ||
            userDataB instanceof Hero && WIN_ZONE_TAG.equals(userDataA)) {
            Hero hero = (Hero) (userDataA instanceof Hero ? userDataA : userDataB);
            loadBossChallenge(hero.getGameScreen());
        }
    }

    private void loadBossChallenge(GameScreen gameScreen) {
        if (gameScreen instanceof NormalGameScreen normalGameScreen) {
            Vector2 spawnInBoss = normalGameScreen.getSpawnInBoss();
            gameScreen.getGame().setScreen(new BossGameScreen(gameScreen,
                new Vector2(spawnInBoss.x, spawnInBoss.y),
                "maps/map_01/map-tmx/map_boss_" +
                    gameScreen.getLevel() +
                    ".tmx"));
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {
        visibleContact(contact, WIN_ZONE_TAG);
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
