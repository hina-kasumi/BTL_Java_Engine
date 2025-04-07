package com.hina.entities.enemy.BossEnemy.FireWorm;

import com.badlogic.gdx.physics.box2d.World;
import com.hina.manager.HeroManager;
import com.hina.entities.enemy.BossEnemy.BossEnemy;

import static com.hina.constant.BossMonsterConst.FireWorm.*;

public class FireWorm extends BossEnemy {
    public FireWorm(World world, HeroManager heroManager, float x, float y) {
        super(world, heroManager, x, y,BOSS_WIDTH, BOSS_HEIGHT, FIRE_WORM_MAX_HEALTH);
        this.scale = FIRE_WORM_SCALE;
        setAttackTime(ATTACK_START_AT, ATTACK_END_AT);
        setBasicAttackDamage(BOSS_MONSTER_ATTACK_BOX_DAMAGE);
        setBasicAttackBoxSize(BOSS_MONSTER_ATTACK_BOX_WIDTH, BOSS_MONSTER_ATTACK_BOX_HEIGHT);
        setAttackArea(BOSS_MONSTER_ATTACK_AREA);
        setExcessPixels(EXCESS_PIXELS);

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
