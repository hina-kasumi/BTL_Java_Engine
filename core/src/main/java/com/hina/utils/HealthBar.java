package com.hina.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hina.entities.enemy.BasicEnemy.BasicEnemy;

import static com.hina.constant.GameConst.PPM;

public class HealthBar {
    private final BasicEnemy basicEnemy;
    private final ShapeRenderer shapeRenderer;
    private final float entityWidth;
    private final float entityHeight;
    private final float maxHealth;
    private float x;
    private float y;
    private float curHealth;

    public HealthBar(BasicEnemy basicEnemy) {
        this.basicEnemy = basicEnemy;

        this.entityWidth = basicEnemy.getEntityWidth();  // Chiều rộng thực tế (vì Box2D dùng nửa chiều dài)
        this.entityHeight = basicEnemy.getEntityHeight(); // Chiều cao thực tế
        System.out.println(entityWidth + " " + entityHeight);

        this.maxHealth = basicEnemy.getMaxHeath();
        this.curHealth = basicEnemy.getCurHealth();

        this.shapeRenderer = new ShapeRenderer();
    }

    private void setXY() {
        this.x = basicEnemy.getPosition().x - entityWidth / 2;
        this.y = basicEnemy.getPosition().y;
    }

    public void update() {
        setXY();
        curHealth = basicEnemy.getCurHealth();
    }

    public void render(OrthographicCamera camera) {
        float border = 3 / PPM;
        float padding = 20 / PPM;
        float gap = 1 / PPM;
        float healthBarHeight = 10f / PPM;
        float healthBatWidth = entityWidth + padding * 2;
        float healthBarX = x - padding;
        float healthBarY = y + entityHeight + gap;

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(healthBarX - border, healthBarY - border,
            healthBatWidth + border * 2, healthBarHeight + border * 2);

        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(healthBarX, healthBarY,
            healthBatWidth * (curHealth / maxHealth), healthBarHeight);

        shapeRenderer.end();
    }
}
