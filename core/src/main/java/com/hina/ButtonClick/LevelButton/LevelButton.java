package com.hina.ButtonClick.LevelButton;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hina.ButtonClick.ButtonAbstract;

import static com.hina.constant.LevelSelectionScreenConst.*;

public class LevelButton extends ButtonAbstract {
    private final Game game;
    private final Screen screen;
    private int level;

    public LevelButton(Game game, Screen screen) {
        super("GUI/png/Level/Button/Dummy.png");
        this.game = game;
        this.screen = screen;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void draw(BitmapFont font, SpriteBatch batch) {
        float x = imageButton.getX();
        float y = imageButton.getY();
        int mod = level % (LEVEL_SELECTION_MAX_ROW * LEVEL_SELECTION_MAX_COL);
        if (mod >= 1 && mod <= LEVEL_SELECTION_MAX_COL) {
            font.draw(batch, level + "", x + MARGIN_LEFT_NUMBER, y + getHeight());
        } else {
            font.draw(batch, level + "", x + MARGIN_LEFT_NUMBER,
                y + getHeight() + MARGIN_BOTTOM_SECOND_ROW_NUMBER);
        }
    }

    public void setVisible(boolean visible) {
        imageButton.setVisible(visible);
    }

    @Override
    public void addListener() {
        imageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(screen);
            }
        });
    }
}
