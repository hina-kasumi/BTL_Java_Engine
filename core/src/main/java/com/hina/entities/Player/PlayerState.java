package com.hina.entities.Player;

import static com.hina.constant.PlayerConst.PLAYER_TEXTURE_SRC;

public enum PlayerState {
    IDLE("idle/"),
    RUNNING("run/"),
    ATTACK("1_atk/"),
    JUMP("j_up/"),
    FALL("j_down/"),
    TAKE_HIT("take_hit/"),
    DEATH("death/"),
    ;

    private final String fileName;

    PlayerState(String fileName) {
        this.fileName = PLAYER_TEXTURE_SRC + fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
