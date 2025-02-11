package com.hina.entities.enemy.Goblin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.hina.entities.Entity;

import static com.hina.constant.GameConst.PPM;

public class Goblin extends Entity {
    private Animation<TextureRegion> idleAnimation;
    private float stateTime = 0;
    private boolean movingRight = true;

    public Goblin(World world) {
        super(world, 10, 10, 0.5f, 1f, 1.5f);
        body.setGravityScale(5);
        body.setUserData("enemy");

        createAnimation();
    }

    private void createAnimation() {
        idleAnimation = importAnimation(GoblinState.IDLE);

        stateTime = 0;
    }

    private Animation<TextureRegion> importAnimation(GoblinState goblinState) {
        Texture texture = new Texture(goblinState.getFileName());
        TextureRegion[][] textureRegions = TextureRegion
            .split(texture, texture.getWidth() / goblinState.getFrameNumber(), texture.getHeight());

        Array<TextureRegion> array = new Array<>();
        for (int i = 0; i < textureRegions[0].length; i++) {
            array.add(textureRegions[0][i]);
        }

        return new Animation<>(0.1f, array, Animation.PlayMode.LOOP);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = idleAnimation.getKeyFrame(stateTime, true);

        if (!movingRight && !currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        } else if (movingRight && currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        }

        batch.draw(currentFrame,
            body.getPosition().x - scale * currentFrame.getRegionWidth() / 2 / PPM,
            body.getPosition().y - scale * currentFrame.getRegionHeight() / 2 / PPM,
            currentFrame.getRegionWidth() * scale / PPM,
            currentFrame.getRegionHeight() * scale / PPM
        );
    }

    @Override
    public void dispose() {

    }
}
