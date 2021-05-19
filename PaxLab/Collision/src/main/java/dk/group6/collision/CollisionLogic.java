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
import dk.group6.common.data.entityparts.SpritePart;
import dk.group6.common.enemy.Enemy;
import dk.group6.common.player.Player;
import dk.group6.common.services.IPostEntityProcessingService;
import dk.group6.common.shot.Shot;
import dk.group6.common.weapon.Weapon;

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
        setWallDistance(world);
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
                    if ((entity1.getClass() == Player.class && entity.getClass() == Weapon.class) ||
							(entity.getClass() == Player.class && entity1.getClass() == Weapon.class )) {
                    }
                    // Player & Enemy
                    if ((entity1.getClass() == Player.class && entity.getClass() == Enemy.class) ||
							(entity.getClass() == Player.class && entity1.getClass() == Enemy.class )) {

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
                    if ((entity1.getClass() == Shot.class && entity.getClass() == Enemy.class) ||
							(entity.getClass() == Shot.class && entity1.getClass() == Enemy.class )) {
						// If bullet hits any entity, it looses a life
						LifePart entityLife = entity.getPart(LifePart.class);

						if (entityLife.getLife() > 0) {
							entityLife.setLife(entityLife.getLife() - 1);
							entityLife.setIsHit(true);
							//remove enemy and bullet as well
							world.removeEntity(entity);
							world.removeEntity(entity1);

							break;
						}
					}
                }
            }
        }
    }

    public void setWallDistance(World world) {
        for (Entity entity : world.getEntities()) {
            MovingPart mp = entity.getPart(MovingPart.class);
            SpritePart sp = entity.getPart(SpritePart.class);

			if (sp.getSprite() == null || mp == null) {
				continue;
			}

            int[] lb = sp.getSpriteLeftBottom();
            int[] lt = sp.getSpriteLeftTop();
            int[] rb = sp.getSpriteRightBottom();
            int[] rt = sp.getSpriteRightTop();
			int[] intarr = {1, 2, 3, 5, 8, 13, 21, 34, 55};
				
			for (int INT : intarr ) {
				//left
				if(check_left(lb, INT, world) && check_left(lt, INT, world)){
					mp.setLeftDistance(55);
				} else {
					mp.setLeftDistance(INT);
					break;
				} 
			}
			for (int INT : intarr ) {
				//up
				if(check_up(lt, INT, world) && check_up(rt, INT, world)){
					mp.setUpDistance(55);
				} else {
					mp.setUpDistance(INT);
					break;
				} 
			}
			for (int INT : intarr ) {
				//right
				if(check_right(rt, INT, world) && check_right(rb, INT, world)){
					mp.setRightDistance(55);
				} else {
					mp.setRightDistance(INT);
					break;
				} 
			}
			for (int INT : intarr ) {
				//down
				if(check_down(rb, INT, world) && check_down(lb, INT, world)){
					mp.setDownDistance(55);
				} else {
					mp.setDownDistance(INT);
					break;
				} 
			}
        }
    }
	private boolean check_left(int[] point, int offset, World world) {
		return world.isValidCell(point[0] - offset , (point[1]) );
	}
	private boolean check_up(int[] point, int offset, World world) {
		return world.isValidCell(point[0] , point[1] + offset );
	}
	private boolean check_right(int[] point, int offset, World world) {
		return world.isValidCell( point[0] + offset , point[1] );
	}
	private boolean check_down(int[] point, int offset, World world) {
		return world.isValidCell(point[0], point[1] - offset );
	}
}
