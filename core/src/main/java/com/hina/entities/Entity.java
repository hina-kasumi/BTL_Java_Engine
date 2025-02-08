package com.hina.entities;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {


    public Entity() {

    }

    public abstract void update(float delta);

//    public abstract void draw(SpriteBatch batch);


    public abstract void dispose();
}
