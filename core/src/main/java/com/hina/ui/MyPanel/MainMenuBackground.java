package com.hina.ui.MyPanel;

import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainMenuBackground extends MyPanel {
    public MainMenuBackground(FitViewport viewport) {
        super("background/MainMenuBackground.jpg");
        setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
    }
}
