package com.hina.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hina.entities.Entity;

import static com.hina.constant.GameConst.PPM;

public class HealthBar {
    private final Entity entity;
    private final float entityWidth;
    private final float entityHeight;
    private final float maxHealth;
    private float x;
    private float y;
    private float curHealth;
    private float gap = 1;

    public HealthBar(Entity entity) {
        this.entity = entity;

        this.entityWidth = entity.getEntityWidth();  // Chiều rộng thực tế (vì Box2D dùng nửa chiều dài)
        this.entityHeight = entity.getEntityHeight(); // Chiều cao thực tế

        this.maxHealth = entity.getMaxHeath();
        this.curHealth = entity.getCurHealth();
    }

    private void setXY() {
        this.x = entity.getPosition().x - entityWidth / 2;
        this.y = entity.getPosition().y;
    }

    public void update() {
        setXY();
        curHealth = entity.getCurHealth();
    }

    public void setGap(float gap) {
        this.gap = gap;
    }

    public void render(ShapeRenderer shapeRenderer) {
        float border = 3 / PPM;
        float padding = 20 / PPM;
        float gap = this.gap / PPM;
        float healthBarHeight = 10f / PPM;
        float healthBatWidth = entityWidth + padding * 2;
        float healthBarX = x - padding;
        float healthBarY = y + entityHeight + gap;

        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(healthBarX - border, healthBarY - border,
            healthBatWidth + border * 2, healthBarHeight + border * 2);

        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(healthBarX, healthBarY,
            healthBatWidth * (curHealth / maxHealth), healthBarHeight);
    }
}
