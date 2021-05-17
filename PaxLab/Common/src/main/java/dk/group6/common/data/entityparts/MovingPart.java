package dk.group6.common.data.entityparts;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class MovingPart implements EntityPart {

    private boolean left, right, up, down, straight;
    private final float acceleration = 200;

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
    private float a = 1;

    public float getA() {
        return a;
    }

    public void setA(float a) {
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
        if (left) {
            positionPart.setX(positionPart.getX() - a);
        } else if (right) {
            positionPart.setX(positionPart.getX() + a);
        } // accelerating            
        else if (up) {
            positionPart.setY(positionPart.getY() + a);
        } else if (down) {
            positionPart.setY(positionPart.getY() - a);
        }
        else if (straight) {
            float radians = positionPart.getRadians();
            float dx, dy;
            
            dx = (float) (cos(radians) * delta);
            dy = (float) (sin(radians) * delta);

            float vec = (float) sqrt(dx * dx + dy * dy);

            dx = (dx / vec) * 10;
            dy = (dy / vec) * 10;
            
            float x = positionPart.getX() + dx;
            float y = positionPart.getY() + dy;
            positionPart.setX(x);
            positionPart.setY(y);
        }
    }
}
