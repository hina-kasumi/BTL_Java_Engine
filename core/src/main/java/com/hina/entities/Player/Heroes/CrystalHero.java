package com.hina.entities.Player.Heroes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.hina.entities.Player.Hero;

import static com.hina.constant.HeroConst.Crystal.*;

public class CrystalHero extends Hero {
    public CrystalHero(World world, Vector2 bornPosition) {
        super(world, bornPosition, HERO_MAX_HEALTH, SRC, OFFSET_Y);

        setBasicAttackAt(4, 5);
        setAttackBoxSize(ATTACK_BOX_BASE_ATTACK_WIDTH, ATTACK_BOX_BASE_ATTACK_HEIGHT);
        setBasicSpecialAttack(SPECIAL_ATTACK_WIDTH,
            SPECIAL_ATTACK_HEIGHT,
            SPECIAL_ATTACK_START_FRAME,
            SPECIAL_ATTACK_END_FRAME,
            SPECIAL_ATTACK_DAMAGE
        );

        setSpecialAttackBoxOffsetX(SPECIAL_ATTACK_OFFSET_X);
    }
}
