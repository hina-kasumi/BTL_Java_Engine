package com.hina.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hina.entities.Player.Hero;
import com.hina.entities.Player.Heroes.*;
import com.hina.screens.GameScreen.GameScreen;

import java.util.ArrayList;
import java.util.List;

public class HeroManager {
    private final List<Hero> heroes;
    private int currentHeroIndex;
    private Vector2 position;
    private boolean movingRight;

    public HeroManager(GameScreen gameScreen, Vector2 position) {
        heroes = new ArrayList<>();
        currentHeroIndex = 0;
        movingRight = true;

        this.position = position;

        add(new CrystalHero(gameScreen, position));
        add(new FireHero(gameScreen, position));
        add(new GroundHero(gameScreen, position));
        add(new LeafHero(gameScreen, position));
        add(new MetalHero(gameScreen, position));
        add(new WaterHero(gameScreen, position));
        add(new WindHero(gameScreen, position));

        heroes.forEach(hero -> hero.getBody().setActive(false));

    }

    public void add(Hero hero) {
        heroes.add(hero);
    }

    public void update(float delta) {
        int prevCurrentHero = currentHeroIndex;
        position = getCurrentHero().getPosition();
        this.movingRight = heroes.get(currentHeroIndex).isMovingRight();

        for (int i = Input.Keys.NUM_1; i < Input.Keys.NUM_1 + heroes.size(); i++) {
            if (Gdx.input.isKeyPressed(i)) {
                currentHeroIndex = i - Input.Keys.NUM_1;
            }
        }

        heroes.forEach(hero -> {
            if (!heroes.get(prevCurrentHero).isIdle()) {
                currentHeroIndex = prevCurrentHero;
            }
            if (hero == getCurrentHero()) {
                hero.getBody().setActive(true);
                hero.setPosition(position);
                hero.setMovingRight(movingRight);
            } else {
                hero.getBody().setActive(false);
            }
        });


        getCurrentHero().update(delta);
    }

    public void draw(SpriteBatch batch) {
        getCurrentHero().draw(batch);
    }

    public void renderHealthBar(SpriteBatch batch, Camera camera, FitViewport viewport) {
        getCurrentHero().renderPlayerHealthBar(batch, camera, viewport);
    }

    public void clear() {
        heroes.clear();
    }

    public Vector2 getPosition() {
        return position;
    }

    public Hero getCurrentHero() {
        return heroes.get(currentHeroIndex);
    }

    public void setCurrentHeroIndex(int currentHeroIndex) {
        if (currentHeroIndex < 0 || currentHeroIndex >= heroes.size())
            throw new IllegalArgumentException("truy cập ngoài mảng heroes!");
        this.currentHeroIndex = currentHeroIndex;
    }


    public void dispose() {
        heroes.forEach(Hero::dispose);
        clear();
    }
}
