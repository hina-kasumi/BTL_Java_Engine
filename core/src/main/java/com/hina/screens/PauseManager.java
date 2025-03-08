package com.hina.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hina.ButtonClick.*;

import java.util.List;

import static com.hina.constant.PauseScreenConst.*;

public class PauseManager {
    private final ScreenAbstract screen;
    private final FitViewport viewport;
    private final OrthographicCamera camera;
    private final ShapeRenderer shapeRenderer;
    private boolean paused;
    private Stage stage;
    private ButtonList buttons;
    private PauseButton pauseButton;
    private Stage pauseStage;

    public PauseManager(ScreenAbstract screen) {
        this.screen = screen;
        this.viewport = screen.getViewport();
        this.camera = screen.getCamera();
        this.paused = false;
        this.shapeRenderer = new ShapeRenderer();

        initButton();
    }

    private void initButton() {
        stage = new Stage(viewport);
        pauseStage = new Stage(viewport);
//        Gdx.input.setInputProcessor(stage);
        buttons = new ButtonList();

        ContinueButton continueButton = new ContinueButton(
            this,
            "GUI/png/Buttons/Square/ArrowRight-Bold/Default.png",
            "GUI/png/Buttons/Square/ArrowRight-Bold/Hover.png");
        buttons.add(continueButton);

        SoundButton soundButton = new SoundButton(
            "GUI/png/Buttons/Square/SoundOn/Default.png",
            "GUI/png/Buttons/Square/SoundOn/Hover.png",
            "GUI/png/Buttons/Square/SoundOff/Default.png",
            "GUI/png/Buttons/Square/SoundOff/Hover.png"
        );
        buttons.add(soundButton);

        ReplayButton replayButton = new ReplayButton(screen,
            "GUI/png/Buttons/Square/Repeat/Default.png",
            "GUI/png/Buttons/Square/Repeat/Hover.png"
        );
        buttons.add(replayButton);

        HomeButton homeButton = new HomeButton(screen,
            "GUI/png/Buttons/Square/Home/Default.png",
            "GUI/png/Buttons/Square/Home/Hover.png"
        );
        buttons.add(homeButton);

        List<ButtonAbstract> list = buttons.getList();
        for (ButtonAbstract button : list) {
            button.setScale(BUTTON_SCALE);
        }

        buttons.stageAddActor(stage);

        pauseButton = new PauseButton(this,
            "GUI/png/Buttons/Square/Pause/Default.png",
            "GUI/png/Buttons/Square/Pause/Hover.png"
        );
        pauseButton.setScale(BUTTON_SCALE);
        pauseStage.addActor(pauseButton.getImageButton());
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isPaused() {
        return paused;
    }

    public void update() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            paused = !paused;
        }
    }

    public void draw(float v) {
        update();
        if (paused) {
            Gdx.input.setInputProcessor(stage);
            List<ButtonAbstract> list = buttons.getList();
            for (int i = 0; i < list.size(); i++) {
                ButtonAbstract button = list.get(i);
                button.setPosition(
                    camera.position.x + viewport.getWorldWidth() / 2 - button.getWidth() - BUTTON_OFFSET_RIGHT,
                    camera.position.y + viewport.getWorldHeight() / 2 - i * (button.getHeight() + BUTTON_GAP) - BUTTON_OFFSET_TOP);
            }

            Gdx.gl.glEnable(Gdx.gl.GL_BLEND);
            Gdx.gl.glBlendFunc(Gdx.gl.GL_SRC_ALPHA, Gdx.gl.GL_ONE_MINUS_SRC_ALPHA);

            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0, 0, 0, OPACITY); // Màu đen mờ 50%
            shapeRenderer.rect(
                camera.position.x - viewport.getWorldWidth() / 2,
                camera.position.y - viewport.getWorldHeight() / 2,
                viewport.getWorldWidth(),
                viewport.getWorldHeight());
            shapeRenderer.end();

            stage.act(v);
            stage.draw();

            Gdx.gl.glDisable(Gdx.gl.GL_BLEND);
        } else {
            Gdx.input.setInputProcessor(pauseStage);
            pauseButton.setPosition(
                camera.position.x + viewport.getWorldWidth() / 2 - pauseButton.getWidth() - BUTTON_OFFSET_RIGHT,
                camera.position.y + viewport.getWorldHeight() / 2 - BUTTON_OFFSET_TOP
            );

            pauseStage.addActor(pauseButton.getImageButton());

            pauseStage.act(v);
            pauseStage.draw();
        }

    }

    public void dispose() {
        pauseStage.dispose();
        stage.dispose();
        buttons.dispose();
        pauseButton.dispose();
    }
}
