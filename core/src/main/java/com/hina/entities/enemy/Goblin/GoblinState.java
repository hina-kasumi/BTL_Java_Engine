package com.hina.entities.enemy.Goblin;

public enum GoblinState {
    IDLE("textures/monster/Monsters_Creatures_Fantasy/Goblin/Idle.png"),
    ATTACK("textures/monster/Monsters_Creatures_Fantasy/Goblin/Attack.png"),
    RUNNING("textures/monster/Monsters_Creatures_Fantasy/Goblin/Run.png"),
    ;

    private final String fileName;

    GoblinState(String fileName) {
        this.fileName = fileName;
    }


    public String getFileName() {
        return fileName;
    }
}
