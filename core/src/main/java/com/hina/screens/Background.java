package com.hina.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    private final Texture background;

    public Background() {
        this.background = new Texture("maps/oak_woods_v1.0/background/background_layer_1.png");
    }

    public void draw (SpriteBatch spriteBatch, float viewportWidth, float viewportHeight){

        spriteBatch.draw(background, 0, 0, viewportWidth, viewportHeight);
    }

    public void dispose(){
        background.dispose();
    }
}
