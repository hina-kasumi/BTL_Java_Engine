package com.hina.entities.enemy.BasicEnemy.Mushroom;

import com.badlogic.gdx.physics.box2d.World;
import com.hina.entities.Player.Player;
import com.hina.entities.enemy.BasicEnemy.BasicEnemy;

import static com.hina.constant.BasicMonsterConst.MushroomConst.*;

public class Mushroom extends BasicEnemy {
    public Mushroom(World world, Player player, float x, float y) {
        super(world, player, x, y, MUSHROOM_MAX_HEALTH);
        this.scale = MUSHROOM_SCALE;

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
