package com.hina;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.hina.entities.enemy.BossEnemy.DemonSlime.DemonSlime;
import com.hina.entities.enemy.BossEnemy.FireWorm.FireWorm;
import com.hina.entities.enemy.BossEnemy.FrostGuardian.FrostGuardian;
import com.hina.manager.BossManager;
import com.hina.manager.HeroManager;
import com.hina.manager.BasicEnemyManager;
import com.hina.entities.enemy.BasicEnemy.Goblin.Goblin;
import com.hina.entities.enemy.BasicEnemy.Mushroom.Mushroom;
import com.hina.entities.enemy.BasicEnemy.Skeletion.Skeleton;

import static com.hina.constant.GameConst.*;

public class Map {
    private final World world;
    private final OrthographicCamera camera;
    private final TiledMap tiledMap;
    private final OrthogonalTiledMapRenderer mapRenderer;
    private final BasicEnemyManager basicEnemyManager;
    private final BossManager bossManager;

    public Map(OrthographicCamera camera, World world, HeroManager heroManager, String fileName) {
        this.world = world;
        this.camera = camera;
        this.basicEnemyManager = new BasicEnemyManager(world, heroManager);
        bossManager = new BossManager(world, heroManager);

        tiledMap = new TmxMapLoader().load(fileName);
        createBasicEnemy(heroManager);
        createBossEnemy(heroManager);
        createGroundFromTiledMap();
        createDeathZone();
        createWinZone();
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1 / PPM * MAP_SCALE);
    }

    private void createGroundFromTiledMap() {
        MapLayer objects = tiledMap.getLayers().get("collision_layer");
        createZone(objects, GROUND_TAG);
    }

    private void createDeathZone() {
        MapLayer deadzone_layer = tiledMap.getLayers().get("deadzone_layer");
        createZone(deadzone_layer, DEATH_ZONE_TAG);
    }

    private void createWinZone() {
        MapLayer winZoneLayer = tiledMap.getLayers().get("winzone_layer");
        createZone(winZoneLayer, WIN_ZONE_TAG);
    }

    private void createZone(MapLayer layer, String tag) {
        if (layer == null)
            return;
        MapObjects objects = layer.getObjects();
        for (MapObject object : objects) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();

                BodyDef bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set((rect.x + rect.width / 2) / PPM * MAP_SCALE,
                    (rect.y + rect.height / 2) / PPM * MAP_SCALE);

                Body body = world.createBody(bodyDef);
                body.setUserData(tag);

                PolygonShape shape = new PolygonShape();
                shape.setAsBox((rect.width / 2) / PPM * MAP_SCALE, (rect.height / 2) / PPM * MAP_SCALE);

                FixtureDef fixtureDef = new FixtureDef();
                fixtureDef.shape = shape;
                fixtureDef.friction = 0.5f;

                body.createFixture(fixtureDef);
                shape.dispose();
            }
        }
    }

    private void createBossEnemy(HeroManager heroManager) {
        MapLayer objectLayer = tiledMap.getLayers().get("boss_layer");
        if (objectLayer != null) {
            for (MapObject mapObject : objectLayer.getObjects()) {
                if (mapObject instanceof RectangleMapObject) {
                    Rectangle rect = ((RectangleMapObject) mapObject).getRectangle();
                    float x = rect.x / PPM * MAP_SCALE;
                    float y = rect.y / PPM * MAP_SCALE;
                    bossManager.add(new FrostGuardian(world, heroManager, x, y));
                }
            }
        }
    }

    private void createBasicEnemy(HeroManager heroManager) {
        // Lấy Object Layer chứa quái
        MapLayer objectLayer = tiledMap.getLayers().get("enemy_layer");
        if (objectLayer != null) {
            for (MapObject mapObject : objectLayer.getObjects()) {
                if (mapObject instanceof RectangleMapObject) {
                    Rectangle rect = ((RectangleMapObject) mapObject).getRectangle();
                    float x = rect.x / PPM * MAP_SCALE;
                    float y = rect.y / PPM * MAP_SCALE;
                    int random = (int) (Math.random() * 3);
                    switch (random) {
                        case 0 -> basicEnemyManager.add(new Mushroom(world, heroManager, x, y));
                        case 1 -> basicEnemyManager.add(new Goblin(world, heroManager, x, y));
                        case 2 -> basicEnemyManager.add(new Skeleton(world, heroManager, x, y));
                        default -> System.out.println("tràn số");
                    }
                }
            }
        }
    }


    public void render() {
        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    public void dispose() {
        tiledMap.dispose();
        mapRenderer.dispose();
    }

    public BossManager getBossManager() {
        return bossManager;
    }

    public BasicEnemyManager getBasicEnemyManager() {
        return basicEnemyManager;
    }
}
