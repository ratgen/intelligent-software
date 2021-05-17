package dk.group6.player;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import dk.group6.common.data.GameKeys;
import static dk.group6.common.data.GameKeys.ENTER;
import static dk.group6.common.data.GameKeys.SPACE;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.MovingPart;
import dk.group6.common.data.entityparts.PositionPart;
import dk.group6.common.data.entityparts.SpritePart;
import dk.group6.common.data.entityparts.WeaponContainerPart;
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
            WeaponContainerPart weaponPart = entity.getPart(WeaponContainerPart.class);

            movingPart.setLeft(gameData.getKeys().isDown(GameKeys.LEFT));
            movingPart.setRight(gameData.getKeys().isDown(GameKeys.RIGHT));
            movingPart.setUp(gameData.getKeys().isDown(GameKeys.UP));
            movingPart.setDown(gameData.getKeys().isDown(GameKeys.DOWN));
            
            if (gameData.getKeys().isPressed(GameKeys.ENTER)){
                createWeapon(entity, gameData, world);
            }
            
            if (gameData.getKeys().isPressed(GameKeys.SPACE)) {
		    if (weaponPart.getWeapon() != null) {
			    Weapon weapon = (Weapon) world.getEntity(weaponPart.getWeapon());
			    weaponSystem.attack(weapon, world);
		    }
            }
	    if (weaponPart.getWeapon() != null) {
            
                Weapon weapon = (Weapon) world.getEntity(weaponPart.getWeapon());
                SpritePart sp = weapon.getPart(SpritePart.class);
                PositionPart ps = weapon.getPart(PositionPart.class);
                if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                    ps.setRadians((float) (Math.PI/2)) ;
                    ps.setX(positionPart.getX() - sp.getSprite().getOriginX() - (sp.getSprite().getHeight() / 2));
                    ps.setY(positionPart.getY());
                } else if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                    ps.setRadians((float) (-Math.PI/2));
                    ps.setX(positionPart.getX() + ( spritePart.getSprite().getWidth() - sp.getSprite().getOriginX() ) + (sp.getSprite().getHeight() / 2));
                    ps.setY(positionPart.getY());
                } else if (gameData.getKeys().isDown(GameKeys.UP)){
                    ps.setRadians((float) (0)) ;
                    ps.setX(positionPart.getX() + (spritePart.getSprite().getWidth() / 2) - (sp.getSprite().getOriginX()));
                    ps.setY(positionPart.getY() + (spritePart.getSprite().getHeight()));
                } else if (gameData.getKeys().isDown(GameKeys.DOWN)){
                    ps.setRadians((float) (Math.PI)) ;
                    ps.setX(positionPart.getX() + (spritePart.getSprite().getWidth() / 2) - (sp.getSprite().getOriginX()));
                    ps.setY(positionPart.getY() - (spritePart.getSprite().getHeight()));
                }
	    }
            
            movingPart.process(gameData, entity);
            positionPart.process(gameData, entity);  
            spritePart.process(gameData, entity);
        }
    }
    
    private void createWeapon(Entity entity, GameData gameData, World world){
        PositionPart positionPart = entity.getPart(PositionPart.class);
        WeaponContainerPart weaponPart = entity.getPart(WeaponContainerPart.class);
        String weaponID = weaponSystem.createWeapon(gameData, world);
        weaponPart.addWeapon(weaponID);
        Entity weapon = world.getEntity(weaponID);
        PositionPart ps = weapon.getPart(PositionPart.class);
        ps.setRadians((float) (Math.PI/4 + Math.PI/2)) ;
        ps.setX(positionPart.getX()- 90f);
        ps.setY(positionPart.getY() - 140f );
    }
    
    public void setWeaponSPI(IWeaponSPI weaponSystem) {
        this.weaponSystem = weaponSystem;
    }
    
    public void removeWeaponSPI(IWeaponSPI weaponSystem) {
        this.weaponSystem = null;
    }
    
}
