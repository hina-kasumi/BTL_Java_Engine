package com.hina.entities.enemy.BossEnemy.DemonSlime;

import com.hina.entities.enemy.BossEnemy.BossEnemy;
import com.hina.manager.HeroManager;
import com.hina.screens.GameScreen.GameScreen;

import static com.hina.constant.BossMonsterConst.DemonSlime.*;
import static com.hina.utils.ImportTextureUtil.newImportAnimation;

public class DemonSlime extends BossEnemy {
    public DemonSlime(GameScreen gameScreen, HeroManager heroManager, float x, float y) {
        super(gameScreen, heroManager, x, y, BOSS_WIDTH, BOSS_HEIGHT, DEMON_SLIME_MAX_HEALTH);
        this.scale = DEMON_SLIME_SCALE;

        setBasicAttackDamage(BOSS_MONSTER_ATTACK_BOX_DAMAGE);
        setBasicAttackBoxSize(BOSS_MONSTER_ATTACK_BOX_WIDTH, BOSS_MONSTER_ATTACK_BOX_HEIGHT);
        setAttackArea(BOSS_MONSTER_ATTACK_AREA);
        setAttackTime(ATTACK_START_AT, ATTACK_END_AT);

        createAnimation();
    }

    private void createAnimation() {
        idleAnimation = newImportAnimation(DemonSlimeState.IDLE.getPath());
        attackAnimation = newImportAnimation(DemonSlimeState.ATTACK.getPath());
        runAnimation = newImportAnimation(DemonSlimeState.RUNNING.getPath());
        takeHitAnimation = newImportAnimation(DemonSlimeState.TAKE_HIT.getPath());
        deathAnimation = newImportAnimation(DemonSlimeState.DEATH.getPath());
    }

}
