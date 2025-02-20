package com.hina.utils.attackUtils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import static com.hina.utils.Bin.bodiesToDestroy;

public abstract class AttackAbstract {
    protected Body hitBox;
    protected Body entity;
    protected final World world;
    protected float damage;

    public AttackAbstract(Body entity) {
        this.world = entity.getWorld();
    }

    public void destroyAttackSensor() {
        if (hitBox != null) {
            bodiesToDestroy.add(hitBox);
            hitBox = null;
        }
    }

    public boolean isDestroyed() {
        return hitBox == null;
    }

    public Body getEntity() {
        return entity;
    }

    public float getDamage() {
        return damage;
    }
}
