package dk.group6.enemy;

import dk.group6.common.ai.IPathFinderSPI;
import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.MovingPart;
import dk.group6.common.data.entityparts.PositionPart;
import dk.group6.common.data.entityparts.SpritePart;
import dk.group6.common.enemy.Enemy;
import dk.group6.common.services.IEntityProcessingService;
import java.util.ArrayList;

public class EnemyProcessor implements IEntityProcessingService {

    private IPathFinderSPI pathFinder;

    @Override
    public void process(GameData gameData, World world) {
        Entity player = null;

        for (Entity entity : world.getEntities(Enemy.class)) {

            PositionPart positionPart = entity.getPart(PositionPart.class);
            MovingPart movingPart = entity.getPart(MovingPart.class);
            SpritePart spritePart = entity.getPart(SpritePart.class);

			player = getPlayerEntity(world, entity);
			
			ArrayList<String> strA = getTrack(entity, player, world);
				
			if (player != null && strA != null && strA.size() > 1) {
				switch (strA.get(1)) {
					case "up":
						movingPart.setUp(true);
						break;
					case "right":
						movingPart.setRight(true);
						break;
					case "left":
						movingPart.setLeft(true);
						break;
					case "down":
						movingPart.setDown(true);
						break;
					default:
						break;
				}
			}

            movingPart.process(gameData, entity);
            positionPart.process(gameData, entity);
            spritePart.process(gameData, entity);
            
            
           
                        

        }
        //Check for enemies on map and if empty, respawn enemies
        EnemyPlugin enemyPlugin = new EnemyPlugin();
        if(!world.getEntities().toString().contains("Enemy")) {
            enemyPlugin.start(gameData, world);
                }
    }

    private ArrayList<String> getTrack(Entity e, Entity p, World w) {
        return pathFinder.track(e, p, w);
    }

    public Entity getPlayerEntity(World world, Entity enemy) {
        Entity tempEntity = null;
        for (Entity entity: world.getEntities()) {
	    if (entity.getClass().toString().contains("Player")){
		return entity;
	    }
        }
        return tempEntity;
    }
    
    public void setPathFinder(IPathFinderSPI pathFinder) {
        this.pathFinder = pathFinder;
    }

    public void removePathFinder(IPathFinderSPI pathFinder) {
        this.pathFinder = null;
    }
}
