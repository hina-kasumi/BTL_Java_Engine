package com.hina.entities.enemy.BasicEnemy.Skeletion;

public enum SkeletonState {
    IDLE("textures/monster/Monsters_Creatures_Fantasy/Skeleton/Idle.png"),
    ATTACK("textures/monster/Monsters_Creatures_Fantasy/Skeleton/Attack.png"),
    RUNNING("textures/monster/Monsters_Creatures_Fantasy/Skeleton/Walk.png"),
    TAKE_HIT("textures/monster/Monsters_Creatures_Fantasy/Skeleton/Take Hit.png"),
    DEATH("textures/monster/Monsters_Creatures_Fantasy/Skeleton/Death.png");
    ;

    private final String fileName;

    SkeletonState(String fileName) {
        this.fileName = fileName;
    }


    public String getFileName() {
        return fileName;
    }
}
