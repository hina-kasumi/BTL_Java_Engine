package com.hina.entities.enemy.BasicEnemy.Skeletion;

import static com.hina.constant.BasicMonsterConst.SOURCE_BASIC_MONSTER_TEXTURE;

public enum SkeletonState {
    IDLE("Idle.png"),
    ATTACK("Attack.png"),
    RUNNING("Walk.png"),
    TAKE_HIT("Take Hit.png"),
    DEATH("Death.png"),
    ;

    private final String fileName;

    SkeletonState(String fileName) {
        this.fileName = SOURCE_BASIC_MONSTER_TEXTURE + "Skeleton/" + fileName;
    }


    public String getFileName() {
        return fileName;
    }
}
