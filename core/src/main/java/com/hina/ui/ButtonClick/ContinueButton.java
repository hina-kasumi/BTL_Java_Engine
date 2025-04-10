package com.hina.ui.ButtonClick;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.hina.screens.InnerScreens.PauseManager;

public class ContinueButton extends ButtonAbstract {
    private final PauseManager pauseManager;

    public ContinueButton(PauseManager pauseManager, String src) {
        super(src);
        this.pauseManager = pauseManager;
    }

    public ContinueButton(PauseManager pauseManager, String normalSrc, String hoverSrc) {
        super(normalSrc, hoverSrc);
        this.pauseManager = pauseManager;
    }

    @Override
    public void addListener(InputEvent event, float x, float y) {
        this.pauseManager.setPaused(false);
    }
}
