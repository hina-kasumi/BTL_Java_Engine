package com.hina.ui.ButtonClick;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.hina.screens.GameScreen.BossGameScreen;
import com.hina.screens.GameScreen.GameScreen;
import com.hina.screens.GameScreen.NormalGameScreen;
import com.hina.screens.ScreenAbstract;

public class ReplayButton extends ButtonAbstract {
    private final ScreenAbstract screenAbstract;

    public ReplayButton(ScreenAbstract screenAbstract, String src) {
        super(src);
        this.screenAbstract = screenAbstract;
    }

    public ReplayButton(ScreenAbstract screenAbstract, String normalSrc, String hoverSrc) {
        super(normalSrc, hoverSrc);
        this.screenAbstract = screenAbstract;
    }

    @Override
    public void addListener(InputEvent event, float x, float y) {
        if (screenAbstract instanceof BossGameScreen bossGameScreen &&
            bossGameScreen.getScreenAbstract() instanceof NormalGameScreen normalGameScreen) {
            Vector2 playerSpawnPosition = normalGameScreen.getPlayerSpawnPosition();
            String fileMapName = normalGameScreen.getFileMapName();
            Vector2 spawnInBoss = normalGameScreen.getSpawnInBoss();

            screenAbstract.getGame().setScreen(
                new NormalGameScreen(normalGameScreen,
                    playerSpawnPosition,
                    fileMapName
                ).setSpawnInBoss(spawnInBoss)
            );
        } else if (screenAbstract instanceof NormalGameScreen gameScreen) {
            screenAbstract.getGame().setScreen(
                new NormalGameScreen(gameScreen,
                    gameScreen.getPlayerSpawnPosition(),
                    gameScreen.getFileMapName()
                ).setSpawnInBoss(gameScreen.getSpawnInBoss())
            );
        } else if (screenAbstract instanceof GameScreen gameScreen) {
            screenAbstract.getGame().setScreen(
                new NormalGameScreen(gameScreen,
                    gameScreen.getPlayerSpawnPosition(),
                    gameScreen.getFileMapName()));
        }
    }
}
