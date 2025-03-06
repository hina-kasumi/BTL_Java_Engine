package com.hina.ButtonClick.LevelButton;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hina.ButtonClick.ButtonAbstract;

public class NextLevelsButton extends ButtonAbstract {
    private final LevelButtonList levelButtonList;

    public NextLevelsButton(String src, LevelButtonList levelButtonList) {
        super(src);
        this.levelButtonList = levelButtonList;
    }

    public NextLevelsButton(String normalSrc, String hoverSrc, LevelButtonList levelButtonList) {
        super(normalSrc, hoverSrc);
        this.levelButtonList = levelButtonList;
    }

    @Override
    public void addListener(InputEvent event, float x, float y) {
        levelButtonList.increaseCurrentPage();
    }
}
