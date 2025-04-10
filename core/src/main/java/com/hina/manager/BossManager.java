package com.hina.manager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hina.entities.Entity;
import com.hina.entities.enemy.BossEnemy.BossEnemy;
import com.hina.screens.GameScreen.BossGameScreen;
import com.hina.screens.GameScreen.GameScreen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BossManager {
    private final List<BossEnemy> bossEnemies;
    private final GameScreen gameScreen;

    public BossManager(GameScreen gameScreen, HeroManager heroManager) {
        this.bossEnemies = new ArrayList<>();
        this.gameScreen = gameScreen;
    }

    public void add(BossEnemy bossEnemy) {
        bossEnemies.add(bossEnemy);
    }

    public void update(float delta) {
        Iterator<BossEnemy> iterator = bossEnemies.iterator();
        while (iterator.hasNext()) {
            BossEnemy bossEnemy = iterator.next();
            bossEnemy.update(delta);
            if (bossEnemy.isDeath()) {
                iterator.remove();
            }
        }

        if (bossEnemies.isEmpty() && gameScreen instanceof BossGameScreen) {
            gameScreen.getWinGameScreen().setWinGame(true);
        }
    }

    public void draw(SpriteBatch batch) {
        bossEnemies.forEach(bossEnemy -> bossEnemy.draw(batch));
    }

    public void clear() {
        bossEnemies.clear();
    }

    public void renderHealthBar(ShapeRenderer shapeRenderer) {
        bossEnemies.forEach(bossEnemy -> bossEnemy.renderHealthBar(shapeRenderer));
    }

    public void dispose() {
        bossEnemies.forEach(Entity::dispose);
        clear();
    }
}
