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
<<<<<<< Updated upstream
=======
import dk.group6.enemy.EnemyPlugin;
>>>>>>> Stashed changes

/**
 *
 * @author Yonus
 */
public class CollisionLogic implements IPostEntityProcessingService {

    TiledMapTileLayer sdf;
    Rectangle r1, r2, r3;
    GameData gameData;

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
                                System.out.println("ENEMY DIED!");
                                
                                // if enemy dies, spawn a new one. (Currently gives nullpointer exception Line:44)
                                
                                /* r3 = new Rectangle(spritePart1.getSprite().getBoundingRectangle());
                                EnemyPlugin enemy = new EnemyPlugin();
                                Entity enemy1 = enemy.createEnemy(gameData);
                                world.addEntity(enemy1);
                                */

                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void setValidDirections(World world) {
        sdf = world.getMapTileLayer();

        for (Entity entity : world.getEntities()) {
            ArrayList<String> directions = new ArrayList();
            PositionPart pp = entity.getPart(PositionPart.class);
            SpritePart sp = entity.getPart(SpritePart.class);

<<<<<<< Updated upstream
			float[] lb = sp.getSpriteLeftBottom();
			float[] lt = sp.getSpriteLeftTop();
			float[] rb = sp.getSpriteRightBottom();
			float[] rt = sp.getSpriteRightTop();
			
            if (!(
				sdf.getCell(
					(int) lb[0] / 45, 
		    		((int) lb[1] - 1) / 45).getTile().getProperties().containsKey("Wall")
                || 
				sdf.getCell(
					(int) rb[0] / 45, 
					((int) lb[1] - 1) / 45).getTile().getProperties().containsKey("Wall"))
				) {
                directions.add("down");
            }

            if (!(sdf.getCell(((int) rb[0] + 1) / 45, 
		    (int) rb[1] / 45).getTile().getProperties().containsKey("Wall")
                    || 
		sdf.getCell(((int) rt[0] + 1) / 45, 
			(int) rt[1] / 45).getTile().getProperties().containsKey("Wall"))) {
=======
            if (!(sdf.getCell((int) sp.getSpriteLeftBottom()[0] / 45,
                    ((int) sp.getSpriteLeftBottom()[1] - 1) / 45).getTile().getProperties().containsKey("Wall")
                    || sdf.getCell((int) sp.getSpriteRightBottom()[0] / 45,
                            ((int) sp.getSpriteLeftBottom()[1] - 1) / 45).getTile().getProperties().containsKey("Wall"))) {
                directions.add("down");
            }

            if (!(sdf.getCell(((int) sp.getSpriteRightBottom()[0] + 1) / 45,
                    (int) sp.getSpriteRightBottom()[1] / 45).getTile().getProperties().containsKey("Wall")
                    || sdf.getCell(((int) sp.getSpriteRightTop()[0] + 1) / 45,
                            (int) sp.getSpriteRightTop()[1] / 45).getTile().getProperties().containsKey("Wall"))) {
>>>>>>> Stashed changes
                directions.add("right");
            }

            if (!(sdf.getCell((int) lt[0] / 45, 
					((int) lt[1] + 1) / 45)
					.getTile().getProperties().containsKey("Wall")
                    || 
				sdf.getCell((int) rt[0] / 45, 
						((int) rt[1] + 1) / 45
				).getTile().getProperties().containsKey("Wall"))) {
                directions.add("up");
            }

            if (!(sdf.getCell(((int) lt[0] - 1) / 45, 
					(int) lt[1] / 45).getTile().getProperties().containsKey("Wall")
                    || sdf.getCell(((int) lb[0] - 1) / 45, (int) lb[1] / 45).getTile().getProperties().containsKey("Wall"))) {
                directions.add("left");
            }
            pp.setDirections(directions);
        }
    }
}
