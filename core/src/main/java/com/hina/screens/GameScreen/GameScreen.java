package com.hina.screens.GameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hina.GameManager;

import static com.hina.utils.Bin.clearBin;

public class GameScreen implements Screen {
    private final Game game;
    private World world;
    private final OrthographicCamera camera;
    private final FitViewport viewport;
    private Box2DDebugRenderer box2DDebugRenderer;
    private SpriteBatch batch;
    private GameManager gameManager;
    private final Vector2 playerSpawnPosition;
    private final String fileMapName;

    public GameScreen(Game game, FitViewport viewport, OrthographicCamera camera, Vector2 playerSpawnPosition, String fileMapName) {
        this.game = game;
        this.viewport = viewport;
        this.camera = camera;
        this.playerSpawnPosition = playerSpawnPosition;
        this.fileMapName = fileMapName;
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, -9.8f), true);
        batch = new SpriteBatch();
        box2DDebugRenderer = new Box2DDebugRenderer();

        gameManager = new GameManager(world, camera, playerSpawnPosition, fileMapName);
    }

    @Override
    public void render(float v) {
        update();
        draw();
    }

    private void update() {
        world.step(Gdx.graphics.getDeltaTime(), 6, 2); // Cập nhật vật lý
        clearBin(world);

        gameManager.update();
    }

    private void draw() {
        ScreenUtils.clear(0, 0, 0, 1); // Xóa màn hình đen

        viewport.apply();
        camera.position.set(gameManager.getHero().getPosition().x, gameManager.getHero().getPosition().y, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        gameManager.draw(batch, camera, viewport);

        batch.end();

        gameManager.renderHealthBat(camera);

        box2DDebugRenderer.render(world, camera.combined);
    }

    @Override
    public void resize(int i, int i1) {
        viewport.update(i, i1, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        world.dispose();
        batch.dispose();
        gameManager.dispose();
    }
}
