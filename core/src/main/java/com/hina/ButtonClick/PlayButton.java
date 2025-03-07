package com.hina.ButtonClick;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.hina.screens.LevelSelectionScreen;
import com.hina.screens.ScreenAbstract;

public class PlayButton extends ButtonAbstract {
    private final ScreenAbstract screenAbstract;

    public PlayButton(ScreenAbstract screenAbstract, String src) {
        super(src);
        this.screenAbstract = screenAbstract;
    }

    public PlayButton(ScreenAbstract screenAbstract, String normal, String hover) {
        super(normal, hover);
        this.screenAbstract = screenAbstract;
    }

    @Override
    public void addListener(InputEvent event, float x, float y) {
        screenAbstract.getGame().setScreen(
            new LevelSelectionScreen(screenAbstract, screenAbstract.getGame().getScreen()));
    }
}
