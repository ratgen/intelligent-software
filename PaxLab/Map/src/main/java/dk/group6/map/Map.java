package dk.group6.map;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import dk.group6.common.map.MapSPI;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author peter
 */
public class Map implements MapSPI {
    
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private TiledMapTileLayer mapTileLayer;
	File dirFile = null;
	boolean knownGameOver = false;
    
    @Override
    public void createMap() {
		String mapPath = "assets/map/";
		String mapFilePath ="test.tmx";
        try {
			File mapFile = loadTmxFile(mapPath, mapFilePath);

			System.out.println("creating map");

			map = new TmxMapLoader().load(mapFile.getAbsolutePath());
			renderer = new OrthogonalTiledMapRenderer(map);   
			mapTileLayer = (TiledMapTileLayer) this.getMap().getLayers().get("Tile Layer 1");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

	private File loadTmxFile(String mapPath, String mapFilePath) throws FileNotFoundException{
        try {
			if (dirFile == null) {
				Path dir = Files.createTempDirectory("assets");
				dirFile = dir.toFile();
			}

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			File mapFile = loadFileToDir(dirFile, mapPath, mapFilePath);
			
			Document doc = docBuilder.parse(mapFile);
			NodeList nodeList = doc.getElementsByTagName("tileset");

			ArrayList<File> tsxFileList = new ArrayList();
			for (int i = 0; i < nodeList.getLength(); i++) {
				System.out.println("parsing tmx");
				Node node = nodeList.item(i);
				String tsxDependency =  node.getAttributes().getNamedItem("source").getTextContent();
				tsxFileList.add(loadFileToDir(dirFile, mapPath, tsxDependency));
			}

			for (File file : tsxFileList){
				System.out.println("parsing the contents of tsx");
				System.out.println(file.getName());
				doc = docBuilder.parse(file);
				nodeList = doc.getElementsByTagName("image");
				for (int i = 0; i < nodeList.getLength(); i++) {
					System.out.println("parsing image dependendies of tsx");
					Node node = nodeList.item(i);
					String imageDependency =  node.getAttributes().getNamedItem("source").getTextContent();
					loadFileToDir(dirFile, mapPath, imageDependency);
				}
			}
			return mapFile;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
		throw new FileNotFoundException("file could not be found, or loaded");
	}

	private File loadFileToDir(File folder, String filePath, String fileName) throws FileNotFoundException, IOException {
		BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		Bundle bundle = context.getBundle();
		URL url = bundle.getResource(filePath + fileName);
		File newFile = new File(folder, fileName);
		FileOutputStream fs = new FileOutputStream(newFile);
		BufferedInputStream input = new BufferedInputStream(url.openConnection().getInputStream());
		while (input.available() > 0 ) {
			int bytes = input.read();
			fs.write(bytes);
		}
		fs.close();
		input.close();
		return newFile;
	}

    @Override
    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }
    
    @Override
    public TiledMap getMap() {
        return map;
    }
   
    @Override
    public TiledMapTileLayer getMapTileLayer() {
        return mapTileLayer;
    }

    @Override
    public Boolean isWall(int x, int y){
        return mapTileLayer.getCell(x/45, y/45).getTile().getProperties().containsKey("Wall");
    }

    @Override
    public void gameLost() {
		if (!knownGameOver){
			try {
				String filePath = loadTmxFile("assets/map/", "Gameover.tmx").getAbsolutePath();
				this.map = new TmxMapLoader().load(filePath);
				mapTileLayer = (TiledMapTileLayer) this.getMap().getLayers().get("Tile Layer 1");
				renderer.setMap(map);
				knownGameOver = true;
			}
			catch (Exception e){
				e.printStackTrace();
			}
    	}
    }
    
    @Override
    public void gameWon() {
		try {
			String filePath = loadTmxFile("assets/map/", "Won.tmx").getAbsolutePath();
			this.map = new TmxMapLoader().load(filePath);
			mapTileLayer = (TiledMapTileLayer) this.getMap().getLayers().get("Tile Layer 1");
			renderer.setMap(map);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override 
	public void unload(){
		System.out.println("unloading map");
		for (File i : dirFile.listFiles()){
			System.out.println(i.getName());
			i.delete();
		}
		dirFile.delete();
	}

}
