package dk.group6.common.services;

import dk.group6.common.data.GameData;
import dk.group6.common.data.World;

public interface IPostEntityProcessingService {
    void process(GameData gameData, World world);
}
