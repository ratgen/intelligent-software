package dk.group6.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.services.IGamePluginService;
import java.lang.reflect.Array;
import java.util.ArrayList;

        
public class MapPlugin implements IGamePluginService {
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    
    @Override
    public void start(GameData gameData, World world) {
        map = new TmxMapLoader().load("DemoMap.tmx");
        
        renderer = new OrthogonalTiledMapRenderer(map);
    }

    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public TiledMap getMap() {
        return this.map;
    }
    
    public OrthogonalTiledMapRenderer getRenderer() {
        return this.renderer;
    }
    
}