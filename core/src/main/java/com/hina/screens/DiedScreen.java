package com.hina.screens;

import com.badlogic.gdx.Gdx;
import com.hina.ButtonClick.HomeButton;
import com.hina.ButtonClick.ReplayButton;
import com.hina.MyPanel.Logotype;
import com.hina.MyPanel.MyPanel;
import com.hina.MyPanel.MyPanelList;

import static com.hina.constant.DiedScreenConst.*;

public class DiedScreen extends InnerScreen {
    private MyPanelList myPanels;

    public DiedScreen(ScreenAbstract screen) {
        super(screen);
        initPanel();
    }

    private void initPanel() {
        myPanels = new MyPanelList();

        MyPanel myPanel = new Logotype("GUI/end-game/you-died.png");
        myPanel.setScale(PANEL_SCALE);
        myPanel.setPosition((viewport.getWorldWidth() - myPanel.getWidth()) / 2,
            viewport.getWorldHeight() - myPanel.getHeight() - PANEL_MARGIN_TOP);
        myPanels.add(myPanel);
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

    @Override
    public void update() {

    }

    @Override
    public void draw(float v) {
        drawShadowBackground();

        stage.act(v);
        stage.draw();
    }

    @Override
    protected void abstractDispose() {

    }
}
