package com.hina.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    protected final Vector2 position;
    protected Animation<TextureRegion> animation;
    protected float stateTime;
    protected final Rectangle hitbox;
    protected final ShapeRenderer shapeRenderer; // Dùng để vẽ hitbox
    protected final float scale;
    protected final float xOffset, yOffset;
    protected final float gravity = -0.5f;

    public Entity(float x, float y, float width, float height, float xOffset, float yOffset, float scale) {
        this.position = new Vector2(x, y);
        this.hitbox = new Rectangle(x, y, width * scale, height * scale);
        this.shapeRenderer = new ShapeRenderer();
        this.scale = scale;
        this.stateTime = 0;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public abstract void update(float delta);

    public abstract void draw(SpriteBatch batch);

    public void renderHitbox(Matrix4 projectionMatrix) {
        shapeRenderer.setProjectionMatrix(projectionMatrix); // Áp dụng viewport
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        shapeRenderer.end();
    }

    public abstract void dispose();
}
