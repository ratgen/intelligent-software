/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.common.data.entityparts;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import java.util.ArrayList;

/**
 *
 * @author peter
 */
public class WeaponContainerPart implements EntityPart {
    private String weapon; 
    
    
    public WeaponContainerPart() {
    }
    
    public String getWeapon() {
        return weapon;
    }
    
    public void addWeapon(String weaponID){
	    this.weapon = weaponID;
    }
    
    public void removeWeapon(String weaponID){
	    this.weapon = null;
    }
    
    @Override
    public void process(GameData gameData, Entity entity) {
    }
}
