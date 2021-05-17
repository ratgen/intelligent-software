package dk.group6.weapon;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import dk.group6.common.weapon.Weapon;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.PositionPart;
import dk.group6.common.data.entityparts.SpritePart;
import dk.group6.common.data.entityparts.WeaponPart;
import dk.group6.common.services.IEntityProcessingService;

public class WeaponProcessor implements IEntityProcessingService {
    
    @Override
    public void process(GameData gameData, World world) {

        for (Entity entity : world.getEntities(Weapon.class)) {
            SpritePart spritePart = entity.getPart(SpritePart.class);
            PositionPart positionPart = entity.getPart(PositionPart.class);
            WeaponPart weaponPart = entity.getPart(WeaponPart.class);
            
            positionPart.process(gameData, entity);            
            spritePart.process(gameData, entity);
            weaponPart.process(gameData, entity);
        }
    }
}
