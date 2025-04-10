package com.hina.entities.enemy.BossEnemy.FireWorm;

import com.hina.manager.HeroManager;
import com.hina.entities.enemy.BossEnemy.BossEnemy;
import com.hina.screens.GameScreen.GameScreen;

import static com.hina.constant.BossMonsterConst.FireWorm.*;

public class FireWorm extends BossEnemy {
    public FireWorm(GameScreen gameScreen, HeroManager heroManager, float x, float y) {
        super(gameScreen, heroManager, x, y,BOSS_WIDTH, BOSS_HEIGHT, FIRE_WORM_MAX_HEALTH);
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
