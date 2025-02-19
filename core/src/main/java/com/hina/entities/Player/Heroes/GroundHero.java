package com.hina.entities.Player.Heroes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.hina.entities.Player.Hero;

import static com.hina.constant.HeroConst.Ground.*;

public class GroundHero extends Hero {
    public GroundHero(World world, Vector2 bornPosition) {
        super(world, bornPosition, HERO_MAX_HEALTH, SRC, OFFSET_Y);

        setAttackAt(4, 5);
        setAttackBoxSize(ATTACK_BOX_WIDTH, ATTACK_BOX_HEIGHT);
    }
}
