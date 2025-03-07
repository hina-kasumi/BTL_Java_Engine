package com.hina.ButtonClick;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.hina.screens.PauseManager;

public class PauseButton extends ButtonAbstract{
    private final PauseManager pauseManager;

    public PauseButton(PauseManager pauseManager, String src) {
        super(src);
        this.pauseManager = pauseManager;
    }

    public PauseButton(PauseManager pauseManager, String normalSrc, String hoverSrc) {
        super(normalSrc, hoverSrc);
        this.pauseManager = pauseManager;
    }

    @Override
    public void addListener(InputEvent event, float x, float y) {
        pauseManager.setPaused(true);
    }
}
