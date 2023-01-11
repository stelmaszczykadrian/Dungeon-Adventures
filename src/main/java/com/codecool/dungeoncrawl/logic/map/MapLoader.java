package com.codecool.dungeoncrawl.logic.map;

import com.codecool.dungeoncrawl.gui.Main;

public interface MapLoader {

    GameMap loadMap(Main main,String fileName);
}
