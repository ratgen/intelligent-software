/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.shot;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.SpritePart;
import dk.group6.common.services.IGamePluginService;
import dk.group6.common.shot.Shot;

/**
 *
 * @author peter
 */
public class ShotPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity shot : world.getEntities(Shot.class)){
			SpritePart sp = shot.getPart(SpritePart.class);
			sp.dispose();
            world.removeEntity(shot);
        }
    }
}
