package dk.group6.player;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import dk.group6.common.data.GameKeys;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.MovingPart;
import dk.group6.common.data.entityparts.PositionPart;
import dk.group6.common.data.entityparts.SpritePart;
import dk.group6.common.data.entityparts.WeaponPart;
import dk.group6.common.player.Player;
import dk.group6.common.services.IEntityProcessingService;
import dk.group6.common.weapon.IWeaponSPI;
import dk.group6.common.weapon.Weapon;

public class PlayerProcessor implements IEntityProcessingService {

    private IWeaponSPI weaponSystem;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Player.class)) {

            PositionPart positionPart = entity.getPart(PositionPart.class);
            MovingPart movingPart = entity.getPart(MovingPart.class);
            SpritePart spritePart = entity.getPart(SpritePart.class);
            WeaponPart weaponPart = entity.getPart(WeaponPart.class);

            movingPart.setLeft(gameData.getKeys().isDown(GameKeys.LEFT));
            movingPart.setRight(gameData.getKeys().isDown(GameKeys.RIGHT));
            movingPart.setUp(gameData.getKeys().isDown(GameKeys.UP));
            movingPart.setDown(gameData.getKeys().isDown(GameKeys.DOWN));
            
            if (gameData.getKeys().isDown(GameKeys.ENTER)){
                System.out.println("pressed enter");
                createWeapon(entity, gameData, world);
            }
            
            if (gameData.getKeys().isDown(GameKeys.SPACE)) {
                System.out.println("Pressed space");
                for (String weaponID: weaponPart.getWeapons()){
                    Weapon weapon = (Weapon) world.getEntity (weaponID);
                    weaponSystem.attack(weapon, world);
                }
            }
            
            movingPart.process(gameData, entity);
            positionPart.process(gameData, entity);  
            spritePart.process(gameData, entity);
        }
    }
    
    private void createWeapon(Entity entity, GameData gameData, World world){
        WeaponPart weaponPart = entity.getPart(WeaponPart.class);
        weaponPart.addWeapon(weaponSystem.createWeapon(gameData, world));
    }
    
    public void setWeaponSPI(IWeaponSPI weaponSystem) {
        this.weaponSystem = weaponSystem;
    }
    
    public void removeWeaponSPI(IWeaponSPI weaponSystem) {
        this.weaponSystem = null;
    }
    
}
