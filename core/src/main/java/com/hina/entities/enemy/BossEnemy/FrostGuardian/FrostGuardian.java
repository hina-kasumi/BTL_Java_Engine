package com.hina.entities.enemy.BossEnemy.FrostGuardian;


import com.badlogic.gdx.physics.box2d.World;
import com.hina.entities.enemy.BossEnemy.BossEnemy;
import com.hina.manager.HeroManager;

import static com.hina.constant.BossMonsterConst.FrostGuardian.*;
import static com.hina.utils.ImportTextureUtil.newImportAnimation;

public class FrostGuardian extends BossEnemy {

    public FrostGuardian(World world, HeroManager heroManager, float x, float y) {
        super(world, heroManager, x, y, BOSS_WIDTH, BOSS_HEIGHT, FROST_GUARDIAN_MAX_HEALTH);
        this.scale = FROST_GUARDIAN_SCALE;

        setBasicAttackDamage(BOSS_MONSTER_ATTACK_BOX_DAMAGE);
        setBasicAttackBoxSize(BOSS_MONSTER_ATTACK_BOX_WIDTH, BOSS_MONSTER_ATTACK_BOX_HEIGHT);
        setAttackArea(BOSS_MONSTER_ATTACK_AREA);
        setExcessPixels(EXCESS_PIXELS);
        setAttackTime(ATTACK_START_AT, ATTACK_END_AT);

        createAnimation();
    }

    private void createAnimation() {
        idleAnimation = newImportAnimation(FrostGuardianState.IDLE.getPath());
        attackAnimation = newImportAnimation(FrostGuardianState.ATTACK.getPath());
        runAnimation = newImportAnimation(FrostGuardianState.RUNNING.getPath());
        takeHitAnimation = newImportAnimation(FrostGuardianState.TAKE_HIT.getPath());
        deathAnimation = newImportAnimation(FrostGuardianState.DEATH.getPath());
    }
}
