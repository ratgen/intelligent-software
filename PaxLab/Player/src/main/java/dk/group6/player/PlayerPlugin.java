package dk.group6.player;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.LifePart;
import dk.group6.common.data.entityparts.MovingPart;
import dk.group6.common.data.entityparts.PositionPart;
import dk.group6.common.data.entityparts.SpritePart;
import dk.group6.common.data.entityparts.WeaponContainerPart;
import dk.group6.common.player.Player;
import dk.group6.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {

    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        Entity player = createPlayer(gameData);
        world.addEntity(player);
    }

    private Entity createPlayer(GameData gameData) {
        Entity player = new Player();

        player.add(new LifePart(1));
        player.setRadius(4);
        player.add(new MovingPart());
        player.add(new PositionPart(360,540));
        SpritePart sprite = new SpritePart("assets/player.png", this.getClass());
        player.add(sprite);
        WeaponContainerPart weaponPart = new WeaponContainerPart();
        player.add(weaponPart);

        return player;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity player : world.getEntities(Player.class)){
            SpritePart sp = player.getPart(SpritePart.class);
            sp.dispose();
            world.removeEntity(player);
        }
    }
}
