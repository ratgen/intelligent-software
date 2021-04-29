package dk.group6.map.tiles;

import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.services.IGamePluginService;

public class Tile {

    enum TileEnum {
        WALL("wall.png", true, 0),
        FLOOR("grass.png", false, 1);

        private boolean isWall;
        private String imagePath;
        private int enumID;

        private TileEnum(String imagePath, boolean isWall, int enumID) {
            this.imagePath = imagePath;
            this.isWall = isWall;
            this.enumID = enumID;
        }
        
        public int getEnumID() {
            return enumID;
    }
    }

    private int width, height = 45;
    private int tileID;
    private TileEnum type;


    public Tile(int enumID, int tileID) {
        this.type = getTileEnum(enumID);
        this.tileID = tileID;
    }
    
    private void setEnum(TileEnum e) {
        
    }

    private boolean getIsWall() {
        return this.type.isWall;
    }

    private String getImagePath() {
        return this.type.imagePath;
    }
    
    private TileEnum getTileEnum(int enumID) {
        /*
        * Used in the 2D Array for Map to get TileEnum by ID.
        */
        for (TileEnum e : TileEnum.values()) {
            if (e.getEnumID() == enumID) {
               return e;
            }
        }
        return null;
    }

}
