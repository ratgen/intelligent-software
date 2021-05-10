/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.collision;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.PositionPart;
import dk.group6.common.services.IPostEntityProcessingService;

/**
 *
 * @author Yonus
 */
public class CollisionLogic implements IPostEntityProcessingService {
    TiledMapTileLayer sdf;

    @Override
    public void process(GameData gameData, World world) {
        entityCollision(world);
        
    }
    
    public void entityCollision(World world) {
        for (Entity entity : world.getEntities()) {
        // INSERT METHOD
        }
    }
    
    public void wallCollision(World world) {
        for (Entity entity : world.getEntities()) {
            sdf = world.getMapTileLayer();
            
            if (sdf.getCell((int)entity.getSpriteLeftBottom()[0], (int)entity.getSpriteLeftBottom()[1]).getTile().getProperties().containsKey("Wall")) {
                PositionPart pp = entity.getPart(PositionPart.class);
                pp.setX(pp.getX()+1);
            }
        }
    }
}
