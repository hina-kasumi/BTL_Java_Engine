package com.hina.entities.enemy.BasicEnemy.Goblin;

import com.badlogic.gdx.physics.box2d.World;
import com.hina.entities.Player.HeroManager;
import com.hina.entities.enemy.BasicEnemy.BasicEnemy;

import static com.hina.constant.BasicMonsterConst.GoblinConst.*;

public class Goblin extends BasicEnemy {
    public Goblin(World world, HeroManager heroManager, float x, float y) {
        super(world, heroManager, x, y, GOBLIN_MAX_HEALTH);
        this.scale = GOBLIN_SCALE;

        createAnimation();
    }

    private void createAnimation() {
        idleAnimation = importAnimation(GoblinState.IDLE.getFileName());
        attackAnimation = importAnimation(GoblinState.ATTACK.getFileName());
        runAnimation = importAnimation(GoblinState.RUNNING.getFileName());
        takeHitAnimation = importAnimation(GoblinState.TAKE_HIT.getFileName());
        deathAnimation = importAnimation(GoblinState.DEATH.getFileName());

        setAttackTime(6);
    }

    @Override
    public void dispose() {

    }
}
