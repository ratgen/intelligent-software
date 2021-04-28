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
import java.awt.Image;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import sun.awt.image.URLImageSource;

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
        player.add(new PositionPart(x, y));
        try {
            URL ss = this.getClass().getClassLoader().getResource("assets/player.png");
            InputStream in = ss.openStream();
            player.add(new SpritePart(this.getClass().getClassLoader().getResource("assets/player.png").toURI()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return player;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(playerID);
    }

}
