package dk.group6.map.tiles;

import dk.group6.common.data.GameData;
import dk.group6.common.data.World;
import dk.group6.common.services.IGamePluginService;

public class Tile {

    enum TileEnum {
        WALL(true, 0),
        FLOOR(false, 1);

        private boolean isWall;
        private int enumID;

        private TileEnum(boolean isWall, int enumID) {
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
    private final float[] tileCoordinates;


    public Tile(int enumID, int tileID, float[] tileCoordinates) {
        this.type = getTileEnum(enumID);
        this.tileID = tileID;
        this.tileCoordinates = tileCoordinates;
    }
    
    private void setEnum(TileEnum e) {
        
    }

    private boolean getIsWall() {
        return this.type.isWall;
    }

    public float[] getTileCoordinates() {
        return tileCoordinates;
    }
    
    private TileEnum getTileEnum(int enumID) {

        //Used in the 2D Array for Map to get TileEnum by ID.

        for (TileEnum e : TileEnum.values()) {
            if (e.getEnumID() == enumID) {
               return e;
            }
        }
        return null;
    }

}
