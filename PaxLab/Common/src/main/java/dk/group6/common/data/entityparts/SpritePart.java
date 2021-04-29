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
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    Texture texture;
    Sprite sprite;


    
    public SpritePart(String image) {
        this.image = image; 
    }
    
    @Override
    public void process(GameData gameData, Entity entity) {
        BundleContext context = FrameworkUtil.getBundle( entity.getClass() ).getBundleContext();
        Bundle bundle = context.getBundle();
        URL imagepath = bundle.getResource(image);
        
        System.out.println(imagepath);
        
        FileHandle fh = new FileHandle(new File(""));
        texture = new Texture(fh);
        PositionPart ps = entity.getPart(PositionPart.class);
        sprite = new Sprite(texture,  (int) ps.getX(), (int) ps.getY(), 1280, 720);
        
      
       
    }
    
    public Sprite getSprite(){
        return sprite;
    }
    
}
