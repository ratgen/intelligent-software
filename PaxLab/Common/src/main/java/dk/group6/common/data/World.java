package dk.group6.common.data;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author group6
 */
public class World {
    private TiledMapTileLayer mapTileLayer;
    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();
    
    public String addEntity(Entity entity) {
        entityMap.put(entity.getID(), entity);
        return entity.getID();
    }

    public void removeEntity(String entityID) {
        entityMap.remove(entityID);
    }

    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getID());
    }
    
    public Collection<Entity> getEntities() {
        return entityMap.values();
    }

    public <E extends Entity> List<Entity> getEntities(Class<E>... entityTypes) {
        List<Entity> r = new ArrayList<>();
        for (Entity e : getEntities()) {
            for (Class<E> entityType : entityTypes) {
                if (entityType.equals(e.getClass())) {
                    r.add(e);
                }
            }
        }
        return r;
    }
    
    public void setMapTileLayer(TiledMapTileLayer tileLayer) {
        mapTileLayer = tileLayer;
    }
    
    public TiledMapTileLayer getMapTileLayer() {
        return this.mapTileLayer;
    }
    // TODO LAV METODE FOR AT HENTE ALLE EKSISTERENDE TILES SAMME MÅDE SOM getEntities()

    public Entity getEntity(String ID) {
        return entityMap.get(ID);
    }

}
