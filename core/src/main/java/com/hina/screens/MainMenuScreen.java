package com.hina.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hina.ui.ButtonClick.*;
import com.hina.ui.MyPanel.Logotype;
import com.hina.ui.MyPanel.MainMenuBackground;
import com.hina.ui.MyPanel.MyPanelList;

import static com.hina.constant.MainMenuConst.*;

public class MainMenuScreen extends ScreenAbstract {
    private Stage stage;
    private ButtonList buttons;// dùng để nhóm các nút lại
    private MyPanelList myPanels;

    public MainMenuScreen(ScreenAbstract screen) {
        super(screen);
    }

    public MainMenuScreen(Game game, FitViewport viewport, OrthographicCamera camera) {
        super(game, viewport, camera);
    }

    @Override
    public void show() {
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        buttons = new ButtonList();
        myPanels = new MyPanelList();

        initLabel();
        initButton();

        myPanels.stageAddActor(stage);
        buttons.stageAddActor(stage);
    }

    private void initLabel() {
        myPanels.add(new MainMenuBackground(viewport));

        Logotype logotype = new Logotype("GUI/png/Logotype/NameBack-1.png");
        logotype.setScale(LABEL_SCALE);
        logotype.setPosition((viewport.getWorldWidth() - logotype.getWidth()) / 2,
            viewport.getWorldHeight() - logotype.getHeight() - PADDING_MAIN_MENU);
        myPanels.add(logotype);
    }

    private void initButton() {
        //nút thoát trò chơi
        ExitButton exitButton = new ExitButton(
            "GUI/png/Buttons/Rect/ExitText/Default.png",
            "GUI/png/Buttons/Rect/ExitText/Hover.png");
        exitButton.setScale(RECT_SCALE);
        exitButton.setPosition((viewport.getWorldWidth() - exitButton.getWidth()) / 2,
            EXIT_BUTTON_MARGIN_BOTTOM);

        // nút bắt đầu chơi
        PlayButton playButton = new PlayButton(this,
            "GUI/png/Buttons/Rect/PlayText/Default.png",
            "GUI/png/Buttons/Rect/PlayText/Hover.png");
        playButton.setScale(RECT_SCALE);
        playButton.setPosition((viewport.getWorldWidth() - playButton.getWidth()) / 2,
            EXIT_BUTTON_MARGIN_BOTTOM + exitButton.getHeight() + GAP_RECT_BUTTON);

        //nút âm thanh
        SoundButton soundButton = new SoundButton(
            "GUI/png/Buttons/Square/SoundOn/Default.png",
            "GUI/png/Buttons/Square/SoundOn/Hover.png",
            "GUI/png/Buttons/Square/SoundOff/Default.png",
            "GUI/png/Buttons/Square/SoundOff/Hover.png"
        );
        soundButton.setScale(SQUARE_SCALE);
        soundButton.setPosition(PADDING_MAIN_MENU, PADDING_MAIN_MENU);

        // nối với trang chủ
        LinkToWebButton linkToWebButton = new LinkToWebButton(
            "GUI/png/Buttons/Square/Star/Default.png",
            "GUI/png/Buttons/Square/Star/Hover.png",
            "https://www.youtube.com"
        );
        linkToWebButton.setScale(SQUARE_SCALE);
        linkToWebButton.setPosition(
            viewport.getWorldWidth() - linkToWebButton.getWidth() - PADDING_MAIN_MENU,
            PADDING_MAIN_MENU);

        buttons.add(playButton, exitButton, soundButton, linkToWebButton);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.BLACK);
        stage.act(v);
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {
        viewport.update(i, i1, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        buttons.dispose();
        myPanels.dispose();
    }
}
