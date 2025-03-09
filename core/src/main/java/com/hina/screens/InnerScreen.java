package com.hina.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hina.ButtonClick.ButtonList;

public abstract class InnerScreen {
    protected final ScreenAbstract screen;
    protected final FitViewport viewport;
    protected final OrthographicCamera camera;
    protected Stage stage;
    protected ButtonList buttons;
    private final ShapeRenderer shapeRenderer;

    public InnerScreen(ScreenAbstract screen) {
        this.screen = screen;
        this.viewport = screen.getViewport();
        this.camera = screen.getCamera();
        this.stage = new Stage(viewport);
        this.buttons = new ButtonList();
        this.shapeRenderer = new ShapeRenderer();

        initButton();

        buttons.stageAddActor(stage);
    }

    protected void drawShadowBackground() {
        Gdx.gl.glEnable(Gdx.gl.GL_BLEND);
        Gdx.gl.glBlendFunc(Gdx.gl.GL_SRC_ALPHA, Gdx.gl.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 0.5f); // Màu đen mờ 50%
        shapeRenderer.rect(
            camera.position.x - viewport.getWorldWidth() / 2,
            camera.position.y - viewport.getWorldHeight() / 2,
            viewport.getWorldWidth(),
            viewport.getWorldHeight());
        shapeRenderer.end();

        Gdx.gl.glDisable(Gdx.gl.GL_BLEND);
    }

    protected abstract void initButton();

    public abstract void update();
    public abstract void draw(float v);

    public void dispose() {
        stage.dispose();
        buttons.dispose();
        abstractDispose();
    }

    protected abstract void abstractDispose();
}
