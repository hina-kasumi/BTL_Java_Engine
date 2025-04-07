package com.hina.entities.enemy.BossEnemy.FrostGuardian;

public enum FrostGuardianState {
    IDLE("idle/"),
    ATTACK("1_atk/"),
    RUNNING("walk/"),
    TAKE_HIT("take_hit/"),
    DEATH("death/");

    private final String path;

    FrostGuardianState(String path) {
        this.path = "assets/textures/monster/Frost_Guardian/PNG files/" + path;
    }

    public String getPath() {
        return path;
    }
}
