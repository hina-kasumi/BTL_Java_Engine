package com.hina.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;

import static com.hina.constant.SoundAndMusicConst.*;

public class MusicAndSoundManager {
    private final Preferences prefs;
    private final Music backgroundMusic;


    public MusicAndSoundManager() {
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(BACKGROUND_MUSIC_SRC));
        backgroundMusic.setLooping(true);
        this.turnOnVolume();

        this.prefs = Gdx.app.getPreferences(PREFERENCES);
    }

    public void play() {
        backgroundMusic.play();
        setPlaying(prefs.getBoolean(BOOLEAN_MUSIC_ON, true));
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
        if (soundOn)
            turnOnVolume();
        else
            mute();
        prefs.putBoolean(BOOLEAN_MUSIC_ON, soundOn);
        prefs.flush();
    }

    public void dispose() {
        backgroundMusic.dispose();
    }
}
