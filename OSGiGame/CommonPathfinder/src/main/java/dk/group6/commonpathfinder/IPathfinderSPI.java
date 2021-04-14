/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.commonpathfinder;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import dk.group6.common.data.World;

/**
 *
 * @author peter
 */
public interface IPathfinderSPI {
    public float track(Entity from, Entity to, GameData gameData, World world);
}
