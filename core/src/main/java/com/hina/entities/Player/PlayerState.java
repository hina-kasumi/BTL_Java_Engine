package com.hina.entities.Player;

public enum PlayerState {
    IDLE("Idle.png"),
    RUNNING("Run.png"),
    ATTACK("Attack1.png"),
    JUMP("Jump.png"),
    FALL("Fall.png"),
    TAKE_HIT("Take Hit.png"),
    DEATH("Death.png"),
    ;

    private final String fileName;

    PlayerState(String fileName) {
        this.fileName = "textures/Player/" + fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
