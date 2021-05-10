package dk.group6.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import dk.group6.common.map.MapSPI;
import dk.group6.map.tiles.Tile;
import java.util.ArrayList;


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

    int[][] enumArray =  {
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}                    
    };
    /*
    private int[][] generateMap() {
        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 24; y++) {
                
            }
        }
        return null;
    }*/
    
    @Override
    public void createMap() {
        
        //Following code creates a 2D Array containing Tile Objects, based on the enumArray
        /*
        Tile[][] generatedMap = new Tile[enumArray.length][enumArray[0].length];
        float xCoordinate;
        float yCoordinate = 697.5f;
        
        
        int tileID = 0;
        for (int x = 0; x < 16; x++) {
            xCoordinate = 22.5f;
            for (int y = 0; y < 24; y++) {
             float[] coordinates = {xCoordinate, yCoordinate};
             Tile tile = new Tile(enumArray[x][y], tileID, coordinates); 
             generatedMap[x][y] = tile;
             xCoordinate += 45;
             tileID++;
            }
            yCoordinate -= 45;
        }*/
        
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
        return mapTileLayer.getCell(x, y).getTile().getProperties().containsKey("Wall");
    }

/*
    @Override
    public Boolean isRightWall(Sprite sprite) {
        int x = (int)(sprite.getX()+sprite.getWidth());
        int bottomY = (int)sprite.getY();
        int topY = (int)(sprite.getY()+sprite.getHeight());
        
        return (this.isWall(x+1, bottomY) || this.isWall(x+1, topY));
    }

    @Override
    public Boolean isLeftWall(Sprite sprite) {
        int x = (int)sprite.getX();
        int bottomY = (int)sprite.getY();
        int topY = (int)(sprite.getY()+sprite.getHeight());
        
        return (this.isWall(x-1, bottomY) || this.isWall(x-1, topY));
    }

    @Override
    public Boolean isUpWall(Sprite sprite) {
        int y = (int)(sprite.getY()+sprite.getHeight());
        int leftX = (int)sprite.getX();
        int rightX = (int)(sprite.getX()+sprite.getWidth());
        
        return (this.isWall(leftX, y+1) || this.isWall(rightX, y+1));
    }

    @Override
    public Boolean isDownWall(Sprite sprite) {
        int y = (int)sprite.getY();
        int leftX = (int)sprite.getX();
        int rightX = (int)(sprite.getX()+sprite.getWidth());
        
        return (this.isWall(leftX, y-1) || this.isWall(rightX, y-1));
    }*/

}
