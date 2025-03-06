package com.hina.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import static com.hina.constant.SoundAndMusicConst.*;

public class MusicAndSoundManager {
    private final Preferences prefs;
    private final Music backgroundMusic;
    private final Sound buttonClickSound;

    public MusicAndSoundManager() {
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(BACKGROUND_MUSIC_SRC));
        backgroundMusic.setLooping(true);
        buttonClickSound = Gdx.audio.newSound(Gdx.files.internal(BUTTON_SOUND_CLICK_SRC));

        this.turnOnVolume();
        this.prefs = Gdx.app.getPreferences(PREFERENCES);
    }

    public void play() {
        backgroundMusic.play();
        setPlaying(isPLaying());
    }

    public boolean isPLaying() {
        return prefs.getBoolean(BOOLEAN_MUSIC_ON, true);
    }

    public void mute() {
        backgroundMusic.setVolume(MUTE_VOLUME);
    }

    public void turnOnVolume() {
        backgroundMusic.setVolume(BACKGROUND_MUSIC_VOLUME);
    }

    public void setPlaying(boolean soundOn) {
        if (soundOn) turnOnVolume();
        else mute();
        putBoolean(soundOn);
        prefs.flush();
    }

    private void putBoolean(boolean value) {
        prefs.putBoolean(BOOLEAN_MUSIC_ON, value);
    }

    public void playButtonClickSound() {
        if (isPLaying()) {
            long id = buttonClickSound.play();
            buttonClickSound.setVolume(id, BUTTON_CLICK_SOUND_VOLUME);
        }
    }

    public void dispose() {
        buttonClickSound.dispose();
        backgroundMusic.dispose();
    }
}
