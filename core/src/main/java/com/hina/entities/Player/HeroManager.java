package com.hina.entities.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hina.entities.Player.Heroes.*;

import java.util.ArrayList;
import java.util.List;

public class HeroManager {
    private final List<Hero> heroes;
    private int currentHeroIndex;
    private Vector2 position;
    private boolean movingRight;

    public HeroManager(World world) {
        heroes = new ArrayList<>();
        currentHeroIndex = 0;
        movingRight = true;

        position = new Vector2(0, 10);

        add(new CrystalHero(world, position));
        add(new FireHero(world, position));
        add(new GroundHero(world, position));
        add(new LeafHero(world, position));
        add(new MetalHero(world, position));
        add(new WaterHero(world, position));
        add(new WindHero(world, position));

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
