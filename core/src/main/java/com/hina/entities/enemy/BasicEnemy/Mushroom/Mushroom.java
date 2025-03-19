package com.hina.entities.enemy.BasicEnemy.Mushroom;

import com.badlogic.gdx.physics.box2d.World;
import com.hina.manager.HeroManager;
import com.hina.entities.enemy.BasicEnemy.BasicEnemy;

import static com.hina.constant.BasicMonsterConst.MushroomConst.*;

public class Mushroom extends BasicEnemy {
    public Mushroom(World world, HeroManager heroManager, float x, float y) {
        super(world, heroManager, x, y, MUSHROOM_MAX_HEALTH);
        this.scale = MUSHROOM_SCALE;
        setAttackAreaWidth(ATTACK_AREA);

        createAnimation();
    }

    private void createAnimation() {
        idleAnimation = importAnimation(MushroomState.IDLE.getFileName());
        attackAnimation = importAnimation(MushroomState.ATTACK.getFileName());
        runAnimation = importAnimation(MushroomState.RUNNING.getFileName());
        takeHitAnimation = importAnimation(MushroomState.TAKE_HIT.getFileName());
        deathAnimation = importAnimation(MushroomState.DEATH.getFileName());

        setAttackTime(6);
    }

    @Override
    public void dispose() {

    }
}
