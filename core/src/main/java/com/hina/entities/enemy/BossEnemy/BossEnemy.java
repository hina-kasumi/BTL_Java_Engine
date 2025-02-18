package com.hina.entities.enemy.BossEnemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.hina.entities.AnimationState;
import com.hina.entities.Entity;
import com.hina.entities.Player.Player;

import static com.hina.constant.BossMonsterConst.*;
import static com.hina.constant.GameConst.PPM;

public abstract class BossEnemy extends Entity {
    protected final Player player;
    protected Animation<TextureRegion> idleAnimation;
    protected Animation<TextureRegion> attackAnimation;
    protected Animation<TextureRegion> runAnimation;
    protected Animation<TextureRegion> takeHitAnimation;
    protected Animation<TextureRegion> deathAnimation;
    protected int startAttackAt;
    protected int endAttackAt;

    public BossEnemy(World world, Player player, float x, float y, float maxHealth) {
        super(world, x, y, BOSS_WIDTH, BOSS_HEIGHT, maxHealth, BOSS_DENSITY);

        this.player = player;

        body.setGravityScale(5);
        body.setUserData(this);
    }

    @Override
    public void update(float delta) {
        animationPriority.add(AnimationState.IDLE);
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (isBodyNull())
            return;
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame;

        switch (animationPriority.get()) {
            case null -> {
                return;
            }
            case RUN -> currentFrame = runAnimation.getKeyFrame(stateTime, true);
            case ATTACK -> currentFrame = attackAnimation.getKeyFrame(stateTime, false);
            case TAKE_HIT -> currentFrame = takeHitAnimation.getKeyFrame(stateTime, false);
            case DEATH -> currentFrame = deathAnimation.getKeyFrame(stateTime, false);
            default -> currentFrame = idleAnimation.getKeyFrame(stateTime, true);
        }

        flip(currentFrame);

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
