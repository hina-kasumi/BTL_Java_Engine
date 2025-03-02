package com.hina.ButtonClick;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import static com.hina.constant.GameConst.PPM;

public abstract class ButtonAbstract {
    protected Texture texture;
    protected ImageButton imageButton;

    protected Texture normalTexture;
    protected Texture hoverTexture;

    private float originWidth;
    private float originHeight;

    private float scale = 1;

    public ButtonAbstract(String src) {
        this.texture = new Texture(src);
        TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(new TextureRegion(texture));
        this.imageButton = new ImageButton(textureRegionDrawable);
        originWidth = imageButton.getWidth() / PPM;
        originHeight = imageButton.getHeight() / PPM;
        imageButton.setSize(originWidth, originHeight);

        addListener();
    }

    public ButtonAbstract(String normalSrc, String hoverSrc) {
        this.normalTexture = new Texture(normalSrc);
        this.hoverTexture = new Texture(hoverSrc);

        TextureRegionDrawable normalDrawAble = new TextureRegionDrawable(normalTexture);
        TextureRegionDrawable hoverDrawAble = new TextureRegionDrawable(hoverTexture);

        ImageButton.ImageButtonStyle buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.imageUp = normalDrawAble;
        buttonStyle.imageOver = hoverDrawAble;

        this.imageButton = new ImageButton(buttonStyle);
        originWidth = imageButton.getWidth() / PPM;
        originHeight = imageButton.getHeight() / PPM;
        imageButton.setSize(originWidth, originHeight);

        addListener();
    }

    public float getWidth() {
        return originWidth * scale;
    }

    public float getHeight() {
        return originHeight * scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
        imageButton.setSize(originWidth * scale, originHeight * scale);
    }

    public abstract void addListener();

    public void setSize(float width, float height) {
        originWidth = width;
        originHeight = height;
        imageButton.setSize(width, height);
    }

    public void setPosition(float x, float y) {
        imageButton.setPosition(x, y);
    }

    public ImageButton getImageButton() {
        return imageButton;
    }

    public void dispose() {
        texture.dispose();
    }
}
