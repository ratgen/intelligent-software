package dk.group6.common.services;

import dk.group6.common.data.GameData;
import dk.group6.common.data.World;

public interface IGamePluginService {
    void start(GameData gameData, World world);

    void stop(GameData gameData, World world);
    
}
