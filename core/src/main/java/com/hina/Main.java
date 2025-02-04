package com.hina;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hina.entities.Player.Player;
import com.hina.screens.Background;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Player player;
    private FitViewport viewport;
    private Background background;

    @Override
    public void create() {
        batch = new SpriteBatch();
        player = new Player(0, 0);
        viewport = new FitViewport(1280, 720);
        background = new Background();
    }

    @Override
    public void render() {
        update();
        draw();
    }

    private void update() {
        float delta = Gdx.graphics.getDeltaTime();

        player.update(delta);
    }

    private void draw() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        background.draw(batch, viewport.getWorldWidth(), viewport.getWorldHeight());
        player.draw(batch);

        batch.end();

        drawHitbox();
    }

    private void drawHitbox(){
        player.renderHitbox(viewport.getCamera().combined);
    }

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
        background.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
}
