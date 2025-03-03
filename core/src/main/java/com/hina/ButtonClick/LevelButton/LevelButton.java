package com.hina.ButtonClick.LevelButton;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hina.ButtonClick.ButtonAbstract;

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
        font.draw(batch, level + "", x + 0.5f, y + getHeight());
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
