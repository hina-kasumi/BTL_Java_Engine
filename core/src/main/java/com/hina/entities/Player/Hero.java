package com.hina.entities.Player;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.hina.entities.AnimationState;
import com.hina.entities.Entity;
import com.hina.utils.attackUtils.MultiHitAttack;

import static com.hina.constant.PlayerConst.*;
import static com.hina.constant.GameConst.*;
import static com.hina.GameManager.isGameStop;
import static com.hina.utils.ImportTextureUtil.newImportAnimation;

public abstract class Hero extends Entity {
    protected Animation<TextureRegion> idleAnimation;
    protected Animation<TextureRegion> movingAnimation;
    protected Animation<TextureRegion> jumpAnimation;
    protected Animation<TextureRegion> fallAnimation;
    protected Animation<TextureRegion> attackAnimation;
    protected Animation<TextureRegion> takeHitAnimation;
    protected Animation<TextureRegion> deathAnimation;
    protected Animation<TextureRegion> specialAnimation;
    protected Animation<TextureRegion> rollAnimation;
    protected Animation<TextureRegion> defendAnimation;
    protected Animation<TextureRegion> airAttackAnimation;
    protected final PlayerHealthBar playerHealthBar;
    protected boolean onGround;
    protected boolean specialAttacking;
    protected boolean attacking;
    protected float attackBoxWidth;
    protected float attackBoxHeight;
    protected int startBasicAttackAt;
    protected int endBasicAttackAt;
    protected float movingSpeed;
    protected final float offsetY;
    protected boolean multiHitInSpecialAttack;
    private final MultiHitAttack multiHitAttack;
    protected float specialAttackWidth;
    protected float specialAttackHeight;
    private int specialAttackStartFrame;
    private int specialAttackEndFrame;
    private float specialAttackDamage;
    private float specialAttackBoxOffsetX;
    private boolean rolling;
    private boolean defending;
    private boolean pauseAnimation;
    private int defendAt;


    public Hero(World world, Vector2 bornPosition, float maxHealth, String heroSrc, float offsetY) {
        super(world, bornPosition.x, bornPosition.y, 0.5f, 1f, maxHealth, 1.5f);

        body.setGravityScale(5);
        body.setUserData(this);

        this.scale = PLAYER_SCALE;
        this.playerHealthBar = new PlayerHealthBar(this);
        this.offsetY = offsetY;
        this.multiHitAttack = new MultiHitAttack(body);

        createAnimation(heroSrc);
    }


    private void createAnimation(String heroSrc) {
        idleAnimation = newImportAnimation(heroSrc + HeroState.IDLE.getFileName());
        attackAnimation = newImportAnimation(heroSrc + HeroState.ATTACK.getFileName(), 0.05f);
        movingAnimation = newImportAnimation(heroSrc + HeroState.RUNNING.getFileName());
        jumpAnimation = newImportAnimation(heroSrc + HeroState.JUMP.getFileName());
        fallAnimation = newImportAnimation(heroSrc + HeroState.FALL.getFileName());
        takeHitAnimation = newImportAnimation(heroSrc + HeroState.TAKE_HIT.getFileName());
        specialAnimation = newImportAnimation(heroSrc + HeroState.SPECIAL_ATTACK.getFileName(), 0.1f);
        rollAnimation = newImportAnimation(heroSrc + HeroState.ROLL.getFileName());
        defendAnimation = newImportAnimation(heroSrc + HeroState.DEFEND.getFileName());
        airAttackAnimation = newImportAnimation(heroSrc + HeroState.AIR_ATTACK.getFileName());
        deathAnimation = newImportAnimation(heroSrc + HeroState.DEATH.getFileName(), 0.15f);
    }

    protected void setHeroImage(String heroImageSrc){
        playerHealthBar.setHeroImage(heroImageSrc);
    }

    private void blockFlip(boolean prevMoveRight) {
        if (prevMoveRight != movingRight) {
            movingRight = prevMoveRight;
        }
    }

    protected void setDefendAnimationAt(int defendAt) {
        this.defendAt = defendAt;
    }


    @Override
    public void update(float delta) {
        deathUpdate();
        playerHealthBar.update();
        if (isDeath) {
            return;
        }

        boolean prevMoveRight = movingRight;
        movingSpeed = 0;
        runUpdate();
        takeHitUpdate();
        attackUpdate(prevMoveRight);
        specialAttackUpdate(prevMoveRight);
        jumpUpdate();
        rollUpdate(prevMoveRight);
        defendUpdate();

        body.setLinearVelocity(movingSpeed, body.getLinearVelocity().y);
        updateAnimation();
    }

