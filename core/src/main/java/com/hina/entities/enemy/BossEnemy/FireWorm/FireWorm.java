package com.hina.entities.enemy.BossEnemy.FireWorm;

import com.badlogic.gdx.physics.box2d.World;
import com.hina.entities.Player.Hero;
import com.hina.entities.Player.HeroManager;
import com.hina.entities.enemy.BossEnemy.BossEnemy;
import static com.hina.constant.BossMonsterConst.FireWorm.*;

public class FireWorm extends BossEnemy {
    public FireWorm(World world, HeroManager heroManager, float x, float y) {
        super(world, heroManager, x, y, FIRE_WORM_MAX_HEALTH);
        this.scale = FIRE_WORM_SCALE;

        createAnimation();
    }

    private void createAnimation() {
        idleAnimation = importAnimation(FireWormState.IDLE.getFileName());
        attackAnimation = importAnimation(FireWormState.ATTACK.getFileName());
        runAnimation = importAnimation(FireWormState.RUNNING.getFileName());
        takeHitAnimation = importAnimation(FireWormState.TAKE_HIT.getFileName());
        deathAnimation = importAnimation(FireWormState.DEATH.getFileName());
    }
}
