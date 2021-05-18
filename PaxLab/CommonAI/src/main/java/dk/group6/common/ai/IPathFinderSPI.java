/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.common.ai;

import dk.group6.common.data.Entity;
import dk.group6.common.data.World;
import java.util.ArrayList;

/**
 *
 * @author peter
 */
public interface IPathFinderSPI {
    ArrayList<String> track(Entity from, Entity to, World world);
}
