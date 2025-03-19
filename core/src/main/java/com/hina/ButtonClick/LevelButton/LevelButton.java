package com.hina.ButtonClick.LevelButton;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hina.ButtonClick.ButtonAbstract;
import com.hina.ui.MyText;

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

    public void draw(MyText myText, SpriteBatch batch) {
        float x = imageButton.getX();
        float y = imageButton.getY();
        float width = myText.getWidth(level, FONT_SCALE);
        myText.drawText(batch,
            x + getWidth() / 2 - width / 2,
            y + 2, level, FONT_SCALE);
    }

    @Override
    public void addListener(InputEvent event, float x, float y) {
        game.setScreen(screen);
    }
}
