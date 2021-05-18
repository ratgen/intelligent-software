package dk.group6.common.data.entityparts;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import java.util.ArrayList;

public class MovingPart implements EntityPart {

    private boolean left, right, up, down, straight;
    private final float acceleration = 200;
    private int a = 1;

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public float getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
    private String[] movement;

    public MovingPart() {
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

    public void setDown(boolean down) {
        this.down = down;
    }
    
    public void reset(){
        down = false;
        up = false;
        left = false;
        right = false;
    }
    
    
    public String[] getMovement(){
        return this.movement;
    }
    
    public void setStraight(boolean straight) {
        this.straight = straight;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        float delta = gameData.getDelta();
        
        PositionPart positionPart = entity.getPart(PositionPart.class);
        ArrayList<String> validDirections = positionPart.getDirections();
	validDirections.add("left");
	validDirections.add("right");
	validDirections.add("up");
	validDirections.add("down");
        
        if (left && validDirections.contains("left")) {
            positionPart.setX((int ) positionPart.getX() - 1);
        } else if (right && validDirections.contains("right")) {
            positionPart.setX((int) positionPart.getX() + 1);
        } // accelerating            
        else if (up && validDirections.contains("up")) {
            positionPart.setY(positionPart.getY() + 1);
        } else if (down && validDirections.contains("down")) {
            positionPart.setY(positionPart.getY() - 1);
        }
        else if (straight) {
            double radians = positionPart.getRadians();
            double dx, dy;
            
            dx = cos(radians) * delta;
            dy = sin(radians) * delta;

            float vec = (float) sqrt(dx * dx + dy * dy);

            dx = (dx / vec) * 5;
            dy = (dy / vec) * 5;
            
            double x = positionPart.getX() + dx;
            double y = positionPart.getY() + dy;
            positionPart.setX((int) x);
            positionPart.setY((int) y);
        }
    }
}
