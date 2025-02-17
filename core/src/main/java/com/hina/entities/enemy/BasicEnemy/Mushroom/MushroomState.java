package com.hina.entities.enemy.BasicEnemy.Mushroom;

public enum MushroomState {
    IDLE("textures/monster/Monsters_Creatures_Fantasy/Mushroom/Idle.png"),
    ATTACK("textures/monster/Monsters_Creatures_Fantasy/Mushroom/Attack.png"),
    RUNNING("textures/monster/Monsters_Creatures_Fantasy/Mushroom/Run.png"),
    TAKE_HIT("textures/monster/Monsters_Creatures_Fantasy/Mushroom/Take Hit.png"),
    DEATH("textures/monster/Monsters_Creatures_Fantasy/Mushroom/Death.png");
    ;

    private final String fileName;

    MushroomState(String fileName) {
        this.fileName = fileName;
    }


    public String getFileName() {
        return fileName;
    }
}
