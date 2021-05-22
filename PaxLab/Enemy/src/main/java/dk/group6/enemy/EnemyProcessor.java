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
import java.util.LinkedList;

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
			
			LinkedList<Double> strA = pathFinder.track(entity, player, world);
				
			if (player != null && strA != null && strA.size() > 1) {
				double first = strA.pollFirst();
				int degree = (int) (first * 180/Math.PI);
				switch (degree) {
					case 0:
						movingPart.setUp(true);
						break;
					case 90:
						movingPart.setRight(true);
						break;
					case -90:
						movingPart.setLeft(true);
						break;
					case 180:
						movingPart.setDown(true);
						break;
					case -180:
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
        if(world.getEntities(Enemy.class).size() <= 0) {
        	EnemyPlugin enemyPlugin = new EnemyPlugin();
            enemyPlugin.start(gameData, world);
        }
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
