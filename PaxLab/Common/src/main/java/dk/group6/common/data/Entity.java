package dk.group6.common.data;


import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.group6.common.data.entityparts.EntityPart;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();

    private float[] shapeX = new float[4];
    private float[] shapeY = new float[4];
    private float radius;
    private Map<Class, EntityPart> parts;
    private Sprite sprite;
    private FileHandle fH;
    private Map<String, Integer> gridLocation;
       
    public Entity() {
        parts = new ConcurrentHashMap<>();
    }

    public Entity(Sprite sprite) {
        this();
        this.sprite = sprite;
    }
    
    public Entity(FileHandle fH) {
        this();
        this.fH = fH;
    }

    public Map<String, Integer> getGridLocation() {
        return gridLocation;
    }

    public void setGridLocation(int x, int y) {
        this.gridLocation.clear();
        this.gridLocation.put("x", x);
        this.gridLocation.put("y", y);
    }
    
    public Integer getX(){
        return this.gridLocation.get("x");
    }
    
    public Integer getY(){
        return this.gridLocation.get("y");
    }
    
    public void stepInGrid(String axis, int steps) {
        //this.gridLocation.clear();
        //gridLocation.put(axis, gridLocation.get(axis) + steps);
        //this.gridLocation.put("x", x);
        //this.gridLocation.put("y", y);
    }

    public Sprite getSprite() {
        return sprite;
    }
    
    public FileHandle getfH() {
        return fH;
    }
    
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void add(EntityPart part) {
        parts.put(part.getClass(), part);
    }

    public void remove(Class partClass) {
        parts.remove(partClass);
    }

    public <E extends EntityPart> E getPart(Class partClass) {
        return (E) parts.get(partClass);
    }

    public void setRadius(float r) {
        this.radius = r;
    }

    public float getRadius() {
        return radius;
    }

    public String getID() {
        return ID.toString();
    }

    public float[] getShapeX() {
        return shapeX;
    }

    public void setShapeX(float[] shapeX) {
        this.shapeX = shapeX;
    }

    public float[] getShapeY() {
        return shapeY;
    }

    public void setShapeY(float[] shapeY) {
        this.shapeY = shapeY;
    }
}
