/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.group6.common.data.entityparts;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 *
 * @author peter
 */
public class SpritePart implements EntityPart {

    public static final int DEFAULT_BUFFER_SIZE = 8192;
    String image;
    Texture texture = null;
    Sprite sprite;
    File file;
    
    /**
     * SpritePart constructor
     *
     * @param image String: relative path of the image in the component
     * @param entity Class: class of the entity, which is placed in the component, where
     * the image is loaded from. 
     */
    public SpritePart(String image, Class entity) {
        this.image = image; 
        BundleContext context = FrameworkUtil.getBundle( entity ).getBundleContext();
        Bundle bundle = context.getBundle();
        URL url = bundle.getResource(image);
        try {
            //check if destroyed on unload
            file = File.createTempFile(image, "tmp");
            FileOutputStream fs = new FileOutputStream(file);
            BufferedInputStream input = new BufferedInputStream(url.openConnection().getInputStream());
            while (input.available() > 0 ) {
                int bytes = input.read();
                fs.write(bytes);
            }
            fs.close();
            input.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void process(GameData gameData, Entity entity) {
        PositionPart ps = entity.getPart(PositionPart.class);
        if (texture == null) {
            FileHandle fh = new FileHandle(file);
            texture = new Texture(fh);
        }
        if (sprite == null) {
            sprite = new Sprite(texture);
        }
        sprite.setPosition(ps.getX(), ps.getY());
        float degrees = (float) (ps.getRadians()  * (180/Math.PI));
        sprite.setRotation(degrees);
    }
    
    public void dispose(){
        file.delete();
    }
    
    public Sprite getSprite(){
        return sprite;
    }
    
    public int[] getSpriteLeftBottom() {
        int[] coordinates = new int[2];
        coordinates[0] = (int) sprite.getX();
        coordinates[1] = (int) sprite.getY();
        return coordinates;
    }
    
    public int[] getSpriteRightBottom() {
        int[] coordinates = new int[2];
        coordinates[0] = (int) (sprite.getX() + sprite.getWidth());
        coordinates[1] = (int) sprite.getY();
        return coordinates;
    }
    
    public int[] getSpriteLeftTop() {
        int[] coordinates = new int[2];
        coordinates[0] = (int) sprite.getX();
        coordinates[1] = (int) (sprite.getY() + sprite.getHeight());
        return coordinates;
    }
    
    public int[] getSpriteRightTop() {
        int[] coordinates = new int[2];
        coordinates[0] = (int) (sprite.getX() + sprite.getWidth());
        coordinates[1] = (int) (sprite.getY() + sprite.getHeight()); 
        return coordinates;
    }
}
