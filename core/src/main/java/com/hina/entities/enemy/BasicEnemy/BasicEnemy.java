package com.hina.entities.enemy.BasicEnemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.hina.entities.AnimationState;
import com.hina.entities.Entity;
import com.hina.entities.Player.Player;

import static com.hina.constant.GameConst.PPM;

public abstract class BasicEnemy extends Entity {
    protected final Player player;
    protected final Vector2 bornPosition;
    protected Animation<TextureRegion> idleAnimation;
    protected Animation<TextureRegion> attackAnimation;
    protected Animation<TextureRegion> runAnimation;
    protected Animation<TextureRegion> takeHitAnimation;
    protected Animation<TextureRegion> deathAnimation;

    public BasicEnemy(World world, Player player, float x, float y, float maxHeath) {
        super(world, x, y, 0.5f, 1f, maxHeath, 1.5f);

        this.player = player;
        this.bornPosition = new Vector2(x, y);
        this.attacking = false;

        body.setGravityScale(5);
        body.setUserData(this);
    }

    @Override
    public void update(float delta) {
        deathUpdate();
        if (isDeath) {
            return;
        }

        float distantToPlayer = player.getPosition().x - body.getPosition().x;
        float bornToPlayer = player.getPosition().x - bornPosition.x;
        boolean ableAttackPlayer = player.getPosition().dst(body.getPosition()) <= 5;
        boolean prevMoveRight = movingRight;

        runUpdate(bornToPlayer, distantToPlayer, ableAttackPlayer);
        attackUpdate(prevMoveRight, distantToPlayer, ableAttackPlayer);
        takeHitUpdate(prevMoveRight);
    }

    protected void runUpdate(float bornToPlayer, float distantToPlayer, boolean ableAttackPlayer) {
        final float activeArea = 5f;
        final float speed = 3f;

        float dst = body.getPosition().x - bornPosition.x;

        if (Math.abs(bornToPlayer) <= activeArea && ableAttackPlayer) {
            if ((distantToPlayer < 0 && movingRight) || (distantToPlayer >= 0 && !movingRight)) {
                movingRight = !movingRight;
            }
        }
        if ((dst < -activeArea && !movingRight) || (dst > activeArea && movingRight)) {
            movingRight = !movingRight;
        }

        animationPriority.add(AnimationState.RUN);
        setMovement(speed * ((movingRight) ? 1 : -1));
    }

    protected void attackUpdate(boolean prevMoveRight, float distantToPlayer, boolean ableAttackPlayer) {
        if (Math.abs(distantToPlayer) <= 2 && ableAttackPlayer && !takingHit) {
            if (!attacking)
                stateTime = 0;
            attacking = true;
        }
        if (attacking) {
            if (attackBox.isDestroyed()) {
                attackBox.createHitBox(1, 1, 10f, movingRight);
            }
            if (attackAnimation.isAnimationFinished(stateTime)) {
                attacking = false;
                attackBox.destroyAttackSensor();
            } else if (prevMoveRight != movingRight) {
                movingRight = prevMoveRight;
            }
            blockMoving();
            animationPriority.add(AnimationState.ATTACK);
        }
    }

    protected void takeHitUpdate(boolean prevMoveRight) {
        if (takingHit) {
            if (takeHitAnimation.isAnimationFinished(stateTime)) {
                stateTime = 0;
                takingHit = false;
            } else if (prevMoveRight != movingRight) {
                movingRight = prevMoveRight;
            }
            attacking = false;
            attackBox.destroyAttackSensor();
            animationPriority.add(AnimationState.TAKE_HIT);
            blockMoving();
        }
    }

    protected void deathUpdate() {
        if (isDeath) {
            if (!isBodyNull())
                blockMoving();
            if (deathAnimation.isAnimationFinished(stateTime)) {
                death();
            }
            animationPriority.add(AnimationState.DEATH);
        }
    }

    private void setMovement(float movingSpeed) {
        body.setLinearVelocity(movingSpeed, body.getLinearVelocity().y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (isBodyNull())
            return;
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame;

        switch (animationPriority.get()) {
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
}
