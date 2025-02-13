package com.hina.entities.enemy.BasicEnemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.hina.entities.Entity;
import com.hina.entities.Player.Player;

public abstract class BasicEnemy extends Entity {
    protected final Player player;
    protected final Vector2 bornPosition;
    protected boolean attacking;
    protected boolean takingHit;
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

    protected Animation<TextureRegion> importAnimation(String fileName) {
        Texture texture = new Texture(fileName);

        TextureRegion[][] textureRegions = TextureRegion
            .split(texture, texture.getHeight(), texture.getHeight());

        Array<TextureRegion> array = new Array<>();
        for (int i = 0; i < textureRegions[0].length; i++) {
            array.add(textureRegions[0][i]);
        }

        return new Animation<>(0.15f, array, Animation.PlayMode.LOOP);
    }

    @Override
    public void update(float delta) {
        if (isDead()) {
            death();
            return;
        }

        final float activeArea = 5f;
        final float speed = 3f;
        boolean prevMoveRight = movingRight;

        float dst = body.getPosition().x - bornPosition.x;
        float distantToPlayer = player.getPosition().x - body.getPosition().x;
        float bornToPlayer = player.getPosition().x - bornPosition.x;
        boolean dstPlayer = player.getPosition().dst(body.getPosition()) <= 5;

        if (Math.abs(bornToPlayer) <= activeArea && dstPlayer) {
            if ((distantToPlayer < 0 && movingRight) || (distantToPlayer >= 0 && !movingRight)) {
                movingRight = !movingRight;
            }
        }
        if ((dst < -activeArea && !movingRight) || (dst > activeArea && movingRight)) {
            movingRight = !movingRight;
        }

        float movingSpeed = speed * ((movingRight) ? 1 : -1);

        // attacking update
        if (Math.abs(distantToPlayer) <= 2 && dstPlayer) {
            attacking = true;
        }
        if (attacking && !takingHit) {
            if (attackBox.isDestroyed()) {
                attackBox.createHitBox(1, 1, 10f, movingRight);
            }
            if (attackAnimation.isAnimationFinished(stateTime)) {
                stateTime = 0;
                attacking = false;
                attackBox.destroyAttackSensor();
            } else if (prevMoveRight != movingRight) {
                movingRight = prevMoveRight;
            }
            movingSpeed = 0;
        }

        //take hit update
        if (takingHit) {
            attackBox.destroyAttackSensor();
            if (takeHitAnimation.isAnimationFinished(stateTime)) {
                stateTime = 0;
                takingHit = false;
            } else if (prevMoveRight != movingRight) {
                movingRight = prevMoveRight;
            }
            movingSpeed = 0;
        }

        body.setLinearVelocity(movingSpeed, body.getLinearVelocity().y);
        updateAnimation();
    }

    @Override
    public void takeDamage(float damage) {
        if (damage > 0) {
            curHeath -= damage;
            takingHit = true;
        }
    }

    protected abstract void updateAnimation();
}
