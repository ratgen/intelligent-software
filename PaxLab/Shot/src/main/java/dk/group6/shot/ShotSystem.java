/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.shot;

import dk.group6.common.data.Entity;
import dk.group6.common.data.World;
import dk.group6.common.data.entityparts.MovingPart;
import dk.group6.common.data.entityparts.PositionPart;
import dk.group6.common.data.entityparts.SpritePart;
import dk.group6.common.shot.Shot;
import dk.group6.common.shot.ShotSPI;

/**
 *
 * @author peter
 */
public class ShotSystem implements ShotSPI {

    @Override
    public Entity shoot(int x, int y, float radian, World world) {
        Shot shot = createShot(x, y, radian);
        world.addEntity(shot);
        return (Entity) shot;
    }
    
    private Shot createShot(int x, int y, float radian){
        Shot shot = new Shot();
        
        shot.add(new PositionPart((float) x, (float) y));
        shot.add(new MovingPart());
        shot.add(new SpritePart("assets/spritshot.png", this.getClass()));
        
        PositionPart positionPart = shot.getPart(PositionPart.class);
        positionPart.setRadians(radian);
        
        MovingPart movingPart = shot.getPart(MovingPart.class);
        movingPart.setUp(true);
        
        
        
        return shot;
    }
}
