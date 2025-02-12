package com.hina.entities.Player;

public enum PlayerState {
    IDLE("textures/Player/Idle.png"),
    RUNNING("textures/Player/Run.png"),
    ATTACK("textures/Player/Attack1.png"),
    JUMP("textures/Player/Jump.png"),
    FALL("textures/Player/Fall.png"),
    TAKE_HIT("textures/Player/Take Hit.png"),
    DEATH("textures/Player/Death.png"),
    ;

    private final String fileName;

    PlayerState(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
