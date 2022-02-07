package model.map;

import model.utils.vector.Vector2;

/**
 * Objet qui permet d'initialiser une map de jeu et de gérer les modifications faites sur la map pendant le jeu.
 */
public class MapManager{

    /**
     * Objet de type GroundMap qui contiendra la map générée.
     */
    private final GroundMap groundMap;

    /**
     * Constructeur paramétré qui permet de créer un MapManager.
     *
     * @param p_worldSize : taille de la map
     * @param p_plotRadius : radius de chaque plot
     */
    public MapManager(Vector2 p_worldSize, float p_plotRadius) {

        MapGenerator mapGenerator = new MapGenerator(); //50000 //9999999

        this.groundMap = mapGenerator.generateMap(p_worldSize, p_plotRadius, 3);
        this.groundMap.getEnvironmentManager().changeEnvironmentExposition();
    }

    /**
     * Getter qui retourne la map.
     *
     * @return groundMap : Map du jeu
     */
    public GroundMap getGroundMap() {
        return groundMap;
    }

}
