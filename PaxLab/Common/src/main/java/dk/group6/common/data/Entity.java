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

    private float radius;
    private Map<Class, EntityPart> parts;
    private Sprite sprite;

    public Entity() {
        parts = new ConcurrentHashMap<>();
    }

    public Entity(Sprite sprite) {
        this();
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

    public boolean hasPart(Class partClass) {
        return parts.containsKey(partClass);
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
}
