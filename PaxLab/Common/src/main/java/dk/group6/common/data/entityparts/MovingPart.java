package dk.group6.common.data.entityparts;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import java.lang.Math;
import java.util.HashMap;

public class MovingPart implements EntityPart {

    private boolean left, right, up, down, straight;
    private int acceleration = 1;
    private HashMap<String, Integer> wallDistance = new HashMap<>();

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

    public void setLeftDistance(int dist){
        if (wallDistance.containsKey("left")) {
            wallDistance.replace("left", dist);
        } else {
            wallDistance.put("left", dist);
        } 
    }
    public void setRightDistance(int dist){
        if (wallDistance.containsKey("right")) {
            wallDistance.replace("right", dist);
        } else {
            wallDistance.put("right", dist);
        } 
    }
    public void setUpDistance(int dist){
        if (wallDistance.containsKey("up")) {
            wallDistance.replace("up", dist);
        } else {
            wallDistance.put("up", dist);
        } 
    }
    public void setDownDistance(int dist){
        if (wallDistance.containsKey("down")) {
            wallDistance.replace("down", dist);
        } else {
            wallDistance.put("down", dist);
        } 
    }
    public int getDist(String direction) throws NullPointerException {
        return wallDistance.get(direction);
    }



    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }
    private String[] movement;

    public MovingPart() {
    }


    public String[] getMovement(){
        return this.movement;
    }

    public void setStraight(boolean straight) {
        this.straight = straight;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        double delta = gameData.getDelta();
        PositionPart positionPart = entity.getPart(PositionPart.class);

        if (left && wallDistance.containsKey("left")) {
            if (wallDistance.get("left") > acceleration){
                int move = positionPart.getX() - acceleration;
                positionPart.setX(move);
            }
        } else if (right && wallDistance.containsKey("right")) {
            if (wallDistance.get("right") > acceleration){
                int move = positionPart.getX() + acceleration ;
                positionPart.setX(move);
            }
        } // accelerating            
        else if (up && wallDistance.containsKey("up")) {
            if (wallDistance.get("up") > acceleration){
                int move = positionPart.getY() + acceleration;
                positionPart.setY(move);
            }
        } else if (down && wallDistance.containsKey("down")) {
            if (wallDistance.get("down") > acceleration){
                int move = positionPart.getY() - acceleration ;
                positionPart.setY(move);
            }
        }
            else if (straight) {
                double radians = positionPart.getRadians();
                double dx, dy;

                dx =  Math.cos(radians) * delta;
                dy =  Math.sin(radians) * delta;

                float vec = (float) Math.sqrt(dx * dx + dy * dy);

                dx = (dx / vec) * 5;
                dy = (dy / vec) * 5;

                double x = positionPart.getX() + dx;
                double y = positionPart.getY() + dy;
                positionPart.setX((int) x);
                positionPart.setY((int) y);
            }
            up = false;
            down = false;
            right = false;
            left = false;
    }
}
