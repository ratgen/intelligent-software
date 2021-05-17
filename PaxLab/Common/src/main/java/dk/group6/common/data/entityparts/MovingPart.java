package dk.group6.common.data.entityparts;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import java.util.ArrayList;

public class MovingPart implements EntityPart {

    private boolean left, right, up, down, straight;

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
    
    public void setStraight(boolean straight) {
        this.straight = straight;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        float delta = gameData.getDelta();
        
        PositionPart positionPart = entity.getPart(PositionPart.class);
        ArrayList<String> validDirections = positionPart.getDirections();
        
        if (left && validDirections.contains("left")) {
            positionPart.setX(positionPart.getX() - 1);
        } else if (right && validDirections.contains("right")) {
            positionPart.setX(positionPart.getX() + 1);
        } // accelerating            
        else if (up && validDirections.contains("up")) {
            positionPart.setY(positionPart.getY() + 1);
        } else if (down && validDirections.contains("down")) {
            positionPart.setY(positionPart.getY() - 1);
        }
        else if (straight) {
            float radians = positionPart.getRadians();
            float dx, dy;
            
            dx = (float) (cos(radians) * delta);
            dy = (float) (sin(radians) * delta);

            float vec = (float) sqrt(dx * dx + dy * dy);

            dx = (dx / vec) * 5;
            dy = (dy / vec) * 5;
            
            float x = positionPart.getX() + dx;
            float y = positionPart.getY() + dy;
            positionPart.setX(x);
            positionPart.setY(y);
        }
    }
}
