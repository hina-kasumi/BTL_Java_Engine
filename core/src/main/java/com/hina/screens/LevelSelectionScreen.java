package com.hina.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hina.ButtonClick.ButtonList;
import com.hina.ButtonClick.LinkToWebButton;
import com.hina.ButtonClick.SoundButton;
import com.hina.MyPanel.MainMenuBackground;
import com.hina.MyPanel.MyPanelList;

import static com.hina.constant.MainMenuConst.PADDING_MAIN_MENU;
import static com.hina.constant.MainMenuConst.SQUARE_SCALE;

public class LevelSelectionScreen implements Screen {
    private final Game game;
    private final FitViewport viewport;
    private final OrthographicCamera camera;
    private Stage stage;
    private ButtonList buttons;
    private MyPanelList myPanels;

    public LevelSelectionScreen(Game game, FitViewport viewport, OrthographicCamera camera) {
        this.game = game;
        this.viewport = viewport;
        this.camera = camera;
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
    }

    private void initButton() {
        //nút âm thanh
        SoundButton soundButton = new SoundButton(
            "GUI/png/Buttons/Square/SoundOn/Default.png",
            "GUI/png/Buttons/Square/SoundOn/Hover.png",
            "GUI/png/Buttons/Square/SoundOff/Default.png",
            "GUI/png/Buttons/Square/SoundOff/Hover.png"
        );
        soundButton.setScale(SQUARE_SCALE);
        soundButton.setPosition(PADDING_MAIN_MENU, PADDING_MAIN_MENU);
        buttons.add(soundButton);

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
        buttons.add(linkToWebButton);
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
