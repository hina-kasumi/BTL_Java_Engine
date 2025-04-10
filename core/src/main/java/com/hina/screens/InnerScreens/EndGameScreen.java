package com.hina.screens.InnerScreens;

import com.badlogic.gdx.Gdx;
import com.hina.screens.ScreenAbstract;
import com.hina.ui.ButtonClick.ButtonAbstract;
import com.hina.ui.ButtonClick.HomeButton;
import com.hina.ui.MyPanel.Logotype;
import com.hina.ui.MyPanel.MyPanel;
import com.hina.ui.MyPanel.MyPanelList;

import static com.hina.constant.EndGameScreenConst.*;

public abstract class EndGameScreen extends InnerScreen {
    protected MyPanelList myPanels;
    protected MyPanel myPanel;

    public EndGameScreen(ScreenAbstract screen, String filename) {
        super(screen);
        initPanel(filename);
    }

    private void initPanel(String filename) {
        myPanels = new MyPanelList();

        myPanel = new Logotype(filename);
        myPanel.setScale(PANEL_SCALE);

        myPanels.add(myPanel);
        myPanels.stageAddActor(stage);
    }

    @Override
    protected void initButton() {
        Gdx.input.setInputProcessor(stage);

        HomeButton homeButton = new HomeButton(screen,
            "GUI/png/Buttons/Square/Home/Default.png",
            "GUI/png/Buttons/Square/Home/Hover.png"
        );
        buttons.add(homeButton);
        buttons.add(addButton());

        buttons.getList().forEach(i -> i.setScale(BUTTON_SCALE));
    }

    protected abstract ButtonAbstract addButton();
    protected abstract boolean conditionReturn();

    @Override
    public void update() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void draw(float v) {
        if (conditionReturn())
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
