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
    private int currentHero;
    private Vector2 position;

    public HeroManager(World world) {
        heroes = new ArrayList<>();
        currentHero = 0;

        position = new Vector2(0, 10);

        add(new CrystalHero(world, position));
        add(new FireHero(world, position));
        add(new GroundHero(world, position));
        add(new LeafHero(world, position));
        add(new MetalHero(world, position));
        add(new WaterHero(world, position));
        add(new WindHero(world, position));

//        getCurrentHero().destroyBody();
//        getCurrentHero().createBody(position, 0.15f);
        heroes.forEach(hero -> hero.getBody().setActive(false));

    }

    public void add(Hero hero) {
        heroes.add(hero);
    }

    public void update(float delta) {
        int prevCurrentHero = currentHero;
        position = getCurrentHero().getPosition();

        for (int i = Input.Keys.NUM_1; i < Input.Keys.NUM_1 + heroes.size(); i++) {
            if (Gdx.input.isKeyPressed(i)) {
                currentHero = i - Input.Keys.NUM_1;
            }
        }

        heroes.forEach(hero -> {
            if (!heroes.get(prevCurrentHero).isIdle()) {
                currentHero = prevCurrentHero;
            }
            if (hero == getCurrentHero()) {
                hero.getBody().setActive(true);
                hero.setPosition(position);
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
        return heroes.get(currentHero);
    }

    public void setCurrentHero(int currentHero) {
        if (currentHero < 0 || currentHero >= heroes.size())
            throw new IllegalArgumentException("truy cập ngoài mảng heroes!");
        this.currentHero = currentHero;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void dispose() {
        heroes.forEach(Hero::dispose);
        heroes.clear();
    }
}
