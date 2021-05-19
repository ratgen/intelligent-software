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
            ArrayList<String> directions = new ArrayList();
            PositionPart pp = entity.getPart(PositionPart.class);
            SpritePart sp = entity.getPart(SpritePart.class);

			if (sp.getSprite() == null) {
				continue;
			}

            float[] lb = sp.getSpriteLeftBottom();
            float[] lt = sp.getSpriteLeftTop();
            float[] rb = sp.getSpriteRightBottom();
            float[] rt = sp.getSpriteRightTop();

			int offset = 0;
			
			boolean lb_wall = sdf.getCell((int) (lb[0] - offset)  / 45, (int) (lb[1] - offset) / 45).getTile().getProperties().containsKey("Wall");
			boolean lt_wall = sdf.getCell((int) (lt[0] - offset)  / 45, (int) (lt[1] + offset) / 45).getTile().getProperties().containsKey("Wall");
			boolean rb_wall = sdf.getCell((int) (rb[0] + offset) / 45, (int) (rb[1] - offset) / 45).getTile().getProperties().containsKey("Wall");
			boolean rt_wall = sdf.getCell((int) (rt[0] + offset) / 45, (int) (rt[1] + offset) / 45).getTile().getProperties().containsKey("Wall");


            if (!(lb_wall || rb_wall)) {
                directions.add("down");
            }

            if (!(rb_wall || rt_wall )) {
                directions.add("right");
            }
            if (!(lt_wall || rt_wall )) {
                directions.add("up");
            }
            if (!(lt_wall || lb_wall )) {
                directions.add("left");
            }

            pp.setDirections(directions);
        }
    }
}
