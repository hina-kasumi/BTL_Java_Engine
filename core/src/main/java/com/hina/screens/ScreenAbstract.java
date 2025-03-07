package com.hina.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;

public abstract class ScreenAbstract implements Screen {
    protected final Game game;
    protected final FitViewport viewport;
    protected final OrthographicCamera camera;

    public ScreenAbstract(Game game, FitViewport viewport, OrthographicCamera camera) {
        this.game = game;
        this.viewport = viewport;
        this.camera = camera;
    }

    public ScreenAbstract(ScreenAbstract screen) {
        this.game = screen.game;
        this.viewport = screen.viewport;
        this.camera = screen.camera;
    }

    public Game getGame() {
        return game;
    }

    public FitViewport getViewport() {
        return viewport;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
