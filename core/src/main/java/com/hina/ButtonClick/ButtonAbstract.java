package com.hina.ButtonClick;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import static com.hina.Main.musicAndSoundManager;
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

        initListener();
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

        initListener();
    }

    private void initListener() {
        imageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                musicAndSoundManager.playButtonClickSound();
                addListener(event, x, y);
            }
        });
    }

    public abstract void addListener(InputEvent event, float x, float y);

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
        normalTexture.dispose();
        hoverTexture.dispose();
        texture.dispose();
    }
}
