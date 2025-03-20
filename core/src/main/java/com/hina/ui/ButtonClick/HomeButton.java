package com.hina.ui.ButtonClick;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.hina.screens.MainMenuScreen;
import com.hina.screens.ScreenAbstract;

public class HomeButton extends ButtonAbstract {
    private ScreenAbstract screen;

    public HomeButton(ScreenAbstract screen, String src) {
        super(src);
        init(screen);
    }

    public HomeButton(ScreenAbstract screen, String normalSrc, String hoverSrc) {
        super(normalSrc, hoverSrc);
        init(screen);
    }

    private void init(ScreenAbstract screen) {
        this.screen = screen;
    }

    @Override
    public void addListener(InputEvent event, float x, float y) {
        screen.getGame().setScreen(new MainMenuScreen(screen));
    }
}
