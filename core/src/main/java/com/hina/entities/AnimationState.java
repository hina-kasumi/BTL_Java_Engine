package com.hina.entities;

public enum AnimationState implements Comparable<AnimationState> {
    IDLE(0),
    RUN(1),
    JUMP(2),
    FALL(3),
    ATTACK(4),
    AIR_ATTACK(5),
    SPECIAL_ATTACK(6),
    ROLL(7),
    DEFEND(8),
    TAKE_HIT(9),
    DEATH(10)
    ;

    private final int priority;

    AnimationState(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
