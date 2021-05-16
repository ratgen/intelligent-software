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
        Entity player = world.getEntities().iterator().next();
        
        for (Entity entity : world.getEntities(Enemy.class)) {

            PositionPart positionPart = entity.getPart(PositionPart.class);
            MovingPart movingPart = entity.getPart(MovingPart.class);
            SpritePart spritePart = entity.getPart(SpritePart.class);

            ArrayList<String> strA = getTrack(entity, player);
            System.out.println("eP: "+strA.get(0));
            
                switch (strA.get(0)){
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
            
            movingPart.setA(0.3f);
            movingPart.process(gameData, entity);
            positionPart.process(gameData, entity);
            spritePart.process(gameData, entity);
            movingPart.reset();

        }
    }
    
    private ArrayList<String> getTrack(Entity e, Entity p){
        return pathFinder.track(e, p);
    } 
    
    public void setPathFinder(IPathFinderSPI pathFinder){
        this.pathFinder = pathFinder;
    }

    public void removePathFinder(IPathFinderSPI pathFinder){
        this.pathFinder = null;
    }
}
