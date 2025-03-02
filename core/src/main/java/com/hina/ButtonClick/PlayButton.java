package com.hina.ButtonClick;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hina.screens.GameScreen.GameScreen;

public class PlayButton extends ButtonAbstract {
    private final Game game;
    private final FitViewport viewport;
    private final OrthographicCamera camera;

    public PlayButton(Game game, FitViewport viewport, OrthographicCamera camera, String src) {
        super(src);
        this.game = game;
        this.viewport = viewport;
        this.camera = camera;
    }

    public PlayButton(Game game, FitViewport viewport, OrthographicCamera camera, String normal, String hover) {
        super(normal, hover);
        this.game = game;
        this.viewport = viewport;
        this.camera = camera;
    }

    @Override
    public void addListener() {
        imageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, viewport, camera));
            }
        });
    }
}
