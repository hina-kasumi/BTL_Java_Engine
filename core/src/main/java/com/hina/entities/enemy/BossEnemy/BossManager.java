package com.hina.entities.enemy.BossEnemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.hina.entities.Entity;
import com.hina.entities.Player.HeroManager;
import com.hina.entities.enemy.BossEnemy.FireWorm.FireWorm;

import java.util.ArrayList;
import java.util.List;

public class BossManager {
    private final List<BossEnemy> bossEnemies;

    public BossManager(World world, HeroManager heroManager) {
        this.bossEnemies = new ArrayList<>();

        add(new FireWorm(world, heroManager, 10, 10));
    }

    public void add(BossEnemy bossEnemy) {
        bossEnemies.add(bossEnemy);
    }

    public void update(float delta) {
        bossEnemies.forEach(bossEnemy -> bossEnemy.update(delta));
    }

    public void draw(SpriteBatch batch) {
        bossEnemies.forEach(bossEnemy -> bossEnemy.draw(batch));
    }

    public void clear() {
        bossEnemies.clear();
    }

    public void renderHealthBar(ShapeRenderer shapeRenderer) {
//        bossEnemies.forEach(bossEnemy -> bossEnemy.renderHealthBar(shapeRenderer));
    }

    public void dispose() {
        bossEnemies.forEach(Entity::dispose);
        clear();
    }
}
