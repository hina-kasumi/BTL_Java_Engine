package com.hina.ButtonClick;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hina.screens.MainMenuScreen;

public class HomeButton extends ButtonAbstract{
    private Game game;
    private FitViewport viewport;
    private OrthographicCamera camera;

    public HomeButton(Game game, FitViewport viewport, OrthographicCamera camera, String src) {
        super(src);
        init(game, viewport, camera);
    }

    public HomeButton(Game game, FitViewport viewport, OrthographicCamera camera, String normalSrc, String hoverSrc) {
        super(normalSrc, hoverSrc);
        init(game, viewport, camera);
    }

    private void init(Game game, FitViewport viewport, OrthographicCamera camera) {
        this.game = game;
        this.viewport = viewport;
        this.camera = camera;
    }

    @Override
    public void addListener(InputEvent event, float x, float y) {
        game.setScreen(new MainMenuScreen(game, viewport, camera));
    }
}
