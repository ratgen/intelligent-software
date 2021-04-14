package dk.group6.common.data.entityparts;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;

public class LifePart implements EntityPart{

    private boolean dead = false;
    private int hitPoints;
    private boolean struck = false;
    
    public LifePart (int hitPoints) {
        this.hitPoints = hitPoints;
    }
    
    public int getHitPoints() {
        return hitPoints;
    }
    
    public void setLife (int hitPoints) {
        this.hitPoints = hitPoints;
    }
    
    public boolean isHit() {
        return struck;
    }
    
    public boolean isDead() {
        return dead;
    }
    
    public void process(GameData gameData, Entity entity) {
        if (struck) {
            hitPoints = -1;
            struck = false;
        }
        if (hitPoints <= 0) {
            dead = true;
        }
    }

}
