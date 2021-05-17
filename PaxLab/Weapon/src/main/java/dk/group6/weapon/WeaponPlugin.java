/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.weapon;

import dk.group6.common.services.IGamePluginService;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;

public class WeaponPlugin implements IGamePluginService {
    private String weaponID;

    @Override
    public void start(GameData gameData, World world) {
        
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(weaponID);
    }
    
    
}
