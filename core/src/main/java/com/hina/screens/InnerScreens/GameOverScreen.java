package com.hina.screens.InnerScreens;

import com.hina.screens.ScreenAbstract;
import com.hina.ui.ButtonClick.ButtonAbstract;
import com.hina.ui.ButtonClick.ReplayButton;

public class GameOverScreen extends EndGameScreen {
    private boolean gameOver;

    public GameOverScreen(ScreenAbstract screen) {
        super(screen, "GUI/end-game/you-died.png");
        gameOver = false;
    }

    @Override
    protected ButtonAbstract addButton() {
        return new ReplayButton(screen,
            "GUI/png/Buttons/Square/Repeat/Default.png",
            "GUI/png/Buttons/Square/Repeat/Hover.png"
        );
    }

    @Override
    protected boolean conditionReturn() {
        return !isGameOver();
    }
    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }


}
