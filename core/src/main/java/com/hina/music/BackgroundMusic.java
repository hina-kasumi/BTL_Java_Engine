package com.hina.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class BackgroundMusic {
    private final Music music;

    public BackgroundMusic() {
        music = Gdx.audio.newMusic(Gdx.files.internal("music/videoplayback.mp3"));
        music.setLooping(true);
    }

    public void playMusic() {
        if (!music.isPlaying()) {
            music.play();
        }
    }

    public void stopMusic() {
        if (music.isPlaying()) {
            music.stop();
        }
    }

    public void dispose() {
        music.dispose();
    }
}
