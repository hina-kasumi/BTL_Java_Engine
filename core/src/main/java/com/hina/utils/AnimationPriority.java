package com.hina.utils;

import com.hina.entities.AnimationState;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class AnimationPriority {
    private final PriorityQueue<Integer> priorityQueue;
    private final Map<Integer, AnimationState> map;

    public AnimationPriority() {
        priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

        map = new HashMap<>();
        map.put(AnimationState.IDLE.getPriority(), AnimationState.IDLE);
        map.put(AnimationState.JUMP.getPriority(), AnimationState.JUMP);
        map.put(AnimationState.FALL.getPriority(), AnimationState.FALL);
        map.put(AnimationState.ATTACK.getPriority(), AnimationState.ATTACK);
        map.put(AnimationState.TAKE_HIT.getPriority(), AnimationState.TAKE_HIT);
        map.put(AnimationState.DEATH.getPriority(), AnimationState.DEATH);
        map.put(AnimationState.RUN.getPriority(), AnimationState.RUN);
        map.put(AnimationState.AIR_ATTACK.getPriority(), AnimationState.AIR_ATTACK);
        map.put(AnimationState.SPECIAL_ATTACK.getPriority(), AnimationState.SPECIAL_ATTACK);
        map.put(AnimationState.ROLL.getPriority(), AnimationState.ROLL);
        map.put(AnimationState.DEFEND.getPriority(), AnimationState.DEFEND);
    }

    public AnimationState get() {
        if (priorityQueue.isEmpty())
            return null;
        var x = map.get(priorityQueue.poll());
        priorityQueue.clear();
        return x;
    }

    public void add(AnimationState animationState) {
        priorityQueue.add(animationState.getPriority());
    }
}
