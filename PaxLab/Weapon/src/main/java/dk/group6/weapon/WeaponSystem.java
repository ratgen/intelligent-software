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
        
        shotSPI.shoot((int) posPart.getX(), (int) posPart.getY(), posPart.getRadians(), world);
    }

    @Override
    public void destroyWeapon(Weapon weapon, World world) {
        world.removeEntity(weapon);
    }

    

    @Override
    public String createWeapon(GameData gameData) {
        Weapon weapon = WeaponPlugin.createWeapon(gameData);
        
        return weapon.getID();
    }
    
    public void setShot(ShotSPI shotSPI){
        this.shotSPI = shotSPI;
    }
    
    public void removeShot(ShotSPI shotSPI){
        this.shotSPI = null;
    }
}
