package dk.group6.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.LifePart;
import dk.group6.common.data.entityparts.MovingPart;
import dk.group6.common.data.entityparts.PositionPart;
import dk.group6.common.data.entityparts.SpritePart;
import dk.group6.common.enemy.Enemy;
import dk.group6.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {
    private String enemyID;

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        //Entity enemy = createEnemy(gameData);
        //enemyID = world.addEntity(enemy);
    }

    private Entity createEnemy(GameData gameData) {
        Entity enemy = new Enemy();

        enemy.add(new LifePart(3));
        enemy.setRadius(4);
        enemy.add(new MovingPart());
        enemy.add(new PositionPart(gameData.getDisplayWidth() / 2 ,gameData.getDisplayHeight()/ 2));
        enemy.add(new SpritePart("assets/enemy.png", this.getClass()));
        
        return enemy;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemyID);
    }

}
