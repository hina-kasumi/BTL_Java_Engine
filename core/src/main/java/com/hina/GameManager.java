package com.hina;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hina.entities.Player.Player;
import com.hina.entities.enemy.BasicEnemy.Goblin.Goblin;
import com.hina.handleListener.MainListener;
import com.hina.screens.Background;


public class GameManager {
    private Player player;
    private Goblin goblin;
    private Background background;
    private Map map;
    public static boolean isGameStop;

    public GameManager(World world, OrthographicCamera camera, Player player) {
        map = new Map(camera, world);
        this.player = player;
        goblin = new Goblin(world, player, 10, 10);
        background = new Background();

        world.setContactListener(new MainListener());
    }

    public void update() {
        float delta = Gdx.graphics.getDeltaTime();
        player.update(delta);
        goblin.update(delta);
    }

    public void draw(SpriteBatch batch, OrthographicCamera camera, FitViewport viewport) {
        background.draw(batch, camera, viewport);
        goblin.draw(batch);
        player.draw(batch);
        map.render();
    }

    public void renderHealthBat(SpriteBatch batch, OrthographicCamera camera){
        goblin.renderHealthBar(camera);

    }

    public void dispose() {
        player.dispose();
        background.dispose();
        map.dispose();
    }
}
