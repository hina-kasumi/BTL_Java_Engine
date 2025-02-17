package com.hina;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hina.entities.Player.Player;
import com.hina.entities.enemy.BasicEnemy.BasicEnemyManager;
import com.hina.handleListener.MainListener;
import com.hina.screens.Background;


public class GameManager {
    private Player player;
    private final Background background;
    private Map map;
    private ShapeRenderer shapeRenderer;
    private BasicEnemyManager basicEnemyManager;
    public static boolean isGameStop;

    public GameManager(World world, OrthographicCamera camera, Player player) {
        this.map = new Map(camera, world);
        this.player = player;
        this.background = new Background();
        this.shapeRenderer = new ShapeRenderer();
        this.basicEnemyManager = new BasicEnemyManager(world, player);

        world.setContactListener(new MainListener());
    }

    public void update() {
        float delta = Gdx.graphics.getDeltaTime();
        player.update(delta);
        basicEnemyManager.update(delta);
    }

    public void draw(SpriteBatch batch, OrthographicCamera camera, FitViewport viewport) {
        background.draw(batch, camera, viewport);
        basicEnemyManager.draw(batch);
        player.renderPlayerHealthBar(batch, camera, viewport);
        player.draw(batch);
        map.render();
    }

    public void renderHealthBat(OrthographicCamera camera) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        basicEnemyManager.renderHealthBar(shapeRenderer);

        shapeRenderer.end();
    }

    public void dispose() {
        player.dispose();
        background.dispose();
        map.dispose();
        basicEnemyManager.dispose();
    }
}
