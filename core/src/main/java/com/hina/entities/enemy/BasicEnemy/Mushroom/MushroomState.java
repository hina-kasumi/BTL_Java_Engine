package com.hina.entities.enemy.BasicEnemy.Mushroom;

import static com.hina.constant.BasicMonsterConst.SOURCE_BASIC_MONSTER_TEXTURE;

public enum MushroomState {
    IDLE("Idle.png"),
    ATTACK("Attack.png"),
    RUNNING("Run.png"),
    TAKE_HIT("Take Hit.png"),
    DEATH("Death.png");
    ;

    private final String fileName;

    MushroomState(String fileName) {
        this.fileName = SOURCE_BASIC_MONSTER_TEXTURE + "Mushroom/" + fileName;
    }


    public String getFileName() {
        return fileName;
    }
}
