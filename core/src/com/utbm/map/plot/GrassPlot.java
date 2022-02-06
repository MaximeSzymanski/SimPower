package com.utbm.map.plot;

import micrell.utils.vector.Vector2;

/**
 * Classe qui hérite de Plot. Représente un petit carré d'herbe sur la map.
 */
public class GrassPlot extends Plot {

    /**
     * Constructeur paramétré.
     *
     * @param p_walkable : Boolean pour savoir si le plot est constructible ou non.
     * @param p_worldPos : position du plot.
     * @param p_gridX : index x du plot dans la grid de la map.
     * @param p_gridY : index y du plot dans la grid de la map.
     */
    public GrassPlot(Boolean p_walkable,Vector2 p_worldPos, int p_gridX, int p_gridY){
        super(p_walkable, p_worldPos, p_gridX, p_gridY);
        //this.draw = new String("[Gr]"); //(Debug)

    }

    /**
     * Constructeur par recopie.
     *
     * @param p_gP : plot à recopier.
     */
    public GrassPlot(Plot p_gP){
        super(p_gP);
        this.setWalkable(true);
        //this.draw = new String("[Gr]"); //(Debug)

    }


//    /**
//    * Méthodes de debug.
//    */
//    @Override
//    public void draw() {
//        System.out.print(draw);
//    }
}
