package com.hina.entities.enemy.BossEnemy.FireWorm;


public enum FireWormState {
    IDLE("Idle.png"),
    ATTACK("Attack.png"),
    RUNNING("Walk.png"),
    TAKE_HIT("Get Hit.png"),
    DEATH("Death.png")
        ;

    private final String fileName;

    FireWormState(String fileName) {
        this.fileName = "textures/monster/Fire Worm/Sprites/Worm/" + fileName;
    }


    public String getFileName() {
        return fileName;
    }
}
