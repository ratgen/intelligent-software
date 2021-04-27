package dk.group6.osgimap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import dk.group6.osgicommonmap.MapSPI;

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

    @Override
    public void createMap() {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        map = new TmxMapLoader().load("../OSGiMap/src/main/resources/assets/map/DemoMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);    
    }


    @Override
    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }



}
