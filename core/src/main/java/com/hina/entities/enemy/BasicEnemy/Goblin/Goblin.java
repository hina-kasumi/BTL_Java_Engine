package com.hina.entities.enemy.BasicEnemy.Goblin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.hina.entities.Player.Player;
import com.hina.entities.enemy.BasicEnemy.BasicEnemy;

import static com.hina.constant.GameConst.PPM;

public class Goblin extends BasicEnemy {
    private GoblinState goblinState;

    public Goblin(World world, Player player, float x, float y) {
        super(world, player, x, y, 20f);
        this.scale = 4;

        createAnimation();
    }

    private void createAnimation() {
        idleAnimation = importAnimation(GoblinState.IDLE.getFileName());
        attackAnimation = importAnimation(GoblinState.ATTACK.getFileName());
        runAnimation = importAnimation(GoblinState.RUNNING.getFileName());
        takeHitAnimation = importAnimation(GoblinState.TAKE_HIT.getFileName());
        deathAnimation = importAnimation(GoblinState.DEATH.getFileName());
    }


    @Override
    protected void updateAnimation() {
        if (takingHit){
            goblinState = GoblinState.TAKE_HIT;
            return;
        }
        if (attacking) {
            goblinState = GoblinState.ATTACK;
            return;
        }
        goblinState = GoblinState.IDLE;
        if (body.getLinearVelocity().x != 0) {
            goblinState = GoblinState.RUNNING;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (isDead())
            return;
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame;

        switch (goblinState) {
            case ATTACK -> currentFrame = attackAnimation.getKeyFrame(stateTime, false);
            case RUNNING -> currentFrame = runAnimation.getKeyFrame(stateTime, true);
            case DEATH -> currentFrame = deathAnimation.getKeyFrame(stateTime, false);
            case TAKE_HIT -> currentFrame = takeHitAnimation.getKeyFrame(stateTime, false);
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
