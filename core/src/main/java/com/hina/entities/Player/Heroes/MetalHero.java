package com.hina.entities.Player.Heroes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.hina.entities.Player.Hero;

import static com.hina.constant.HeroConst.Metal.*;

public class MetalHero extends Hero {
    public MetalHero(World world, Vector2 bornPosition) {
        super(world, bornPosition, HERO_MAX_HEALTH, SRC, OFFSET_Y);

        setBasicAttackAt(4, 5);
        setAttackBoxSize(ATTACK_BOX_BASE_ATTACK_WIDTH, ATTACK_BOX_BASE_ATTACK_HEIGHT);
    }
}
