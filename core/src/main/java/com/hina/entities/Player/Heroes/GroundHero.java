package com.hina.entities.Player.Heroes;

import com.badlogic.gdx.math.Vector2;
import com.hina.entities.Player.Hero;
import com.hina.screens.GameScreen.GameScreen;

import static com.hina.constant.HeroConst.Ground.*;

public class GroundHero extends Hero {
    public GroundHero(GameScreen gameScreen, Vector2 bornPosition) {
        super(gameScreen, bornPosition, HERO_MAX_HEALTH, SRC, OFFSET_Y);

        setBasicAttackAt(BASIC_ATTACK_START_AT, BASIC_ATTACK_END_AT);
        setAttackBoxSize(ATTACK_BOX_BASE_ATTACK_WIDTH, ATTACK_BOX_BASE_ATTACK_HEIGHT);

        setMultiHitInSpecialAttack(SPECIAL_ATTACK_TURNS,
            SPECIAL_ATTACK_WIDTH, SPECIAL_ATTACK_HEIGHT, SPECIAL_ATTACK_DAMAGE);

        setSpecialAttackBoxOffsetX(SPECIAL_ATTACK_OFFSET_X);
        setDefendAnimationAt(DEFEND_AT);
        setHeroImage(HERO_IMAGE_SRC);
    }
}
