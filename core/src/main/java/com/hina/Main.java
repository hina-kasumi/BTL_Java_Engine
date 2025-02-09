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
import com.hina.handleListener.MainListener;
import com.hina.screens.Background;


/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    private World world;
    private OrthographicCamera camera;
    private Player player;
    private Box2DDebugRenderer box2DDebugRenderer;
    private SpriteBatch batch;
    private Background background;
    private FitViewport viewport;

    @Override
    public void create() {
        world = new World(new Vector2(0, -9.8f), true);
        camera = new OrthographicCamera();
        viewport = new FitViewport(32, 18, camera);
        batch = new SpriteBatch();
        box2DDebugRenderer = new Box2DDebugRenderer();


        player = new Player(world);
        background = new Background();

        world.setContactListener(new MainListener(player));

        makeGround();
    }

    private void makeGround() {
        BodyDef groundDef = new BodyDef();
        groundDef.type = BodyDef.BodyType.StaticBody;
        groundDef.position.set(10, 0);
        Body ground = world.createBody(groundDef);
        ground.setUserData("ground");

        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(10, 1);
        ground.createFixture(groundShape, 0);
        groundShape.dispose();

        BodyDef blockDef = new BodyDef();
        blockDef.type = BodyDef.BodyType.StaticBody;
        blockDef.position.set(10, 1);
        Body block = world.createBody(blockDef);
        block.setUserData("block");
        PolygonShape blockShape = new PolygonShape();
        blockShape.setAsBox(2, 2);
        block.createFixture(blockShape, 0);
        blockShape.dispose();
    }

    @Override
    public void render() {
        update();
        draw();
    }

    private void update() {
        world.step(1 / 60f, 6, 2); // Cập nhật vật lý
        player.update(Gdx.graphics.getDeltaTime());
    }

    private void draw() {
        ScreenUtils.clear(0, 0, 0, 1); // Xóa màn hình đen

        viewport.apply();
        camera.position.set(player.getPosition().x, player.getPosition().y, 0);
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        background.draw(batch);
        player.draw(batch);

        batch.end();

        box2DDebugRenderer.render(world, camera.combined);
    }

    @Override
    public void dispose() {
        world.dispose();
        batch.dispose();
        player.dispose();
        background.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}
