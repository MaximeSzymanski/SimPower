package com.utbm.map;


import com.utbm.build.buildings.Building;
import com.utbm.map.plot.Plot;
import micrell.utils.grid.Grid;
import micrell.utils.vector.Vector2;
import java.util.ArrayList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Cette classe contiendra la map du jeu, elle hérite de la classe générique Grid et le type générique est obligatoirement de type Plot.
 */
public class GroundMap extends Grid<Plot> {

    /**
     * environmentManager s'occupe de gérer l'environement de la map.
     */
    private EnvironmentManager environmentManager;

    /**
     * Constructeur paramétré
     *
     * @param p_worldSize : la taille de la map
     * @param p_plotRadius : le radius d'un plot
     */
    public GroundMap(Vector2 p_worldSize, float p_plotRadius) {
        super(p_worldSize, p_plotRadius);
        this.environmentManager = new EnvironmentManager(this);
    }

    /**
     * Methode qui permet de remplacer un plot par un autre dans la map.
     * L'actualisation des voisins est faite par cette méthode.
     *
     * @param p_a : le plot à changer
     * @param p_b : le nouveau plot
     */
    public void replacePlotToAnother(Plot p_a, Plot p_b){

        if(p_a == p_b){
            return;
        }
        changeNodeInGrid(p_a.getGridX(), p_a.getGridY(), p_b);
        for(Plot n : getNodeInGrid(p_b.getGridX(),p_b.getGridY()).getNeighbours()){
            n.setNeighbours(this.searchNeighbours(n));
        }
    }

    /**
     * Pour chaque plot de la map, on lui ajoute ses voisins pour que chaque plot ait une référence sur ses voisins.
     */
    public void setNodeNeighbours(){
        for(int x = 0 ; x < getGridSizeX() ; x++){
            for(int y = 0 ; y < getGridSizeY() ; y++){

                getNodeInGrid(x,y).setNeighbours(searchNeighbours(getNodeInGrid(x,y)));
            }
        }
    }

    /**
     * Cette méthode permet de chercher les voisins d'un plot prit en paramètre.
     *
     * @param p_plot : Le plot dont on veut les voisins.
     * @return : Une liste qui contient les voisins du plot pris en paramètre.
     */
    public ArrayList<Plot> searchNeighbours(Plot p_plot){
        ArrayList<Plot> neighbours = new ArrayList<>();
        for(int x = -1; x <= 1; x++){
            for(int y = -1; y <= 1; y++){
                if( x ==0 && y == 0){
                    continue;
                }
                int checkX = p_plot.getGridX() + x;
                int checkY = p_plot.getGridY() + y;
                if(checkX >= 0 && checkX < getGridSizeX() && checkY >= 0 && checkY < getGridSizeY()){ //Vérifie si les coordonnées sont théoriquement possible et pas en déhors des limites de la map
                    neighbours.add(getNodeInGrid(checkX, checkY));
                }
            }
        }
        return neighbours;
    }

    /**
     * Cette méthode permet de connaître la distance entre deux bâtiments sur la map.
     *
     * @param p_buildingOne : premier bâtiment.
     * @param buildingVisited : deuxième bâtiment.
     * @return : La distance entre les deux building.
     */
    public int distanceBetweenTwoBuilding(Building p_buildingOne, Building buildingVisited) {
        return (int) sqrt(pow((p_buildingOne.getArea().getPlotList().get(0).getGridX() - buildingVisited.getArea().getPlotList().get(0).getGridX()),2) + pow((p_buildingOne.getArea().getPlotList().get(0).getGridY() - buildingVisited.getArea().getPlotList().get(0).getGridY()),2));
    }

    public EnvironmentManager getEnvironmentManager() {
        return environmentManager;
    }

    public ArrayList<ArrayList<Plot>> getPlotList(){
        return this.gridArray;
    }

//    /**
//     * Méthodes de debug
//     */
//
//    public void drawSunExposition(){
//        for(int x = 0 ; x < getGridSizeX() ; x++){
//            for(int y = 0 ; y < getGridSizeY() ; y++){
//                Plot p = getNodeInGrid(x, y);
//                System.out.print("[" + p.getSunExposition() + "]");
//            }
//            System.out.println();
//        }
//    }
//
//    public void draw(){
//        for(int x = 0 ; x < getGridSizeX() ; x++){
//            for(int y = 0 ; y < getGridSizeY() ; y++){
//
//                getNodeInGrid(x, y).draw();
//            }
//            System.out.println();
//        }
//    }
//
//    public void drawNoise(){
//        for(int x = 0 ; x < getGridSizeX() ; x++){
//            for(int y = 0 ; y < getGridSizeY() ; y++){
//
//                getNodeInGrid(x, y).drawNoiseValue();
//            }
//            System.out.println();
//        }
//    }
//
//    public void drawPollution(){
//        for(int x = 0 ; x < getGridSizeX() ; x++){
//            for(int y = 0 ; y < getGridSizeY() ; y++){
//                Plot p = getNodeInGrid(x, y);
//                System.out.print("[" + p.getPollutionRate() + "]");
//            }
//            System.out.println();
//        }
//    }

}
