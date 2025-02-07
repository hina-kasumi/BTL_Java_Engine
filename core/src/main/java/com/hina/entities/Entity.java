package com.hina.entities;


public abstract class Entity {


    public Entity() {

    }

    public abstract void update(float delta);

    public abstract void draw();


    public abstract void dispose();
}
