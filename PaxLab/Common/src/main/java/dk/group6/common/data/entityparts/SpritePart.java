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
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 *
 * @author peter
 */
public class SpritePart implements EntityPart {

    String image;
    Texture texture;
    Sprite sprite;
    
    public SpritePart(String image) {
        this.image = image; 
        System.out.println("Image path: " + image);
    }
    
    
    @Override
    public void process(GameData gameData, Entity entity) {
        BundleContext context = FrameworkUtil.getBundle( entity.getClass() ).getBundleContext();
        Bundle file = context.getBundle();
        URL url = file.getResource(image);
        System.out.println("resouce " + url);
        
        try {
             BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
        while(br.ready()){
            System.out.println(br.readLine());
        }
        br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
       
        
        FileHandle fh = new FileHandle("assets/player.png");
        texture = new Texture(fh);
        PositionPart ps = entity.getPart(PositionPart.class);
        sprite = new Sprite(texture,  (int) ps.getX(), (int) ps.getY(), 1280, 720);
    }
    
    public Sprite getSprite(){
        return sprite;
    }
    
}
