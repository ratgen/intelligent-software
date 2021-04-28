package dk.group6.osgienemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.LifePart;
import dk.group6.common.data.entityparts.MovingPart;
import dk.group6.common.data.entityparts.PositionPart;
import dk.group6.common.enemy.Enemy;
import dk.group6.common.services.IGamePluginService;

public class EnemyPlugin implements IGamePluginService {
    private String enemyID;

    public EnemyPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        Entity enemy = createEnemy(gameData);
        enemyID = world.addEntity(enemy);
        
    }

    private Entity createEnemy(GameData gameData) {
        FileHandle fH = Gdx.files.internal("../OSGiEnemy/src/main/resources/assets/enemy.png");
        Entity enemy = new Enemy(fH);

        float x = gameData.getDisplayWidth() / 3;
        float y = gameData.getDisplayHeight() / 3;
        enemy.add(new LifePart(3));
        enemy.setRadius(4);
        enemy.add(new MovingPart());
        enemy.add(new PositionPart(x, y));
        
        return enemy;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemyID);
    }

}
