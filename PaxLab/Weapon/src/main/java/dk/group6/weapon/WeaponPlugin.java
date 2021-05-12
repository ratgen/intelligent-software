/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.weapon;

import dk.group6.common.data.Entity;
import dk.group6.common.services.IGamePluginService;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.PositionPart;
import dk.group6.common.data.entityparts.SpritePart;
import dk.group6.common.data.entityparts.WeaponPart;
import dk.group6.common.weapon.Weapon;

public class WeaponPlugin implements IGamePluginService {
    private String weaponID;

    @Override
    public void start(GameData gameData, World world) {
        //Entity weapon = createWeapon(gameData);
        //weaponID = world.addEntity(weapon);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(weaponID);
    }
    
    protected static Weapon createWeapon(GameData gameData) {
        Weapon weapon = new Weapon();
        
        PositionPart positionPart = new PositionPart(gameData.getDisplayWidth() / 2 ,gameData.getDisplayHeight()/ 2);

        positionPart.setRadians(3f);
        weapon.add(positionPart);
        weapon.add(new WeaponPart(1,1));
        SpritePart spritePart = new SpritePart("assets/syringe.png", weapon);
        spritePart.setScale(0.25f);
        weapon.add(spritePart);

        return weapon;    
    }
}
