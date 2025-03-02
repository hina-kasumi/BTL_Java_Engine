package com.hina.MyPanel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import static com.hina.constant.GameConst.PPM;

public abstract class MyPanel {
    private Texture texture;
    private Image image;

    public MyPanel(String src) {
        texture = new Texture(src);
        image = new Image(texture);
        setSize(texture.getWidth() / PPM, texture.getHeight() / PPM);
    }

    public void setPosition(float x, float y) {
        image.setPosition(x, y);
    }

    public float getWidth() {
        return image.getWidth();
    }

    public float getHeight() {
        return image.getHeight();
    }

    public void setScale(float scale) {
        image.setSize(getWidth() * scale, getHeight() * scale);
    }

    public void setSize(float width, float height) {
        image.setSize(width, height);
    }

    public void dispose() {
        texture.dispose();
    }

    public Image getImage() {
        return image;
    }
}
