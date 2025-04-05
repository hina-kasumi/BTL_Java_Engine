package com.hina.screens.GameScreen;

import com.badlogic.gdx.math.Vector2;
import com.hina.screens.ScreenAbstract;

public class NormalGameScreen extends GameScreen {
    private final Vector2 spawnInBoss;

    public NormalGameScreen(ScreenAbstract screenAbstract, Vector2 playerSpawnPosition, String fileMapName) {
        super(screenAbstract, playerSpawnPosition, fileMapName);
        spawnInBoss = new Vector2(1.44f, 1.44f);
    }

    public NormalGameScreen setSpawnInBoss(float x, float y) {
        spawnInBoss.set(x, y);
        return this;
    }

    public Vector2 getSpawnInBoss() {
        return spawnInBoss;
    }
}
