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

            int left, up, right, down;
            try {
                left = movingPart.getDist("left"); 
                up = movingPart.getDist("up"); 
                right = movingPart.getDist("right");
                down = movingPart.getDist("down");
                if (left < 2) {
                    world.removeEntity(shot);
                    return;
                } 
                if (up < 2) {
                    world.removeEntity(shot);
                    return;
                } 
                if (right < 2) {
                    world.removeEntity(shot);
                    return;
                } 
                if (down < 2) {
                    world.removeEntity(shot);
                    return;
                }
            } 
            catch (NullPointerException e) {
            }
            movingPart.process(gameData, shot);
            spritePart.process(gameData, shot);
        }
    }
}
