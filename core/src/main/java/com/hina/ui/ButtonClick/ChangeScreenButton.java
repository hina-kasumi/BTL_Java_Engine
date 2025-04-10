package com.hina.ui.ButtonClick;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class ChangeScreenButton extends ButtonAbstract {
    private final Game game;
    private final Screen newScreen;

    public ChangeScreenButton(String src, Game game, Screen newScreen) {
        super(src);
        this.game = game;
        this.newScreen = newScreen;
    }

    public ChangeScreenButton(String normalSrc, String hoverSrc, Game game, Screen newScreen) {
        super(normalSrc, hoverSrc);
        this.game = game;
        this.newScreen = newScreen;
    }

    @Override
    public void addListener(InputEvent event, float x, float y) {
        game.setScreen(newScreen);
    }
}
