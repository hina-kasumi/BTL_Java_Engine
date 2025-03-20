package com.hina.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hina.ui.MyPanel.CoinPanel;
import com.hina.screens.ScreenAbstract;

import static com.hina.manager.CoinManager.getCoin;


public class CoinDisplay {
    private final CoinPanel coinPanel;
    private final Stage stage;
    private final FitViewport viewport;
    private final OrthographicCamera camera;
    private final MyText myText;
    private final SpriteBatch batch;

    public CoinDisplay(ScreenAbstract screenAbstract) {
        coinPanel = new CoinPanel();
        viewport = screenAbstract.getViewport();
        camera = screenAbstract.getCamera();
        stage = new Stage(viewport);
        batch = new SpriteBatch();
        myText = new MyText();
        coinPanel.setScale(8);
        stage.addActor(coinPanel.getImage());

    }

    public void update() {

    }

    public void draw(float v, float scale) {
        Vector2 position = new Vector2(
            camera.position.x - viewport.getWorldWidth() / 2,
            camera.position.y - viewport.getWorldHeight() / 2);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        myText.drawText(batch, position.x + coinPanel.getWidth(), position.y, getCoin(), scale);

        batch.end();

        coinPanel.setPosition(position.x, position.y);

        stage.act(v);
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
        coinPanel.dispose();
        myText.dispose();
    }
}
