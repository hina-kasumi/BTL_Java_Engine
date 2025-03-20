package com.hina.screens;

import com.badlogic.gdx.Gdx;
import com.hina.ui.ButtonClick.ButtonAbstract;
import com.hina.ui.ButtonClick.HomeButton;
import com.hina.ui.ButtonClick.ReplayButton;
import com.hina.ui.MyPanel.Logotype;
import com.hina.ui.MyPanel.MyPanel;
import com.hina.ui.MyPanel.MyPanelList;

import static com.hina.constant.GameOverScreenConst.*;

public class GameOverScreen extends InnerScreen {
    private MyPanelList myPanels;
    private boolean gameOver;
    private MyPanel myPanel;

    public GameOverScreen(ScreenAbstract screen) {
        super(screen);
        initPanel();
        gameOver = false;
    }

    private void initPanel() {
        myPanels = new MyPanelList();

        myPanel = new Logotype("GUI/end-game/you-died.png");
        myPanel.setScale(PANEL_SCALE);

        myPanels.add(myPanel);
        myPanels.stageAddActor(stage);
    }

    @Override
    protected void initButton() {
        Gdx.input.setInputProcessor(stage);

        ReplayButton replayButton = new ReplayButton(screen,
            "GUI/png/Buttons/Square/Repeat/Default.png",
            "GUI/png/Buttons/Square/Repeat/Hover.png"
        );

        HomeButton homeButton = new HomeButton(screen,
            "GUI/png/Buttons/Square/Home/Default.png",
            "GUI/png/Buttons/Square/Home/Hover.png"
        );

        buttons.add(homeButton, replayButton);
        buttons.getList().forEach(i -> i.setScale(BUTTON_SCALE));
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    @Override
    public void update() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void draw(float v) {
        if (!isGameOver())
            return;
        drawShadowBackground();

        myPanel.setPosition(
            camera.position.x - myPanel.getWidth() / 2,
            camera.position.y + PANEL_MARGIN_BOTTOM);

        ButtonAbstract firstButton = buttons.getList().getFirst();
        firstButton.setPosition(
            camera.position.x - firstButton.getWidth() - BUTTON_GAP,
            camera.position.y - BUTTON_MARGIN_TOP
        );

        buttons.getList().getLast().setPosition(
            camera.position.x + BUTTON_GAP,
            camera.position.y - BUTTON_MARGIN_TOP
        );

        stage.act(v);
        stage.draw();
    }

    @Override
    protected void abstractDispose() {
        myPanels.dispose();
    }
}
