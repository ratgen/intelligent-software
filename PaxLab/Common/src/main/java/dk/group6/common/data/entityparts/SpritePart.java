/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.common.data.entityparts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import java.io.File;
import java.net.URI;

/**
 *
 * @author peter
 */
public class SpritePart implements EntityPart {

    URI image;
    Texture texture;
    Sprite sprite;
    
    public SpritePart(URI image) {
        this.image = image; 
        System.out.println("Image path: " + image);
    }
    
    
    @Override
    public void process(GameData gameData, Entity entity) {
        FileHandle fh;
        File file = new File(image);
        fh = new FileHandle(file);
        texture = new Texture(fh);
        PositionPart ps = entity.getPart(PositionPart.class);
        sprite = new Sprite(texture,  (int) ps.getX(), (int) ps.getY(), 1280, 720);
    }
    
    public Sprite getSprite(){
        return sprite;
    }
    
}
