package com.hina.ButtonClick.LevelButton;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hina.ButtonClick.ButtonAbstract;

public class PrevLevelsButton extends ButtonAbstract {
    private final LevelButtonList levelButtonList;

    public PrevLevelsButton(String src, LevelButtonList levelButtonList) {
        super(src);
        this.levelButtonList = levelButtonList;
    }

    public PrevLevelsButton(String normalSrc, String hoverSrc, LevelButtonList levelButtonList) {
        super(normalSrc, hoverSrc);
        this.levelButtonList = levelButtonList;
    }

    @Override
    public void addListener() {
        imageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                levelButtonList.decreaseCurrentPage();
            }
        });
    }
}
