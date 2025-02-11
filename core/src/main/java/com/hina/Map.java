package com.hina;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

import static com.hina.constant.GameConst.GROUND_TAG;
import static com.hina.constant.GameConst.PPM;

public class Map {
    private OrthographicCamera camera;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer mapRenderer;
    private final float scale = 2f;

    public Map(OrthographicCamera camera, World world) {
        this.camera = camera;

        tiledMap = new TmxMapLoader().load("maps/map.tmx");
        createGroundFromTiledMap(world, tiledMap);
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1 / PPM * scale);
    }

    private void createGroundFromTiledMap(World world, TiledMap map) {
        MapObjects objects = map.getLayers().get("collision").getObjects();

        for (MapObject object : objects) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();

                BodyDef bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set((rect.x + rect.width / 2) / PPM * scale,
                    (rect.y + rect.height / 2) / PPM * scale);

                Body body = world.createBody(bodyDef);
                body.setUserData(GROUND_TAG);

                PolygonShape shape = new PolygonShape();
                shape.setAsBox((rect.width / 2) / PPM * scale, (rect.height / 2) / PPM * scale);

                FixtureDef fixtureDef = new FixtureDef();
                fixtureDef.shape = shape;
                fixtureDef.friction = 0.5f;

                body.createFixture(fixtureDef);
                shape.dispose();
            }
        }
    }

    public void render() {
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    public void dispose() {
        tiledMap.dispose();
        mapRenderer.dispose();
    }
}
