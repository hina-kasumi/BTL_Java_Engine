package com.hina.screens.InnerScreens;

import com.hina.screens.LevelSelectionScreen;
import com.hina.screens.MainMenuScreen;
import com.hina.screens.ScreenAbstract;
import com.hina.ui.ButtonClick.ButtonAbstract;
import com.hina.ui.ButtonClick.ChangeScreenButton;
import com.hina.ui.ButtonClick.ReplayButton;

public class WinGameScreen extends EndGameScreen {
    public boolean winGame;

    public WinGameScreen(ScreenAbstract screen) {
        super(screen, "GUI/end-game/you_win.png");
        winGame = false;
    }

    @Override
    protected ButtonAbstract addButton() {
        return new ChangeScreenButton(
            "GUI/png/Buttons/Square/Play/Default.png",
            "GUI/png/Buttons/Square/Play/Hover.png",
            screen.getGame(), new LevelSelectionScreen(screen, new MainMenuScreen(screen))
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
