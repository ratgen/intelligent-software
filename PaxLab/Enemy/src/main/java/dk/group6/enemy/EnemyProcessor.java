package dk.group6.enemy;


import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.MovingPart;
import dk.group6.common.data.entityparts.PositionPart;
import dk.group6.common.data.entityparts.SpritePart;
import dk.group6.common.enemy.Enemy;
import dk.group6.common.services.IEntityProcessingService;

public class EnemyProcessor implements IEntityProcessingService {



    @Override
    public void process(GameData gameData, World world) {

        for (Entity entity : world.getEntities(Enemy.class)) {

            PositionPart positionPart = entity.getPart(PositionPart.class);
            MovingPart movingPart = entity.getPart(MovingPart.class);
            SpritePart spritePart = entity.getPart(SpritePart.class);

            double random = Math.random();
            //movingPart.setLeft(random < 0.2);
            //movingPart.setRight(random > 0.3 && random < 0.5);
            //movingPart.setUp(random > 0.7 && random < 0.85);
            //movingPart.setDown(random > 0.85 && random < 1);
            
            
            movingPart.process(gameData, entity);
            positionPart.process(gameData, entity);
            spritePart.process(gameData, entity);
        }
    }
}
