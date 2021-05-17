/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.shot;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.MovingPart;
import dk.group6.common.data.entityparts.SpritePart;
import dk.group6.common.services.IEntityProcessingService;
import dk.group6.common.shot.Shot;

/**
 *
 * @author peter
 */
public class ShotProcessor implements IEntityProcessingService{

    @Override
    public void process(GameData gameData, World world) {
        for (Entity shot : world.getEntities(Shot.class)){
            MovingPart movingPart = shot.getPart(MovingPart.class);
            SpritePart spritePart = shot.getPart(SpritePart.class);
            movingPart.process(gameData, shot);
            spritePart.process(gameData, shot);
            
            //Checks if bullet collides with wall
            if (world.getMapTileLayer().getCell((int)spritePart.getSpriteLeftBottom()[0]/45, (int)spritePart.getSpriteLeftBottom()[1]/45).getTile().getProperties().containsKey("Wall") ||
                    world.getMapTileLayer().getCell((int)spritePart.getSpriteRightBottom()[0]/45, (int)spritePart.getSpriteRightBottom()[1]/45).getTile().getProperties().containsKey("Wall") ||
                    world.getMapTileLayer().getCell((int)spritePart.getSpriteLeftTop()[0]/45, (int)spritePart.getSpriteLeftTop()[1]/45).getTile().getProperties().containsKey("Wall") ||
                    world.getMapTileLayer().getCell((int)spritePart.getSpriteRightTop()[0]/45, (int)spritePart.getSpriteRightTop()[1]/45).getTile().getProperties().containsKey("Wall")
                    ) {
                world.removeEntity(shot);
            }
        }
    }
}
