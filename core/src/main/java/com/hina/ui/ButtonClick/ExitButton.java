package com.hina.ui.ButtonClick;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class ExitButton extends ButtonAbstract{
    public ExitButton(String src) {
        super(src);
    }

    public ExitButton(String normalSrc, String hoverSrc) {
        super(normalSrc, hoverSrc);
    }

    @Override
    public void addListener(InputEvent event, float x, float y) {
        Gdx.app.exit(); // Thoát game
    }
}
