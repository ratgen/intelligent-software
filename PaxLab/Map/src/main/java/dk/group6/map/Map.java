package dk.group6.map;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import dk.group6.common.map.MapSPI;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;



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
		String mapPath = "assets/map/test.tmx";
        try {
			BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
			Bundle bundle = context.getBundle();
			URL url = bundle.getResource(mapPath);
			//create temp dir to hold the files	
			Path dir = Files.createTempDirectory("assets");
			File dirFile = dir.toFile();
			
			//read the map file
			File mapFile = new File(dirFile, "map");
            FileOutputStream fs = new FileOutputStream(mapFile);
            BufferedInputStream input = new BufferedInputStream(url.openConnection().getInputStream());
            while (input.available() > 0 ) {
                int bytes = input.read();
                fs.write(bytes);
            }
            fs.close();
            input.close();



        }
        catch (IOException e) {
            e.printStackTrace();
        }




        map = new TmxMapLoader().load();
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
