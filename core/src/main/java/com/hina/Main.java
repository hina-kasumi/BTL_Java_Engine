package com.hina;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hina.entities.Player.Player;

import static com.hina.utils.Bin.*;


/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    private World world;
    private OrthographicCamera camera;
    private Player player;
    private Box2DDebugRenderer box2DDebugRenderer;
    private SpriteBatch batch;
    private FitViewport viewport;
    private GameManager gameManager;

    @Override
    public void create() {
        world = new World(new Vector2(0, -9.8f), true);
        camera = new OrthographicCamera();
        viewport = new FitViewport(32, 18, camera);
        batch = new SpriteBatch();
        box2DDebugRenderer = new Box2DDebugRenderer();

        player = new Player(world);
        gameManager = new GameManager(world, camera, player);
    }

    @Override
    public void render() {
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
        camera.position.set(player.getPosition().x, player.getPosition().y, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        gameManager.draw(batch, camera, viewport);

        batch.end();

        gameManager.renderHealthBat(batch, camera);

        box2DDebugRenderer.render(world, camera.combined);
    }

    @Override
    public void dispose() {
        world.dispose();
        batch.dispose();
        gameManager.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}
