package com.hina.entities.enemy.Goblin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.hina.entities.Entity;
import com.hina.entities.Player.Player;

import static com.hina.constant.GameConst.PPM;

public class Goblin extends Entity {
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> attackAnimation;
    private Animation<TextureRegion> runAnimation;
    private float stateTime = 0;
    private boolean movingRight = true;
    private GoblinState goblinState;
    private boolean attacking = false;
    private final Vector2 bornPosition;
    private Player player;


    public Goblin(World world, Player player) {
        super(world, 10, 10, 0.5f, 1f, 1.5f);
        this.scale = 4;

        this.player = player;
        bornPosition = new Vector2(body.getPosition());
        body.setGravityScale(5);
        body.setUserData("enemy");

        createAnimation();
    }

    private void createAnimation() {
        idleAnimation = importAnimation(GoblinState.IDLE);
        attackAnimation = importAnimation(GoblinState.ATTACK);
        runAnimation = importAnimation(GoblinState.RUNNING);

        stateTime = 0;
    }

    private Animation<TextureRegion> importAnimation(GoblinState goblinState) {
        Texture texture = new Texture(goblinState.getFileName());
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
        final float activeArea = 5f;
        final float speed = 3f;

        float dst = body.getPosition().x - bornPosition.x;
        float distantToPlayer = body.getPosition().x - player.getPosition().x;

        if ((dst < -activeArea && !movingRight) || (dst > activeArea && movingRight)) {
            movingRight = !movingRight;
        }

        float movingSpeed = speed * ((movingRight) ? 1 : -1);
        body.setLinearVelocity(movingSpeed, body.getLinearVelocity().y);
        updateAnimation();
    }

    private void updateAnimation() {
        goblinState = GoblinState.IDLE;
        if (attacking) {
            goblinState = GoblinState.ATTACK;
            return;
        }
        if (body.getLinearVelocity().x != 0) {
            goblinState = GoblinState.RUNNING;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame;
        switch (goblinState) {
            case ATTACK -> currentFrame = attackAnimation.getKeyFrame(stateTime, false);
            case RUNNING -> currentFrame = runAnimation.getKeyFrame(stateTime, true);
            default -> currentFrame = idleAnimation.getKeyFrame(stateTime, true);
        }

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

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }
}
