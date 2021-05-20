package dk.group6.enemy;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.LifePart;
import dk.group6.common.data.entityparts.MovingPart;
import dk.group6.common.data.entityparts.PositionPart;
import dk.group6.common.data.entityparts.SpritePart;
import dk.group6.common.enemy.Enemy;
import dk.group6.common.services.IGamePluginService;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyPlugin implements IGamePluginService {
  
    ArrayList<int[]> spawnPoints;
    GameData gameData;
    World world;
    Random rand = new Random();
    
    public EnemyPlugin() {
    }
    
    
    
    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        spawnPoints = new ArrayList<>();
        loadSpawnPoints();
        this.gameData = gameData;
        this.world = world;
        for (int i = 0; i < 4 ; i++) {
            int random = rand.nextInt(-i);
            int[] index = spawnPoints.get(random);
            this.spawnPoints.remove(random);
            Entity enemy = createEnemy(gameData, index);
            world.addEntity(enemy);
        }
    }

    public Entity createEnemy(GameData gameData, int[] spawn) {
        
        //int[] spawnPoint = spawnPoints[rand.nextInt(6)];

        Entity enemy = new Enemy();

        enemy.add(new LifePart(1));
        enemy.setRadius(4);
		MovingPart movingPart = new MovingPart();
        movingPart.setAcceleration(1);
        enemy.add(movingPart);
        enemy.add(new PositionPart(spawn[0], spawn[1]));
        enemy.add(new SpritePart("assets/enemy.png", this.getClass()));

        return enemy;
    }

 public void respawnEnemies(GameData gameData, World world){
        start(this.gameData, this.world);
    }
 
 public void loadSpawnPoints(){
     int[][] arr = {{46, 46}, {100, 629}, {675, 495}, {46, 629}, {39, 523}, {55, 232}, {125, 211}, {988, 46}, {510, 586}, {722, 404}};
     for (int[] point : arr) {
         this.spawnPoints.add(point);
     }

    }
 
    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity enemy : world.getEntities(Enemy.class)) {
			SpritePart sp = enemy.getPart(SpritePart.class);
			sp.dispose();
            world.removeEntity(enemy.getID());
        }
    }
}
