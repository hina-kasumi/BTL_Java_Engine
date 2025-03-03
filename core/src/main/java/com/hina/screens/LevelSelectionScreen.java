package com.hina.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hina.ButtonClick.BackButton;
import com.hina.ButtonClick.ButtonList;
import com.hina.ButtonClick.LevelButton.LevelButton;
import com.hina.ButtonClick.LevelButton.LevelButtonList;
import com.hina.ButtonClick.LevelButton.NextLevelsButton;
import com.hina.ButtonClick.LevelButton.PrevLevelsButton;
import com.hina.ButtonClick.LinkToWebButton;
import com.hina.ButtonClick.SoundButton;
import com.hina.MyPanel.MainMenuBackground;
import com.hina.MyPanel.MyPanelList;
import com.hina.screens.GameScreen.GameScreen;

import static com.hina.constant.LevelSelectionScreenConst.*;
import static com.hina.constant.MainMenuConst.PADDING_MAIN_MENU;
import static com.hina.constant.MainMenuConst.SQUARE_SCALE;

public class LevelSelectionScreen implements Screen {
    private final Game game;
    private final FitViewport viewport;
    private final OrthographicCamera camera;
    private final Screen mainMenuScreen;
    private Stage stage;
    private ButtonList buttons;
    private MyPanelList myPanels;
    private LevelButtonList levelButtonList;

    public LevelSelectionScreen(Game game, FitViewport viewport, OrthographicCamera camera, Screen mainMenuScreen) {
        this.game = game;
        this.viewport = viewport;
        this.camera = camera;
        this.mainMenuScreen = mainMenuScreen;
    }

    @Override
    public void show() {
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        buttons = new ButtonList();
        myPanels = new MyPanelList();
        levelButtonList = new LevelButtonList();

        initLabel();
        initButton();
        initLevelButtons();

        myPanels.stageAddActor(stage);
        buttons.stageAddActor(stage);
        levelButtonList.stageAddActor(stage);
    }

    private void initLabel() {
        myPanels.add(new MainMenuBackground(viewport));
    }

    private void initButton() {
        //nút back
        BackButton backButton = new BackButton(
            "GUI/png/Buttons/Square/ArrowLeft-Thin/Default.png",
            "GUI/png/Buttons/Square/ArrowLeft-Thin/Hover.png",
            game, mainMenuScreen
        );
        backButton.setScale(SQUARE_SCALE);
        backButton.setPosition(PADDING_MAIN_MENU,
            viewport.getWorldHeight() - backButton.getHeight() - PADDING_MAIN_MENU);
        buttons.add(backButton);

        NextLevelsButton nextLevelsButton = new NextLevelsButton(
            "GUI/png/Buttons/Square/ArrowRight-Bold/Default.png",
            "GUI/png/Buttons/Square/ArrowRight-Bold/Hover.png",
            levelButtonList
        );
        nextLevelsButton.setScale(SQUARE_SCALE);
        nextLevelsButton.setPosition(viewport.getWorldWidth() / 2 + SCROLL_BUTTON_GAP,
            PADDING_MAIN_MENU + SCROLL_BUTTON_MARGIN_BOTTOM);
        buttons.add(nextLevelsButton);

        PrevLevelsButton prevLevelsButton = new PrevLevelsButton(
            "GUI/png/Buttons/Square/ArrowLeft-Bold/Default.png",
            "GUI/png/Buttons/Square/ArrowLeft-Bold/Hover.png",
            levelButtonList
        );
        prevLevelsButton.setScale(SQUARE_SCALE);
        prevLevelsButton.setPosition(viewport.getWorldWidth() / 2 - prevLevelsButton.getWidth() - SCROLL_BUTTON_GAP,
            PADDING_MAIN_MENU + SCROLL_BUTTON_MARGIN_BOTTOM);
        buttons.add(prevLevelsButton);

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

    private void initLevelButtons() {
        levelButtonList.add(new LevelButton(game, new GameScreen(game, viewport, camera)), LEVEL_SELECTION_SCALE);
        levelButtonList.add(new LevelButton(game, new GameScreen(game, viewport, camera)), LEVEL_SELECTION_SCALE);
        levelButtonList.add(new LevelButton(game, new GameScreen(game, viewport, camera)), LEVEL_SELECTION_SCALE);
        levelButtonList.add(new LevelButton(game, new GameScreen(game, viewport, camera)), LEVEL_SELECTION_SCALE);
        levelButtonList.add(new LevelButton(game, new GameScreen(game, viewport, camera)), LEVEL_SELECTION_SCALE);
        levelButtonList.add(new LevelButton(game, new GameScreen(game, viewport, camera)), LEVEL_SELECTION_SCALE);
        levelButtonList.add(new LevelButton(game, new GameScreen(game, viewport, camera)), LEVEL_SELECTION_SCALE);
        levelButtonList.add(new LevelButton(game, new GameScreen(game, viewport, camera)), LEVEL_SELECTION_SCALE);
        levelButtonList.add(new LevelButton(game, new GameScreen(game, viewport, camera)), LEVEL_SELECTION_SCALE);
        levelButtonList.setPosition((viewport.getWorldWidth() - levelButtonList.getWidth()) / 2,
            viewport.getWorldHeight() - LEVEL_SELECTION_MARGIN_TOP);
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
        levelButtonList.dispose();
    }
}
