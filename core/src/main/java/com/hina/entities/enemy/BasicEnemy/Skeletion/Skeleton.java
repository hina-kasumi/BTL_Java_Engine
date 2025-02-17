package com.hina.entities.enemy.BasicEnemy.Skeletion;

import com.badlogic.gdx.physics.box2d.World;
import com.hina.entities.Player.Player;
import com.hina.entities.enemy.BasicEnemy.BasicEnemy;

import static com.hina.constant.GameConst.SKELETON_MAX_HEALTH;

public class Skeleton extends BasicEnemy {
    public Skeleton(World world, Player player, float x, float y) {
        super(world, player, x, y, SKELETON_MAX_HEALTH);
        this.scale = 3.5f;

        createAnimation();
    }

    private void createAnimation() {
        idleAnimation = importAnimation(SkeletonState.IDLE.getFileName());
        attackAnimation = importAnimation(SkeletonState.ATTACK.getFileName());
        runAnimation = importAnimation(SkeletonState.RUNNING.getFileName());
        takeHitAnimation = importAnimation(SkeletonState.TAKE_HIT.getFileName());
        deathAnimation = importAnimation(SkeletonState.DEATH.getFileName());

        setAttackTime(6);
    }

    @Override
    public void dispose() {

    }
}
