package com.hina.screens.InnerScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hina.screens.ScreenAbstract;
import com.hina.ui.ButtonClick.*;

import java.util.List;

import static com.hina.constant.PauseScreenConst.*;

public class PauseManager extends InnerScreen {
    private boolean paused;
    private PauseButton pauseButton;
    private Stage pauseStage;

    public PauseManager(ScreenAbstract screen) {
        super(screen);
        this.paused = false;
    }

    @Override
    protected void initButton() {
        pauseStage = new Stage(viewport);
//        Gdx.input.setInputProcessor(stage);

        ContinueButton continueButton = new ContinueButton(
            this,
            "GUI/png/Buttons/Square/ArrowRight-Bold/Default.png",
            "GUI/png/Buttons/Square/ArrowRight-Bold/Hover.png");

        SoundButton soundButton = new SoundButton(
            "GUI/png/Buttons/Square/SoundOn/Default.png",
            "GUI/png/Buttons/Square/SoundOn/Hover.png",
            "GUI/png/Buttons/Square/SoundOff/Default.png",
            "GUI/png/Buttons/Square/SoundOff/Hover.png"
        );

        ReplayButton replayButton = new ReplayButton(screen,
            "GUI/png/Buttons/Square/Repeat/Default.png",
            "GUI/png/Buttons/Square/Repeat/Hover.png"
        );

        HomeButton homeButton = new HomeButton(screen,
            "GUI/png/Buttons/Square/Home/Default.png",
            "GUI/png/Buttons/Square/Home/Hover.png"
        );

        buttons.add(continueButton, replayButton, soundButton, homeButton);
        buttons.getList().forEach(i -> i.setScale(BUTTON_SCALE));

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

    @Override
    public void update() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            paused = !paused;
        }
    }

    @Override
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
            drawShadowBackground();

            stage.act(v);
            stage.draw();

        } else {
            Gdx.input.setInputProcessor(pauseStage);
            pauseButton.setPosition(
                camera.position.x + viewport.getWorldWidth() / 2 - pauseButton.getWidth() - BUTTON_OFFSET_RIGHT,
                camera.position.y + viewport.getWorldHeight() / 2 - BUTTON_OFFSET_TOP
            );

            pauseStage.act(v);
            pauseStage.draw();
        }

    }

    @Override
    protected void abstractDispose() {
        pauseStage.dispose();
        pauseButton.dispose();
    }
}
