package com.hina.ButtonClick;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class BackButton extends ButtonAbstract {
    private final Game game;
    private final Screen prevScreen;

    public BackButton(String src,Game game, Screen prevScreen) {
        super(src);
        this.game = game;
        this.prevScreen = prevScreen;
    }

    public BackButton(String normalSrc, String hoverSrc,Game game, Screen prevScreen) {
        super(normalSrc, hoverSrc);
        this.game = game;
        this.prevScreen = prevScreen;
    }

    @Override
    public void addListener(InputEvent event, float x, float y) {
        game.setScreen(prevScreen);
    }
}
