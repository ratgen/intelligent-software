package dk.group6.common.data.entityparts;

import dk.group6.common.data.Entity;
import dk.group6.common.data.GameData;

public interface EntityPart {
    
    void process (GameData gameData, Entity entity);
    
}
