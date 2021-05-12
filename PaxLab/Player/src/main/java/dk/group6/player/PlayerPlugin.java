package dk.group6.player;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.LifePart;
import dk.group6.common.data.entityparts.MovingPart;
import dk.group6.common.data.entityparts.PositionPart;
import dk.group6.common.data.entityparts.SpritePart;
import dk.group6.common.data.entityparts.WeaponPart;
import dk.group6.common.player.Player;
import dk.group6.common.services.IGamePluginService;
import dk.group6.common.weapon.IWeaponSPI;

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
 
        player.add(new LifePart(3));
        player.setRadius(4);
        player.add(new MovingPart());
        player.add(new PositionPart(gameData.getDisplayWidth() / 2 ,gameData.getDisplayHeight()/ 2));
        SpritePart sprite = new SpritePart("assets/player.png", this.getClass());
        WeaponPart weaponPart = new WeaponPart(1000, 1);
        player.add(weaponPart);
        sprite.setScale(1f);
        player.add(sprite);
        
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
