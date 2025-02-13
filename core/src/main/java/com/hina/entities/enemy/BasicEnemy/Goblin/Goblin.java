package com.hina.entities.enemy.BasicEnemy.Goblin;

import com.badlogic.gdx.physics.box2d.World;
import com.hina.entities.Player.Player;
import com.hina.entities.enemy.BasicEnemy.BasicEnemy;

public class Goblin extends BasicEnemy {
    public Goblin(World world, Player player, float x, float y) {
        super(world, player, x, y, 50f);
        this.scale = 4;

        createAnimation();
    }

    private void createAnimation() {
        idleAnimation = importAnimation(GoblinState.IDLE.getFileName());
        attackAnimation = importAnimation(GoblinState.ATTACK.getFileName());
        runAnimation = importAnimation(GoblinState.RUNNING.getFileName());
        takeHitAnimation = importAnimation(GoblinState.TAKE_HIT.getFileName());
        deathAnimation = importAnimation(GoblinState.DEATH.getFileName());
    }

    @Override
    public void dispose() {

    }
}
