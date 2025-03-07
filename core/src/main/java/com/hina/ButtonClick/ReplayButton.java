package com.hina.ButtonClick;

import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class ReplayButton extends ButtonAbstract{
    public ReplayButton(String src) {
        super(src);
    }

    public ReplayButton(String normalSrc, String hoverSrc) {
        super(normalSrc, hoverSrc);
    }

    @Override
    public void addListener(InputEvent event, float x, float y) {

    }
}