    private void defendUpdate() {
        if (Gdx.input.isKeyPressed(Input.Keys.S) && isIdle()) {
            resetStateTime();
            defending = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S) && defending) {
            if (defendAt == defendAnimation.getKeyFrameIndex(stateTime)) {
                immortal = true;
                pauseAnimation = true;
            }
        } else {
            pauseAnimation = false;
            immortal = false;
            defending = false;
        }
        if (defending) {
            movingSpeed = 0;
        }
    }

    private void rollUpdate(boolean prevMoveRight) {
        if (Gdx.input.isKeyPressed(Input.Keys.L) && isIdle()) {
            resetStateTime();
            rolling = true;
        }

        if (rolling) {
            blockFlip(prevMoveRight);
            if (rollAnimation.isAnimationFinished(stateTime)) {
                rolling = false;
            }
            movingSpeed = 12f * (movingRight ? 1 : -1);
        }
    }


    private void runUpdate() {
        final float speed = 10f;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            movingSpeed -= speed;
            movingRight = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            movingSpeed += speed;
            movingRight = true;
        }
    }

    private void jumpUpdate() {
        final float jumpStrength = Math.min(entityHeight, entityWidth) * 100;
        if (Gdx.input.isKeyPressed(Input.Keys.K) && onGround && !specialAttacking && !rolling) {
            body.applyLinearImpulse(new Vector2(0, jumpStrength), body.getWorldCenter(), true);
            onGround = false;
        }
    }

    private void attackUpdate(boolean prevMoveRight) {
        if (Gdx.input.isKeyPressed(Input.Keys.J) && isNotCombat()) {
            resetStateTime();
            attacking = true;
        }
        if (attacking && !takingHit) {
            blockFlip(prevMoveRight);
            if (Math.abs(body.getLinearVelocity().y) < 0.1) {
                movingSpeed = 0;
            }

            int keyFrameIndex = attackAnimation.getKeyFrameIndex(stateTime);
            if (basicAttackBox.isDestroyed() && keyFrameIndex == startBasicAttackAt) {
                basicAttackBox.createHitBox(body,
                    getPosition().x + attackBoxWidth * (movingRight ? 1 : -1), getPosition().y,
                    attackBoxWidth, attackBoxHeight, ATTACK_DAMAGE);
            }
            if (endBasicAttackAt != 0 && keyFrameIndex == endBasicAttackAt) {
                basicAttackBox.destroyAttackSensor();
            }

            if (attackAnimation.isAnimationFinished(stateTime)) {
                attacking = false;
                basicAttackBox.destroyAttackSensor();
            }
        }
    }

    private void takeHitUpdate() {
        if (takingHit) {
            if (takeHitAnimation.isAnimationFinished(stateTime)) {
                resetStateTime();
                takingHit = false;
            }
            attacking = false;
            specialAttacking = false;
            defending = false;
            basicAttackBox.destroyAttackSensor();
            animationPriority.add(AnimationState.TAKE_HIT);
            blockMoving();
        }
    }

    protected void setMultiHitInSpecialAttack(int[] specialAttackTurns, float width, float height, float damage) {
        this.multiHitInSpecialAttack = true;
        this.multiHitAttack.setTurn(specialAttackTurns);
        this.specialAttackWidth = width;
        this.specialAttackHeight = height;
        this.specialAttackDamage = damage;
    }

    protected void setBasicSpecialAttack(float width, float height, int startFrame, int endFrame, float damage) {
        this.specialAttackWidth = width;
        this.specialAttackHeight = height;
        this.specialAttackStartFrame = startFrame - 1;
        this.specialAttackEndFrame = endFrame - 1;
        this.specialAttackDamage = damage;
    }

    protected void setSpecialAttackBoxOffsetX(float specialAttackBoxOffsetX) {
        this.specialAttackBoxOffsetX = specialAttackBoxOffsetX;
    }

    private void specialAttackUpdate(boolean prevMoveRight) {
        if (Gdx.input.isKeyPressed(Input.Keys.I) && isNotCombat()) {
            resetStateTime();
            specialAttacking = true;
        }
        if (specialAttacking) {
            blockFlip(prevMoveRight);
            float x = body.getPosition().x + specialAttackBoxOffsetX * (movingRight ? 1 : -1);
            float y = body.getPosition().y + Math.abs(entityHeight - specialAttackHeight);

            if (multiHitInSpecialAttack) {
                multiHitAttack.attack(body, x, y,
                    specialAttackWidth,
                    specialAttackHeight, specialAttackDamage,
                    specialAnimation.getKeyFrameIndex(stateTime));

                if (specialAnimation.isAnimationFinished(stateTime)) {
                    specialAttacking = false;
                }
            } else {
                int keyFrameIndex = specialAnimation.getKeyFrameIndex(stateTime);
                if (basicAttackBox.isDestroyed() && keyFrameIndex == specialAttackStartFrame) {
                    basicAttackBox.createHitBox(body, x, y,
                        specialAttackWidth, specialAttackHeight, specialAttackDamage);
                }

                if (specialAttackEndFrame != 0 && keyFrameIndex == specialAttackEndFrame) {
                    basicAttackBox.destroyAttackSensor();
                }

                if (specialAnimation.isAnimationFinished(stateTime)) {
                    specialAttacking = false;
                    basicAttackBox.destroyAttackSensor();
                }
            }
            movingSpeed = 0;
        }
    }

    private void deathUpdate() {
        if (isDeath) {
            blockMoving();
            if (deathAnimation.isAnimationFinished(stateTime)) {
                isGameStop = true;
            }
            animationPriority.add(AnimationState.DEATH);
        }
    }

    public boolean isIdle() {
        return !isDeath && !attacking && onGround && !takingHit && !specialAttacking && !rolling && !defending;
    }

    public boolean isNotCombat() {
        return !attacking && !takingHit && !specialAttacking && !rolling && !defending;
    }


    private void updateAnimation() {
        animationPriority.add(AnimationState.IDLE);
        if (specialAttacking) {
            animationPriority.add(AnimationState.SPECIAL_ATTACK);
        }
        if (attacking) {
            animationPriority.add(AnimationState.ATTACK);
            if (!onGround)
                animationPriority.add(AnimationState.AIR_ATTACK);
            return;
        }

        if (defending) {
            animationPriority.add(AnimationState.DEFEND);
        }

        if (rolling) {
            animationPriority.add(AnimationState.ROLL);
        }
        if (body.getLinearVelocity().x != 0) {
            animationPriority.add(AnimationState.RUN);
        }
        if (body.getLinearVelocity().y >= 0.1) {
            animationPriority.add(AnimationState.JUMP);
        } else if (body.getLinearVelocity().y <= -0.1) {
            animationPriority.add(AnimationState.FALL);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (!pauseAnimation) {
            stateTime += Gdx.graphics.getDeltaTime();
        }
        TextureRegion currentFrame;

        switch (animationPriority.get()) {
            case null -> {
                return;
            }
            case RUN -> currentFrame = movingAnimation.getKeyFrame(stateTime, true);
            case ATTACK -> currentFrame = attackAnimation.getKeyFrame(stateTime, false);
            case JUMP -> currentFrame = jumpAnimation.getKeyFrame(stateTime, true);
            case FALL -> currentFrame = fallAnimation.getKeyFrame(stateTime, true);
            case DEATH -> currentFrame = deathAnimation.getKeyFrame(stateTime, false);
            case TAKE_HIT -> currentFrame = takeHitAnimation.getKeyFrame(stateTime, false);
            case AIR_ATTACK -> currentFrame = airAttackAnimation.getKeyFrame(stateTime, false);
            case SPECIAL_ATTACK -> currentFrame = specialAnimation.getKeyFrame(stateTime, false);
            case ROLL -> currentFrame = rollAnimation.getKeyFrame(stateTime, false);
            case DEFEND -> currentFrame = defendAnimation.getKeyFrame(stateTime, false);
            default -> currentFrame = idleAnimation.getKeyFrame(stateTime, true);
        }

        flip(currentFrame);

        batch.draw(currentFrame,
            body.getPosition().x - scale * currentFrame.getRegionWidth() / 2 / PPM,
            body.getPosition().y - entityHeight - offsetY / PPM * scale,
            currentFrame.getRegionWidth() * scale / PPM,
            currentFrame.getRegionHeight() * scale / PPM
        );
    }

    public void renderPlayerHealthBar(SpriteBatch batch, Camera camera, FitViewport viewport) {
        playerHealthBar.render(batch, camera, viewport);
    }

    protected void setBasicAttackAt(int start, int end) {
        if (start > end)
            return;
        this.startBasicAttackAt = start - 1;
        this.endBasicAttackAt = end - 1;
    }

    public void setAttackBoxSize(float width, float height) {
        this.attackBoxWidth = width;
        this.attackBoxHeight = height;
    }

    protected void setBasicAttackAt(int start) {
        this.startBasicAttackAt = start;
    }

    public void setPosition(Vector2 position) {
        body.setTransform(position, body.getAngle());
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    @Override
    public void dispose() {
        playerHealthBar.dispose();
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
}
