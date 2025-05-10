package com.hina.screens;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Background {
    private final Texture background;

    public Background() {
        this.background = new Texture("maps/temp_assets/Final Taiga Forest/baner.png");
    }

    public void draw(SpriteBatch spriteBatch, Camera camera, FitViewport viewport) {
        spriteBatch.draw(background,
            camera.position.x - viewport.getWorldWidth() / 2,
            camera.position.y - viewport.getWorldHeight() / 2,
            viewport.getWorldWidth(),
            viewport.getWorldHeight());
    }

    public void dispose() {
        background.dispose();
    }
}
