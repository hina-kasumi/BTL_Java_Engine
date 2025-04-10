package com.hina.screens.InnerScreens;

import com.hina.screens.ScreenAbstract;
import com.hina.ui.ButtonClick.ButtonAbstract;
import com.hina.ui.ButtonClick.ReplayButton;

public class WinGameScreen extends EndGameScreen {
    public boolean winGame;

    public WinGameScreen(ScreenAbstract screen) {
        super(screen, "GUI/end-game/you_win.png");
        winGame = false;
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
        return !isWinGame();
    }

    public boolean isWinGame() {
        return winGame;
    }

    public void setWinGame(boolean winGame) {
        this.winGame = winGame;
    }
}
