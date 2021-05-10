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

    @Override
    public void process(GameData gameData, World world) {
        Entity player = world.getEntities().iterator().next();

        for (Entity entity : world.getEntities(Enemy.class)) {

            PositionPart positionPart = entity.getPart(PositionPart.class);
            MovingPart movingPart = entity.getPart(MovingPart.class);
            SpritePart spritePart = entity.getPart(SpritePart.class);      
            
            strA = getTrack(entity, player);
            
            movingPart.setMovement(strA[0]);
            
            movingPart.process(gameData, entity);
            movingPart.reset();
            positionPart.process(gameData, entity);
            spritePart.process(gameData, entity);
            //updateShape(entity);

        }
    }
    
    private String[] getTrack(Entity e, Entity p){
        return pathFinder.track(e, p);
    }
    
    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 8);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

    public void setPathFinder(IPathFinderSPI pathFinder){
        this.pathFinder = pathFinder;
    }
    
    public void removePathFinder(IPathFinderSPI pathFinder){
        this.pathFinder = null;
    }
    
}
