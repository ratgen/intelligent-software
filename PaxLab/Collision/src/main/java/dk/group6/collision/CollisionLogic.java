/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.collision;

import com.badlogic.gdx.graphics.g2d.Sprite;
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
import dk.group6.enemy.EnemyPlugin;
import java.util.ArrayList;

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

			Sprite entitySprite = spritePart.getSprite();

			if (entitySprite == null) {
				continue;
			}

            r1 = new Rectangle(entitySprite.getBoundingRectangle());

            for (Entity entity1 : world.getEntities()) {
                SpritePart spritePart1 = entity1.getPart(SpritePart.class);

				Sprite entity1Sprite = spritePart1.getSprite();

				if (entity1Sprite == null) {
					continue;
				}

                r2 = new Rectangle(entity1Sprite.getBoundingRectangle());
                // skips if entities are the same
                if (entity.getID().equals(entity1.getID()) && entitySprite.equals(entity1Sprite)) {
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
                        } else if (entityLife.getLife() <= 0 && entitySprite != entity1Sprite) {
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

                                //spawn enemy if another dies (gives nullpointer @ Line:44)
                                /* r3 = new Rectangle(spritePart.getSprite().getBoundingRectangle());
                                EnemyPlugin enemy1 = new EnemyPlugin();
                                Entity enemy11 = enemy1.createEnemy(gameData);
                                world.addEntity(enemy11);
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
            MovingPart mp = entity.getPart(MovingPart.class);
            SpritePart sp = entity.getPart(SpritePart.class);

			if (sp.getSprite() == null || mp == null) {
				continue;
			}

            float[] lb = sp.getSpriteLeftBottom();
            float[] lt = sp.getSpriteLeftTop();
            float[] rb = sp.getSpriteRightBottom();
            float[] rt = sp.getSpriteRightTop();
			int[] intarr = {1, 2, 3, 5, 8, 13, 21, 34, 55};
				
			for (int INT : intarr ) {
				//left
				if(check_left(lb, INT, world) && check_left(lt, INT, world)){
					System.out.println("left found at " + INT);
					mp.setLeftDistance(INT);
					break;
				} else {
					mp.setLeftDistance(55);
				} 
			}
			for (int INT : intarr ) {
				//up
				if(check_up(lt, INT, world) && check_up(rt, INT, world)){
					System.out.println("up found at " + INT);
					mp.setUpDistance(INT);
					break;
				} else {
					mp.setUpDistance(55);
				} 
			}
			for (int INT : intarr ) {
				//right
				if(check_right(rt, INT, world) && check_right(rb, INT, world)){
					System.out.println("right found at " + INT);
					mp.setRightDistance(INT);
					break;
				} else {
					mp.setRightDistance(55);
				} 
			}
			for (int INT : intarr ) {
				//down
				if(check_down(rb, INT, world) && check_down(lb, INT, world)){
					System.out.println("down found at " + INT);
					mp.setDownDistance(INT);
					break;
				} else {
					mp.setDownDistance(55);
				} 
			}
        }
    }
	private boolean check_left(float[] point, int offset, World world) {
		return world.isValidCell((int) (point[0] - offset) / 45, (int) (point[1]) / 45);
	}
	private boolean check_up(float[] point, int offset, World world) {
		return world.isValidCell((int) (point[0] ) / 45, (int) (point[1] + offset) / 45);
	}
	private boolean check_right(float[] point, int offset, World world) {
		return world.isValidCell((int) (point[0] + offset) / 45, (int) (point[1]) / 45);
	}
	private boolean check_down(float[] point, int offset, World world) {
		return world.isValidCell((int) (point[0] ) / 45, (int) (point[1] - offset) / 45);
	}
}
