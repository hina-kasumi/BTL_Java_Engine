package com.hina.entities.enemy.BasicEnemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.hina.entities.Entity;
import com.hina.entities.Player.Player;
import com.hina.entities.enemy.BasicEnemy.Mushroom.Mushroom;

import java.util.ArrayList;
import java.util.List;

public class BasicEnemyManager {
    private final List<BasicEnemy> basicEnemies;

    public BasicEnemyManager(World world, Player player) {
        basicEnemies = new ArrayList<>();

//        add(new Mushroom(world, player, 10, 10));
    }

    public void add(BasicEnemy basicEnemy) {
        basicEnemies.add(basicEnemy);
    }

    public void update(float delta) {
        basicEnemies.forEach(basicEnemy -> basicEnemy.update(delta));
    }

    public void draw(SpriteBatch batch) {
        basicEnemies.forEach(basicEnemy -> basicEnemy.draw(batch));
    }

    public void clear() {
        basicEnemies.clear();
    }

    public void renderHealthBar(ShapeRenderer shapeRenderer) {
        basicEnemies.forEach(basicEnemy -> basicEnemy.renderHealthBar(shapeRenderer));
    }

    public void dispose() {
        basicEnemies.forEach(Entity::dispose);
        clear();
    }
}
