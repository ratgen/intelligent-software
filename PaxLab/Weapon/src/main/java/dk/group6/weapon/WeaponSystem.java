package dk.group6.weapon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.PositionPart;
import dk.group6.common.data.entityparts.SpritePart;
import dk.group6.common.data.entityparts.WeaponPart;
import dk.group6.common.shot.ShotSPI;
import dk.group6.common.weapon.IWeaponSPI;
import dk.group6.common.weapon.Weapon;


/**
 *
 * @author peter
 */
public class WeaponSystem implements IWeaponSPI {
    ShotSPI shotSPI;

    @Override
    public void attack(Weapon weapon, World world) {
        PositionPart posPart = weapon.getPart(PositionPart.class);
        SpritePart sp = weapon.getPart(SpritePart.class);
	WeaponPart wp = weapon.getPart(WeaponPart.class);
		if (wp.canFire()) {
			int x_offset = (int) sp.getSprite().getOriginX();
			int y_offset = (int) sp.getSprite().getHeight();
			int degrees = (int) (posPart.getRadians() * (180f/Math.PI)) ;
			switch (degrees) {
				case 0:
					//up position
					shotSPI.shoot(
						(int) posPart.getX() + x_offset / 2,
						(int) posPart.getY() + y_offset,
						posPart.getRadians() ,
						world
					);
					wp.fire();
					break;
				case 90:
					//left positoin
					shotSPI.shoot(
						(int) posPart.getX() - y_offset/2,
						(int) posPart.getY() + (int) sp.getSprite().getOriginY() - x_offset / 2,
						posPart.getRadians(),
						world
					);		    
					wp.fire();
					break;
				case -90:
					//right position
					shotSPI.shoot(
						(int) posPart.getX() + y_offset/2,
						(int) posPart.getY() + (int) sp.getSprite().getOriginY() - x_offset / 2,
						posPart.getRadians(),
						world
					);
					wp.fire();
					break;
				case 180:
					//down position
					shotSPI.shoot(
						(int) posPart.getX() + x_offset / 2,
						(int) posPart.getY(),
						posPart.getRadians(),
						world
					);
					wp.fire();
					break;
					default:
					break;
			}
		System.out.println("ammo left: " + wp.getAmmo());

		}
    }

    @Override public void destroyWeapon(Weapon weapon, World world) {
        world.removeEntity(weapon);
    }

    

    @Override
    public String createWeapon(GameData gameData, World world) {
        Weapon weapon = weapon(gameData);
        world.addEntity(weapon);
        return weapon.getID();
    }
    
    public void setShot(ShotSPI shotSPI){
        this.shotSPI = shotSPI;
    }
    
    public void removeShot(ShotSPI shotSPI){
        this.shotSPI = null;
    }
    
    private Weapon weapon(GameData gameData) {
        Weapon weapon = new Weapon();
        
        PositionPart positionPart = new PositionPart(50 ,100);
        positionPart.setRadians(0);
        weapon.add(positionPart);
        weapon.add(new WeaponPart(3, 100));
        SpritePart spritePart = new SpritePart("assets/syringesmall.png", this.getClass());
        weapon.add(spritePart);

        return weapon;    
    }
}
