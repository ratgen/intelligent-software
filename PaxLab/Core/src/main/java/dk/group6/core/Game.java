package dk.group6.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.SpritePart;
import dk.group6.common.services.IEntityProcessingService;
import dk.group6.common.services.IGamePluginService;
import dk.group6.common.services.IPostEntityProcessingService;
import dk.group6.core.managers.GameInputProcessor;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import dk.group6.common.map.MapSPI;

public class Game implements ApplicationListener {

    private static OrthographicCamera cam;
    private final GameData gameData = new GameData();
    private static World world = new World();
    private static final List<IEntityProcessingService> entityProcessorList = new CopyOnWriteArrayList<>();
    private static final List<IGamePluginService> gamePluginList = new CopyOnWriteArrayList<>();
    private static List<IPostEntityProcessingService> postEntityProcessorList = new CopyOnWriteArrayList<>();

    private SpriteBatch batch;
    private MapSPI map;

    public Game() {
        init();
    }

    public void init() {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
        cfg.title = "Corona Mortal Combat";
        cfg.width = 1080;
        cfg.height = 720;
        cfg.useGL30 = false;
        cfg.resizable = false;

        new LwjglApplication(this, cfg);
    }

    @Override
    public void create() {
        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);

        cam.update();

        Gdx.input.setInputProcessor(new GameInputProcessor(gameData));

        map.createMap();

        world.setMapTileLayer(map.getMapTileLayer());

        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);

    }

    @Override
    public void render() {
        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());

        update();
        gameData.getKeys().update();
        draw();

    }

    private void update() {
        // Update
        for (IEntityProcessingService entityProcessorService : entityProcessorList) {
            entityProcessorService.process(gameData, world);
        }

        // Post Update
        for (IPostEntityProcessingService postEntityProcessorService : postEntityProcessorList) {
            postEntityProcessorService.process(gameData, world);
        }

        // Checks if game is lost
        if (gameData.isGameLost()) {
            map.gameLost();
        }
    }

    private void draw() {
        map.getRenderer().render();
        map.getRenderer().setView(cam);
        batch.begin();
        for (Entity entity : world.getEntities()) {
            SpritePart spritePart = entity.getPart(SpritePart.class);
            Sprite sprite = spritePart.getSprite();

            if (sprite == null) {
                continue;
            }

            sprite.draw(batch);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        map.unload();
    }

    public void addEntityProcessingService(IEntityProcessingService eps) {
        this.entityProcessorList.add(eps);
    }

    public void removeEntityProcessingService(IEntityProcessingService eps) {
        this.entityProcessorList.remove(eps);
    }

    public void addPostEntityProcessingService(IPostEntityProcessingService eps) {
        postEntityProcessorList.add(eps);
    }

    public void removePostEntityProcessingService(IPostEntityProcessingService eps) {
        postEntityProcessorList.remove(eps);
    }

    public void addGamePluginService(IGamePluginService plugin) {
        this.gamePluginList.add(plugin);
        plugin.start(gameData, world);

    }

    public void removeGamePluginService(IGamePluginService plugin) {
        this.gamePluginList.remove(plugin);
        plugin.stop(gameData, world);
    }

    public void setMap(MapSPI map) {
        this.map = map;
    }

    public void removeMap(MapSPI map) {
        this.map = null;
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
