package com.hina.entities.Player;

public enum HeroState {
    IDLE("idle/"),
    RUNNING("run/"),
    ATTACK("1_atk/"),
    AIR_ATTACK("air_atk/"),
    SPECIAL_ATTACK("sp_atk/"),
    ROLL("roll/"),
    JUMP("j_up/"),
    FALL("j_down/"),
    TAKE_HIT("take_hit/"),
    DEATH("death/"),
    DEFEND("defend/"),
    ;

    private final String fileName;

    HeroState(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
