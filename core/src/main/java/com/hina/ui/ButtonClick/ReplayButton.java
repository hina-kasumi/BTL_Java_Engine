package com.hina.ui.ButtonClick;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
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
        if (screenAbstract instanceof GameScreen gameScreen) {
            screenAbstract.getGame().setScreen(
                new NormalGameScreen(screenAbstract,
                    gameScreen.getPlayerSpawnPosition(),
                    gameScreen.getFileMapName()));
        }
    }
}
