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
        int x_offset = 80;
        int y_offset = 20;
        shotSPI.shoot(
                (int) posPart.getX() + x_offset, 
                (int) posPart.getY() + y_offset, 
                (double) posPart.getRadians() + 3.14, 
                world
        );
    }

    @Override
    public void destroyWeapon(Weapon weapon, World world) {
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

        positionPart.setRadians((float) - Math.PI/4);
        weapon.add(positionPart);
        weapon.add(new WeaponPart(1,1));
        SpritePart spritePart = new SpritePart("assets/syringesmall.png", this.getClass());
        spritePart.setScale(0.25f);
        weapon.add(spritePart);

        return weapon;    
    }
}
