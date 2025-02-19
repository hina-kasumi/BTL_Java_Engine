package com.hina.entities.Player;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static com.hina.constant.GameConst.PPM;

public class PlayerHealthBar {
    private final Hero hero;
    private final float healthBarWidth;
    private final float healthBarHeight;
    private final float maxHealth;
    private float curHealth;
    private Texture noHeartFull;
    private Texture heartEmpty;

    public PlayerHealthBar(Hero hero) {
        this.hero = hero;

        this.maxHealth = hero.getMaxHeath();
        this.curHealth = hero.getMaxHeath();

        String path = "textures/Player health bar/";
        this.noHeartFull = new Texture(path + "standard-1.png");
        this.heartEmpty = new Texture(path + "standard-empty-1.png");

        this.healthBarWidth = noHeartFull.getWidth() / PPM;
        this.healthBarHeight = noHeartFull.getHeight() / PPM;
    }

    public void update() {
        curHealth = hero.getCurHealth();
    }

    public void render(SpriteBatch batch, Camera camera, FitViewport viewport) {
        float healthPercent = curHealth / maxHealth;

        // xử lý cắt ảnh xóa lấy phần dư
        float offsetX = 23;
        Sprite healthSprite = new Sprite(noHeartFull, (int) offsetX, 0,
            (int) (noHeartFull.getWidth() - offsetX), noHeartFull.getHeight());

        healthSprite = new Sprite(healthSprite, 0, 0,
            (int) (healthSprite.getWidth() * healthPercent), (int) healthSprite.getHeight());

        final float scale = 7f;
        final float pixelScale = scale / PPM;
        float width = healthBarWidth * scale;
        float height = healthBarHeight * scale;
        float x = camera.position.x - viewport.getWorldWidth() / 2;
        float y = camera.position.y + viewport.getWorldHeight() / 2 - height;

        // vẽ cục máu
        batch.draw(healthSprite, x + offsetX * pixelScale, y,
            healthSprite.getWidth() * pixelScale, height);

        //vẽ cái chứa tim
        batch.draw(heartEmpty, x, y, width, height);
    }

    public void dispose() {
        heartEmpty.dispose();
        noHeartFull.dispose();
    }
}
