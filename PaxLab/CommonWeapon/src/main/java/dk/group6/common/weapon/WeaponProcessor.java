package dk.group6.osgiplayer;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import dk.group6.common.data.GameKeys;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.PositionPart;
import dk.group6.common.player.Player;
import dk.group6.common.services.IEntityProcessingService;

public class WeaponProcessor implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity entity : world.getEntities(Weapon.class)) {
            PositionPart positionPart = entity.getPart(PositionPart.class);
            positionPart.process(gameData, entity);            
        }
    }
}
