package dk.group6.common.data.entityparts;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;

/**
 *
 * @author gruppe 6
 */
public class MovingPart implements EntityPart {

    private boolean left, right, up, down;

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
