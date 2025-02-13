package com.hina.entities;

public enum AnimationState implements Comparable<AnimationState> {
    IDLE(0),
    RUN(1),
    JUMP(2),
    FALL(3),
    ATTACK(4),
    TAKE_HIT(5),
    DEATH(6)
    ;

    private final int priority;

    AnimationState(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
