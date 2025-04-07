package com.hina.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hina.Map;
import com.hina.entities.Player.Hero;
import com.hina.handleListener.MainListener;
import com.hina.screens.Background;
import com.hina.screens.GameScreen.GameScreen;


public class GameManager {
    private final HeroManager heroManager;
    private final Background background;
    private final Map map;
    private final ShapeRenderer shapeRenderer;
    private final BasicEnemyManager basicEnemyManager;
    private final BossManager bossManager;

    public GameManager(GameScreen gameScreen, OrthographicCamera camera, Vector2 playerSpawnPosition, String fileMapName) {
        this.heroManager = new HeroManager(gameScreen, playerSpawnPosition);
        this.background = new Background();
        this.shapeRenderer = new ShapeRenderer();
        this.map = new Map(camera, gameScreen.getWorld(), heroManager, fileMapName);
        this.basicEnemyManager = map.getBasicEnemyManager();
        this.bossManager = map.getBossManager();

        gameScreen.getWorld().setContactListener(new MainListener());
    }

    public void update() {
        float delta = Gdx.graphics.getDeltaTime();
        heroManager.update(delta);
        basicEnemyManager.update(delta);
        bossManager.update(delta);
    }

    public void draw(SpriteBatch batch, OrthographicCamera camera, FitViewport viewport) {
        background.draw(batch, camera, viewport);
        batch.end();

        map.render();

        batch.begin();
        basicEnemyManager.draw(batch);
        bossManager.draw(batch);
        heroManager.renderHealthBar(batch, camera, viewport);
        heroManager.draw(batch);
    }

    public void renderHealthBat(OrthographicCamera camera) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // viết ở đây
        basicEnemyManager.renderHealthBar(shapeRenderer);
        bossManager.renderHealthBar(shapeRenderer);

        shapeRenderer.end();
    }

    public Hero getHero() {
        return heroManager.getCurrentHero();
    }

    public void dispose() {
        heroManager.dispose();
        background.dispose();
        map.dispose();
        basicEnemyManager.dispose();
        bossManager.dispose();
    }
}
