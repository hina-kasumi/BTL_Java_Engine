package com.hina.entities.enemy.BossEnemy.DemonSlime;

import com.badlogic.gdx.physics.box2d.World;
import com.hina.entities.enemy.BossEnemy.BossEnemy;
import com.hina.manager.HeroManager;

import static com.hina.constant.BossMonsterConst.DemonSlime.*;
import static com.hina.utils.ImportTextureUtil.newImportAnimation;

public class DemonSlime extends BossEnemy {
    public DemonSlime(World world, HeroManager heroManager, float x, float y) {
        super(world, heroManager, x, y, BOSS_WIDTH, BOSS_HEIGHT, DEMON_SLIME_MAX_HEALTH);
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
