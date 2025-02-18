package com.hina.entities.enemy.BasicEnemy.Goblin;

import static com.hina.constant.BasicMonsterConst.SOURCE_BASIC_MONSTER_TEXTURE;

public enum GoblinState {
    IDLE("Idle.png"),
    ATTACK("Attack.png"),
    RUNNING("Run.png"),
    TAKE_HIT("Take Hit.png"),
    DEATH("Death.png")
    ;

    private final String fileName;

    GoblinState(String fileName) {
        this.fileName = SOURCE_BASIC_MONSTER_TEXTURE + "Goblin/" + fileName;
    }


    public String getFileName() {
        return fileName;
    }
}
