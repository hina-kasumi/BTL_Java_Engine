package com.hina.entities.enemy.Goblin;

public enum GoblinState {
    IDLE(4, "textures/monster/Monsters_Creatures_Fantasy/Goblin/Idle.png");

    private final int frameNumber;
    private final String fileName;

    GoblinState(int frameNumber, String fileName) {
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
