package com.hina.entities.enemy.BossEnemy.DemonSlime;

public enum DemonSlimeState {
    IDLE("01_demon_idle/"),
    ATTACK("03_demon_cleave/"),
    RUNNING("02_demon_walk/"),
    TAKE_HIT("04_demon_take_hit/"),
    DEATH("05_demon_death/");

    private final String path;

    DemonSlimeState(String path) {
        this.path = "assets/textures/monster/Demon_Slime/individual_sprites/" + path;
    }

    public String getPath() {
        return path;
    }
}
