package com.hina.entities.Player;

public enum PlayerState {
    IDLE(8, "textures/Player/Idle.png"),
    RUNNING(8, "textures/Player/Run.png"),
    ATTACK(6, "textures/Player/Attack1.png"),
    JUMP(2, "textures/Player/Jump.png"),
    FALL(2, "textures/Player/Fall.png"),
    ;

    private final int frameNumber;
    private final String fileName;

    PlayerState(int frameNumber, String fileName) {
        this.frameNumber = frameNumber;
        this.fileName = fileName;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public String getFileName() {
        return fileName;
    }
}
