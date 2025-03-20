package com.hina.ui.ButtonClick;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import static com.hina.Main.musicAndSoundManager;

public class SoundButton extends ButtonAbstract {
    private final Texture soundOnSrc;
    private Texture soundOnHOverSrc;
    private final Texture soundOffSrc;
    private Texture soundOffHOverSrc;
    private boolean soundOn;

    public SoundButton(String onNormal, String offNormal) {
        super(onNormal);
        soundOnSrc = new Texture(onNormal);
        soundOffSrc = new Texture(offNormal);
        init();
    }

    public SoundButton(String onNormal, String onHover, String offNormal, String offHover) {
        super(onNormal, onHover);
        soundOnSrc = new Texture(onNormal);
        soundOnHOverSrc = new Texture(onHover);
        soundOffSrc = new Texture(offNormal);
        soundOffHOverSrc = new Texture(offHover);
        init();
    }

    private void init() {
        boolean isMusicOn = musicAndSoundManager.isPLaying();
        changeButtonImage(isMusicOn);
    }

    private void changeButtonImage(boolean soundOn) {
        this.soundOn = soundOn;
        TextureRegionDrawable normal = new TextureRegionDrawable((soundOn) ? soundOnSrc : soundOffSrc);
        TextureRegionDrawable hover = new TextureRegionDrawable((soundOn) ? soundOnHOverSrc : soundOffHOverSrc);
        imageButton.getStyle().imageUp = normal;
        imageButton.getStyle().imageOver = hover;

        musicAndSoundManager.setPlaying(soundOn);
    }

    @Override
    public void addListener(InputEvent event, float x, float y) {
        changeButtonImage(!soundOn);
    }

    @Override
    public void dispose() {
        normalTexture.dispose();
        hoverTexture.dispose();
        texture.dispose();
        soundOffSrc.dispose();
        soundOffHOverSrc.dispose();
        soundOnHOverSrc.dispose();
        soundOnSrc.dispose();
    }
}
