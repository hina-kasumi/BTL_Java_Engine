package com.hina.screens.GameScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.hina.manager.GameManager;
import com.hina.manager.HeroManager;
import com.hina.screens.GameOverScreen;
import com.hina.screens.PauseManager;
import com.hina.screens.ScreenAbstract;
import com.hina.ui.CoinDisplay;

import static com.hina.constant.GameScreenConst.GRAVITY;

public class BossGameScreen extends GameScreen {
    private final ScreenAbstract screenAbstract;
    private final HeroManager heroManager;

    public BossGameScreen(ScreenAbstract screenAbstract, Vector2 playerSpawnPosition, String fileMapName) {
        super(screenAbstract, playerSpawnPosition, fileMapName);
        this.screenAbstract = screenAbstract;
        this.heroManager = null;
    }

    public BossGameScreen(ScreenAbstract screenAbstract, HeroManager heroManager, Vector2 playerSpawnPosition, String fileMapName) {
        super(screenAbstract, playerSpawnPosition, fileMapName);
        this.screenAbstract = screenAbstract;
        this.heroManager = heroManager;
    }

    @Override
    public void show() {
        super.show();

        if (heroManager != null) {
            setGameManager(new GameManager(
                this, camera,
                new HeroManager(this, heroManager, playerSpawnPosition),
                getFileMapName()));
        }
    }

    public ScreenAbstract getScreenAbstract() {
        return screenAbstract;
    }
}
