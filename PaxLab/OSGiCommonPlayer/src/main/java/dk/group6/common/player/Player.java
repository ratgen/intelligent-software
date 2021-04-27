package dk.group6.common.player;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.group6.common.data.Entity;

/**
 *
 * @author corfixen
 */
public class Player extends Entity {
    
     public Player() {
        super();
    }
    
    public Player(FileHandle fH) {
        super(fH);
    }
     
    public Player(Sprite sprite) {
        super(sprite);
    }
}
