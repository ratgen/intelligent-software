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
    float scale = 1;
    
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
        sprite.setScale(scale);
        sprite.setPosition(ps.getX(), ps.getY());
        sprite.setRotation((float) ((float) ps.getRadians() * 180/Math.PI));
    }
    
    public void setScale(float scale) {
        this.scale = scale;
    }
    
    public void dispose(){
        texture.dispose();
        file.delete();
    }
    
    public Sprite getSprite(){
        return sprite;
    }
    
    public float[] getSpriteLeftBottom() {
        float[] coordinates = new float[2];
        coordinates[0] = sprite.getX();
        coordinates[1] = sprite.getY();
        return coordinates;
    }
    
    public float[] getSpriteRightBottom() {
        float[] coordinates = new float[2];
        coordinates[0] = sprite.getX() + sprite.getWidth();
        coordinates[1] = sprite.getY();
        return coordinates;
    }
    
    public float[] getSpriteLeftTop() {
        float[] coordinates = new float[2];
        coordinates[0] = sprite.getX();
        coordinates[1] = sprite.getY() + sprite.getHeight();
        return coordinates;
    }
    
    public float[] getSpriteRightTop() {
        float[] coordinates = new float[2];
        coordinates[0] = sprite.getX() + sprite.getWidth();
        coordinates[1] = sprite.getY() + sprite.getHeight();
        return coordinates;
    }
}
