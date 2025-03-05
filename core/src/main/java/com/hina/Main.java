package com.hina;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hina.music.MusicAndSoundManager;
import com.hina.screens.MainMenuScreen;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends Game {
    public static MusicAndSoundManager musicAndSoundManager;

    @Override
    public void create() {
        OrthographicCamera camera = new OrthographicCamera();
        FitViewport viewport = new FitViewport(32, 18, camera);
        musicAndSoundManager = new MusicAndSoundManager();
        musicAndSoundManager.play();

        setScreen(new MainMenuScreen(this, viewport, camera));

    }
}
