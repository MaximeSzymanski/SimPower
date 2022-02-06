package com.utbm.map.plot;

import com.utbm.build.buildings.Building;
import micrell.utils.grid.Node;
import micrell.utils.math.Math;
import micrell.utils.vector.Vector2;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Classe qui hérite de node. Un plot correspond à un petit carré sur la map de jeu.
 */
public abstract class Plot extends Node  {

    /**
     * Taux de pollution du plot.
     */
    private float pollutionRate;
    /**
     * Taux d'exposition au soleil du plot.
     */
    private int sunExposition;
    /**
     * Taux d'exposition au vent du plot.
     */
    private int windExposition;
    /**
     * Plot parent -> utilisation de cet attribut pour le pathfinder.
     */
    protected Plot parent;
    /**
     * Liste de Plot qui contient tous les voisins du plot (les plots qui ont un coté ou un coin en commun avec le plot)
     */
    protected ArrayList<Plot> neighbours;
//    /**
//     * Permet d'afficher le type de plot en console (Debug)
//     */
//    public String draw;
    /**
     * Null par défault, si un bâtiment est construit sur le plot, une référence sur le bâtiment est ajouter dans cet attribut.
     */
    private Building buildingOnit;
    /**
     * Valeur d'énergie verte dispo sur le plot.
     */
    protected int greenElectricityValue;
    /**
     * Valeur d'énergie polluante dispo sur le plot.
     */
    protected int pollutingElectricityValue;

    /**
     * Constructeyr paramétré.
     *
     * @param p_walkable : défini si le plot est constructible ou non.
     * @param p_worldPos : position du plot sur la map.
     * @param p_gridX : index x du plot dans la grid de la map.
     * @param p_gridY : index y du plot dans la grid de la map.
     */
    public Plot(boolean p_walkable, Vector2 p_worldPos, int p_gridX, int p_gridY){
        super(p_walkable, p_worldPos, p_gridX, p_gridY);
        this.greenElectricityValue = 0;
        this.pollutingElectricityValue = 0;
    }

    /**
     * Constructeur par recopie du plot pris en paramètre.
     *
     * @param p_p : plot à recopier.
     */
    public Plot(Plot p_p){
        super(p_p);
        setNeighbours(p_p.getNeighbours());
        this.greenElectricityValue = p_p.getGreenElectricityValue();
        this.pollutingElectricityValue = p_p.getPollutingElectricityValue();
    }

    /**
     * Méthode qui permet d'obtenir la couronne de plot autour de cet instance à une certaine distance donnée en paramètre.
     * Exemple :
     * radius de 0, on obtient cette instance.
     * radius de 1, on obtient les voisins de cette instance.
     * radius de 2, on obtient les voisins des voisins de cette instance (seulement les voisins extérieurs)
     *
     * @param p_radius : radius voulu
     * @return la liste des plots qui forme la couronne.
     */
    public Stack<Plot> getPlotCrown(int p_radius){

        Stack<Plot> crown = getAllPlotAroundRadius(p_radius);
        Stack<Plot> crownToRemove = getAllPlotAroundRadius(p_radius-1);

        for(Plot p : crownToRemove){
            if(crown.contains(p)){
                crown.remove(p);
            }
        }

        return crown;
    }

    /**
     * Cette méthode permet d'obtenir la liste de tous les plots à une certaine distance du plot.
     *
     * @param p_radius : Le radius voulu.
     * @return la liste des plots.
     */
    private Stack<Plot> getAllPlotAroundRadius(int p_radius){
        Stack<Plot> crown = new Stack<>();
        Stack<Plot> temp = new Stack<>();

        crown.add(this);

        for(int n = 0; n < p_radius; n++){
            for(Plot p : crown){
                for(Plot neighbour : p.getNeighbours()){
                    if(!temp.contains(neighbour)){
                        temp.add(neighbour);
                    }
                }
            }
            crown.addAll(temp);
            temp.clear();
        }

        return crown;
    }

    public void setBuildingOnIt(Building p_building){
        this.buildingOnit = p_building;
    }

    public Building getBuildingOnit(){
        return this.buildingOnit;
    }

    public ArrayList<Plot> getNeighbours(){
        return this.neighbours;
    }

    public void setNeighbours(ArrayList<Plot> neighbours) {
        this.neighbours = neighbours;
    }

    public Plot getParent() {
        return parent;
    }

    public void setParent(Plot parent) {
        this.parent = parent;
    }


    public int getSunExposition() {
        return sunExposition;
    }

    public void setSunExposition(int p_sunExposition) {
        this.sunExposition = p_sunExposition;
    }

    public int getWindExposition() {
        return windExposition;
    }

    public void setWindExposition(int p_windExposition) {
        this.windExposition = p_windExposition;
    }

    public float getPollutionRate() {
        return pollutionRate;
    }

    public void setPollutionRate(float p_pollutionRate) {
        this.pollutionRate = Math.clamp(0,100,p_pollutionRate);
    }



    public int getGreenElectricityValue() {
        return greenElectricityValue;
    }

    public int getPollutingElectricityValue() {
        return pollutingElectricityValue;
    }

    public void setGreenElectricityValue(int greenElectricityValue) {
        this.greenElectricityValue = greenElectricityValue;
    }

    public void setPollutingElectricityValue(int pollutingElectricityValue) {
        this.pollutingElectricityValue = pollutingElectricityValue;
    }


//    /**
//     * Méthode de débug
//     */
//    public void drawSunExpositionValue() {
//        System.out.print("["+ getSunExposition() +"]");
//    }
//
//    public void drawWindExpositionValue() {
//        System.out.print("["+ getSunExposition() +"]");
//    }
    //    @Override
//    public String toString() {
//        return super.toString() +
//                "Plot{" +
//                "pollutionRate=" + pollutionRate +
//                ", draw='" + draw + '\'' +
//                '}';
//    }
}
