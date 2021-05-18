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
import dk.group6.common.data.entityparts.PositionPart;
import dk.group6.common.data.entityparts.SpritePart;
import dk.group6.common.services.IPostEntityProcessingService;
import java.util.ArrayList;
import dk.group6.common.shot.Shot;

/**
 *
 * @author Yonus
 */
public class CollisionLogic implements IPostEntityProcessingService {

    TiledMapTileLayer sdf;
    Rectangle r1, r2;

    @Override
    public void process(GameData gameData, World world) {
        entityCollision(world);
        //wallCollision(world);
        setValidDirections(world);
    }

    public void entityCollision(World world) {
        for (Entity entity : world.getEntities()) {
            SpritePart spritePart = entity.getPart(SpritePart.class);
            r1 = new Rectangle(spritePart.getSprite().getBoundingRectangle());

            for (Entity entity1 : world.getEntities()) {
                SpritePart spritePart1 = entity1.getPart(SpritePart.class);
                r2 = new Rectangle(spritePart1.getSprite().getBoundingRectangle());
                // skips if entities are the same
                if (entity.getID().equals(entity1.getID()) && spritePart.getSprite().equals(spritePart1.getSprite())) {
                    continue;
                }
                if (r1.overlaps(r2)) {
                    // Player & Weapon
                    if ((entity1.getClass().toString().contains("Player") && entity.getClass().toString().contains("Weapon")) || (entity.getClass().toString().contains("Player") && entity1.getClass().toString().contains("Weapon"))) {
                      //  System.out.println("PLAYER touches a WEAPON");
                    }
                    // Player & Enemy
                    if ((entity1.getClass().toString().contains("Player") && entity.getClass().toString().contains("Enemy")) || (entity.getClass().toString().contains("Player") && entity1.getClass().toString().contains("Enemy"))) {
                     //   System.out.println("PLAYER touches a ENEMY");

                        LifePart entityLife = entity.getPart(LifePart.class);

                        if (entityLife.getLife() > 0) {
                            entityLife.setLife(entityLife.getLife() - 1);
                            entityLife.setIsHit(true);
                            break;
                        } else if (entityLife.getLife() <= 0 && spritePart.getSprite() != spritePart1.getSprite()) {
                            world.removeEntity(entity);
                        }
                    }
                    // Enemy & Bullet
                    if ((entity1.getClass().toString().contains("Shot") && entity.getClass().toString().contains("Enemy")) || (entity.getClass().toString().contains("Shot") && entity1.getClass().toString().contains("Enemy"))) {
                        if ((entity1.getClass().toString().contains("Shot") && entity.getClass().toString().contains("Enemy")) || (entity.getClass().toString().contains("Shot") && entity1.getClass().toString().contains("Enemy"))) {
                            // If bullet hits any entity, it looses a life
                            LifePart entityLife = entity.getPart(LifePart.class);

                            if (entityLife.getLife() > 0) {
                                entityLife.setLife(entityLife.getLife() - 1);
                                entityLife.setIsHit(true);
                                world.removeEntity(entity);
                                System.out.println("Enemy got hit by a bullet! - " + entity.getClass().toString());

                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void wallCollision(World world) {
        /*
        for (Entity entity : world.getEntities()) {

            sdf = world.getMapTileLayer();
            PositionPart pp = entity.getPart(PositionPart.class);
            SpritePart spritePart = entity.getPart(SpritePart.class);

            if (entity.getClass() == Shot.class) {
                continue;
            }

            // Bottom    
            if (sdf.getCell(((int) spritePart.getSpriteLeftBottom()[0] + 1) / 45, (int) spritePart.getSpriteLeftBottom()[1] / 45).getTile().getProperties().containsKey("Wall")
                    || sdf.getCell(((int) spritePart.getSpriteRightBottom()[0] - 1) / 45, (int) spritePart.getSpriteLeftBottom()[1] / 45).getTile().getProperties().containsKey("Wall")) {
                pp.setY(pp.getY() + 1);
            }

            // Right
            if (sdf.getCell((int) spritePart.getSpriteRightBottom()[0] / 45, ((int) spritePart.getSpriteRightBottom()[1] + 1) / 45).getTile().getProperties().containsKey("Wall")
                    || sdf.getCell((int) spritePart.getSpriteRightTop()[0] / 45, ((int) spritePart.getSpriteRightTop()[1] - 1) / 45).getTile().getProperties().containsKey("Wall")) {
                pp.setX(pp.getX() - 1);
            }
            // Top
            if (sdf.getCell(((int) spritePart.getSpriteLeftTop()[0] + 1) / 45, (int) spritePart.getSpriteLeftTop()[1] / 45).getTile().getProperties().containsKey("Wall")
                    || sdf.getCell(((int) spritePart.getSpriteRightTop()[0] - 1) / 45, ((int) spritePart.getSpriteRightTop()[1]) / 45).getTile().getProperties().containsKey("Wall")) {
                pp.setY(pp.getY() - 1);
            }

            // Left
            if (sdf.getCell((int) spritePart.getSpriteLeftTop()[0] / 45, ((int) spritePart.getSpriteLeftTop()[1] - 1) / 45).getTile().getProperties().containsKey("Wall")
                    || sdf.getCell((int) spritePart.getSpriteLeftBottom()[0] / 45, ((int) spritePart.getSpriteLeftBottom()[1] + 1) / 45).getTile().getProperties().containsKey("Wall")) {
                pp.setX(pp.getX() + 1);
            }
        }
         */
    }

    public void setValidDirections(World world) {
        sdf = world.getMapTileLayer();

        for (Entity entity : world.getEntities()) {
            ArrayList<String> directions = new ArrayList();
            PositionPart pp = entity.getPart(PositionPart.class);
            SpritePart sp = entity.getPart(SpritePart.class);

            if (!(sdf.getCell((int) sp.getSpriteLeftBottom()[0] / 45, ((int) sp.getSpriteLeftBottom()[1] - 1) / 45).getTile().getProperties().containsKey("Wall")
                    || sdf.getCell((int) sp.getSpriteRightBottom()[0] / 45, ((int) sp.getSpriteLeftBottom()[1] - 1) / 45).getTile().getProperties().containsKey("Wall"))) {
                directions.add("Down");
            }

            if (!(sdf.getCell(((int) sp.getSpriteRightBottom()[0] + 1) / 45, (int) sp.getSpriteRightBottom()[1] / 45).getTile().getProperties().containsKey("Wall")
                    || sdf.getCell(((int) sp.getSpriteRightTop()[0] + 1) / 45, (int) sp.getSpriteRightTop()[1] / 45).getTile().getProperties().containsKey("Wall"))) {
                directions.add("Right");
            }

            if (!(sdf.getCell((int) sp.getSpriteLeftTop()[0] / 45, ((int) sp.getSpriteLeftTop()[1] + 1) / 45).getTile().getProperties().containsKey("Wall")
                    || sdf.getCell((int) sp.getSpriteRightTop()[0] / 45, ((int) sp.getSpriteRightTop()[1] + 1) / 45).getTile().getProperties().containsKey("Wall"))) {
                directions.add("Up");
            }

            if (!(sdf.getCell(((int) sp.getSpriteLeftTop()[0] - 1) / 45, (int) sp.getSpriteLeftTop()[1] / 45).getTile().getProperties().containsKey("Wall")
                    || sdf.getCell(((int) sp.getSpriteLeftBottom()[0] - 1) / 45, (int) sp.getSpriteLeftBottom()[1] / 45).getTile().getProperties().containsKey("Wall"))) {
                directions.add("Left");
            }
            pp.setDirections(directions);
        }
    }
}
