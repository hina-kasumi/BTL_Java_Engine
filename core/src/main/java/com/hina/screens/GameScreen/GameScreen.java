package com.hina.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.hina.manager.GameManager;
import com.hina.screens.InnerScreens.WinGameScreen;
import com.hina.ui.CoinDisplay;
import com.hina.screens.InnerScreens.GameOverScreen;
import com.hina.screens.InnerScreens.PauseManager;
import com.hina.screens.ScreenAbstract;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.hina.constant.GameConst.*;
import static com.hina.constant.GameScreenConst.*;
import static com.hina.utils.Bin.clearBin;

public abstract class GameScreen extends ScreenAbstract {
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private SpriteBatch batch;
    private GameManager gameManager;
    protected final Vector2 playerSpawnPosition;
    private final String fileMapName;
    private PauseManager pauseManager;
    private GameOverScreen gameOverScreen;
    private WinGameScreen winGameScreen;
    private CoinDisplay coinDisplay;
    private int level;

    public GameScreen(ScreenAbstract screenAbstract, Vector2 playerSpawnPosition, String fileMapName) {
        super(screenAbstract);
        this.playerSpawnPosition =
            new Vector2(playerSpawnPosition.x * MAP_SCALE, playerSpawnPosition.y * MAP_SCALE);
        this.fileMapName = fileMapName;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(fileMapName);
        while (matcher.find()) {
            level = Integer.parseInt(matcher.group());
        }
    }

    @Override
    public void show() {
        world = new World(new Vector2(0, GRAVITY), true);
        batch = new SpriteBatch();
        box2DDebugRenderer = new Box2DDebugRenderer();
        pauseManager = new PauseManager(this);
        gameOverScreen = new GameOverScreen(this);
        winGameScreen = new WinGameScreen(this);
        coinDisplay = new CoinDisplay(this);

        gameManager = new GameManager(this, camera, playerSpawnPosition, fileMapName);
    }

    @Override
    public void render(float v) {
        update();
        draw(v);
    }

    private void update() {
        world.step(Gdx.graphics.getDeltaTime(), 6, 2); // Cập nhật vật lý
        clearBin(world);

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            CHEAT = !CHEAT;
            System.out.println(CHEAT ? "switch to cheat mode" : "switch to play mode");
        }
        if (winGameScreen.isWinGame()) {
            winGameScreen.update();
            return;
        }
        if (gameOverScreen.isGameOver()) {
            gameOverScreen.update();
            return;
        }
        if (!pauseManager.isPaused())
            gameManager.update();

        coinDisplay.update();
    }

    private void draw(float v) {
        ScreenUtils.clear(0, 0, 0, 1); // Xóa màn hình đen

        viewport.apply();
        camera.position.set(gameManager.getHero().getPosition().x, gameManager.getHero().getPosition().y, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        gameManager.draw(batch, camera, viewport);

        batch.end();

        gameManager.renderHealthBat(camera);

        if (winGameScreen.isWinGame()) {
            winGameScreen.draw(v);
        } else if (gameOverScreen.isGameOver()) {
            gameOverScreen.draw(v);
        } else {
            pauseManager.draw(v);
            coinDisplay.draw(v, COIN_SCALE);
        }

        if (isDEBUG())
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
        pauseManager.dispose();
        world.dispose();
        batch.dispose();
        gameManager.dispose();
        coinDisplay.dispose();
    }

    public Vector2 getPlayerSpawnPosition() {
        return new Vector2(
            playerSpawnPosition.x / MAP_SCALE,
            playerSpawnPosition.y / MAP_SCALE
        );
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public String getFileMapName() {
        return fileMapName;
    }

    public GameOverScreen getGameOverScreen() {
        return gameOverScreen;
    }

    public World getWorld() {
        return world;
    }

    public String getLevel() {
        return ((level < 10) ? "0" : "") + level;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager.dispose();
        this.gameManager = gameManager;
    }

    public WinGameScreen getWinGameScreen() {
        return winGameScreen;
    }
}
