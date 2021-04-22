/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.common.data.entityparts;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 *
 * @author Alexander
 */
public class MovingPart implements EntityPart {

    private float dx, dy;
    private float acceleration;
    private float maxSpeed, rotationSpeed;
    private boolean left, right, up, down;

    public MovingPart() {
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }
    

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    
    public void setSpeed(float speed) {
        this.acceleration = speed;
        this.maxSpeed = speed;
    }

    public void setRotationSpeed(float rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }
    
    public void setDown(boolean down){
        this.down = down;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        PositionPart positionPart = entity.getPart(PositionPart.class);

        // turning
        if (left) {
            positionPart.setX(positionPart.getX() - 1); 
        }

        if (right) {
           positionPart.setX(positionPart.getX() + 1);
        }

        // accelerating            
        if (up) {
            positionPart.setY(positionPart.getY() + 1);              
        } 
        
        if (down){
            positionPart.setY(positionPart.getY() - 1);  
        }
    }

}
