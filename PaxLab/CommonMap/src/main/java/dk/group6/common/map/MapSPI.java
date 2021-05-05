/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.common.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;


/**
 *
 * @author peter
 */
public interface MapSPI {
    
    void createMap();    
    OrthogonalTiledMapRenderer getRenderer();
    TiledMap getMap();
    TiledMapTileLayer getMapTileLayer();
    Boolean isWall(int x, int y);
}
