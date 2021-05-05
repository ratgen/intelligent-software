package dk.group6.player;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.LifePart;
import dk.group6.common.data.entityparts.MovingPart;
import dk.group6.common.data.entityparts.PositionPart;
import dk.group6.common.data.entityparts.SpritePart;
import dk.group6.common.player.Player;
import dk.group6.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {
    private String playerID;
    
    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        Entity player = createPlayer(gameData);
        playerID = world.addEntity(player);
    }

    private Entity createPlayer(GameData gameData) {
        
        Entity player = new Player();

        float x = gameData.getDisplayWidth() / 3;
        float y = gameData.getDisplayHeight() / 3;
        player.add(new LifePart(3));
        player.setRadius(4);
        player.add(new MovingPart());
        player.add(new PositionPart(0, 0));
        player.add(new SpritePart("assets/player.png", player));
        
        return player;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        System.out.println("remong");
        Entity player = world.getEntity(playerID);
        SpritePart sp = player.getPart(SpritePart.class);
        world.removeEntity(playerID);
    }

}
