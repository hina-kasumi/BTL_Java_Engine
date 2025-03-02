package com.hina;

import com.hina.music.BackgroundMusic;

public class MusicAndSoundManager {
    private final BackgroundMusic backgroundMusic;

    public MusicAndSoundManager() {
        this.backgroundMusic = new BackgroundMusic();
    }

    public void play() {
        backgroundMusic.playMusic();
    }

    public void stop() {
        backgroundMusic.stopMusic();
    }

    public void dispose() {
        backgroundMusic.dispose();
    }
}
