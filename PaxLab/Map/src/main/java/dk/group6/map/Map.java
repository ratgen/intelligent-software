package dk.group6.map;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import dk.group6.common.map.MapSPI;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author peter
 */
public class Map implements MapSPI {
    
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private TiledMapTileLayer mapTileLayer;
    
    @Override
    public void createMap() {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        map = new TmxMapLoader().load("../Map/src/main/resources/assets/map/test.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);   
        mapTileLayer = (TiledMapTileLayer) this.getMap().getLayers().get("Tile Layer 1");
    }

    @Override
    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }
    
    @Override
    public TiledMap getMap() {
        return map;
    }
   
    @Override
    public TiledMapTileLayer getMapTileLayer() {
        return mapTileLayer;
    }

    @Override
    public Boolean isWall(int x, int y){
        return mapTileLayer.getCell(x/45, y/45).getTile().getProperties().containsKey("Wall");
    }
}
