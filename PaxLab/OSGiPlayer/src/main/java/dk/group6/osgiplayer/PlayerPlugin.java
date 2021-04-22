package dk.group6.osgiplayer;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.LifePart;
import dk.group6.common.data.entityparts.MovingPart;
import dk.group6.common.data.entityparts.PositionPart;
import dk.group6.common.player.Player;
import dk.group6.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {
    private String playerID;

    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        Entity player = createPlayerShip(gameData);
        playerID = world.addEntity(player);
        
    }

    private Entity createPlayerShip(GameData gameData) {
        Entity playerShip = new Player();

        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;
        playerShip.add(new LifePart(3));
        playerShip.setRadius(4);
        playerShip.add(new MovingPart());
        playerShip.add(new PositionPart(x, y));
        
        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(playerID);
    }

}
