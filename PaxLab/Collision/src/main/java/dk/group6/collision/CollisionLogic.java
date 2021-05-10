/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.collision;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.LifePart;
import dk.group6.common.data.entityparts.MovingPart;
import dk.group6.common.data.entityparts.PositionPart;
import dk.group6.common.data.entityparts.SpritePart;
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
        wallCollision(world);
    }

    public void entityCollision(World world) {
        for (Entity entity : world.getEntities()) {
            SpritePart spritePart = entity.getPart(SpritePart.class);
            Rectangle r1 = spritePart.getSprite().getBoundingRectangle();   
            
            for (Entity entity1 : world.getEntities()) {
                SpritePart spritePart1 = entity1.getPart(SpritePart.class);
                Rectangle r2 = spritePart1.getSprite().getBoundingRectangle();

                if (entity.getID().equals(entity1.getID()) && spritePart.getSprite().equals(spritePart1.getSprite())) {
                    continue;
                }
                if (r1.overlaps(r2)) {
                    System.out.println("Collision between entities!");
                }
            }
        }
    }

    // Crasher ved 24 på x-aksen, og 16 på y-aksen
    public void wallCollision(World world) {
        for (Entity entity : world.getEntities()) {
            sdf = world.getMapTileLayer();
            PositionPart pp = entity.getPart(PositionPart.class);
            SpritePart spritePart = entity.getPart(SpritePart.class);

            if (sdf.getCell((int) spritePart.getSpriteLeftBottom()[0] / 45, (int) spritePart.getSpriteLeftBottom()[1] / 45).getTile().getProperties().containsKey("Wall")
                    || sdf.getCell((int) spritePart.getSpriteLeftTop()[0] / 45, (int) spritePart.getSpriteLeftTop()[1] / 45).getTile().getProperties().containsKey("Wall")) {
                pp.setX(pp.getX() + 1);
            }

            if (sdf.getCell((int) spritePart.getSpriteRightBottom()[0] / 45, (int) spritePart.getSpriteRightBottom()[1] / 45).getTile().getProperties().containsKey("Wall")
                    || sdf.getCell((int) spritePart.getSpriteRightTop()[0] / 45, (int) spritePart.getSpriteRightTop()[1] / 45).getTile().getProperties().containsKey("Wall")) {
                pp.setX(pp.getX() - 1);
            }

        }
    }

    public Boolean Collides(Entity entity, Entity entity2) {
        PositionPart entMov = entity.getPart(PositionPart.class);
        PositionPart entMov2 = entity2.getPart(PositionPart.class);
        float dx = (float) entMov.getX() - (float) entMov2.getX();
        float dy = (float) entMov.getY() - (float) entMov2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        if (distance < (entity.getRadius() + entity2.getRadius())) {
            return true;
        }
        return false;
    }
}
