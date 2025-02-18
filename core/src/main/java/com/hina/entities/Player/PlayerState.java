package com.hina.entities.Player;

import static com.hina.constant.PlayerConst.PLAYER_TEXTURE_SRC;

public enum PlayerState {
    IDLE("Idle.png"),
    RUNNING("Run.png"),
    ATTACK("Attack1.png"),
    JUMP("Jump.png"),
    FALL("Fall.png"),
    TAKE_HIT("Take Hit.png"),
    DEATH("Death.png"),
    ;

    private final String fileName;

    PlayerState(String fileName) {
        this.fileName = PLAYER_TEXTURE_SRC + fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
