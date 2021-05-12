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

public class EnemyProcessor implements IEntityProcessingService {

    private IPathFinderSPI pathFinder;
    private String[] strA;
    boolean spawned = false;

    @Override
    public void process(GameData gameData, World world) {
        Entity player = world.getEntities().iterator().next();

        for (Entity entity : world.getEntities(Enemy.class)) {

            PositionPart positionPart = entity.getPart(PositionPart.class);
            MovingPart movingPart = entity.getPart(MovingPart.class);
            SpritePart spritePart = entity.getPart(SpritePart.class);
            if (!spawned) {
                positionPart.setX(300);
                positionPart.setY(-300);
                
                spawned = true;
            }
            strA = getTrack(entity, player);
            
            for (int i = 0; i < strA.length; i++) {
                switch (strA[i]){
                case "Up":
                    movingPart.setUp(true);
                    break;
                case "Right":
                    movingPart.setRight(true);
                    break;
                case "Left":
                    movingPart.setLeft(true);
                    break;
                case "Down":
                    movingPart.setDown(true);
                    break;
            }
                movingPart.process(gameData, entity);
                positionPart.process(gameData, entity);
                spritePart.process(gameData, entity);
            }
        }
    }
    
    private String[] getTrack(Entity e, Entity p){
        return pathFinder.track(e, p);
    } 
}
