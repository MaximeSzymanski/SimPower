package com.utbm.map.area;

import com.utbm.build.buildings.Building;
import com.utbm.map.plot.Plot;

import java.util.Stack;

/**
 * Objet qui contient plusieurs Plot.
 */
public class Area{

    /**
     * Liste de plot qui forme l'area.
     */
    private Stack<Plot> plotList;

    /**
     * Constructeur par défault qui instancie la liste en attribut.
     */
    public Area(){
        this.plotList = new Stack<>();
    }

    /**
     * Constructeur paramétré qui prend en paramètre un nombre indéterminer de Plot.
     *
     * @param p_plotList : Les plots qui formeront l'area.
     */
    public Area(Plot... p_plotList){
        this();

        for(Plot p : p_plotList){
            this.setWalkable();
            this.plotList.add(p);
        }
    }

    /**
     * Cette méthode place un building sur l'area.
     *
     * @param p_build : Building à placer sur l'area.
     */
    public void setBuildingOnPlot(Building p_build){
        for(Plot p : this.plotList){
            p.setBuildingOnIt(p_build);
        }
    }

    /**
     * Ajoute le plot pris en paramètre à la liste de plot en attribut.
     *
     * @param p_plot
     */
    public void add(Plot p_plot){
        getPlotList().push(p_plot);
    }

    /**
     * Cette méthode modifie tous les plot de la liste de l'instance en mettant à true leur attribut walkable.
     */
    public void setWalkable(){

        for(Plot p : this.plotList){
            p.setWalkable(true);
        }
    }

    /**
     * Cette méthode set la pollution avec la valeur en paramètre pour tous les plots de l'instance.
     *
     * @param p_pollution : Le pourcentage de pollution.
     */
    public void setPollution(int p_pollution){
        for(Plot currentPlot : this.plotList){
            currentPlot.setPollutionRate(currentPlot.getPollutionRate() + p_pollution);
        }
    }

    /**
     * Vérifie si l'area est construcible.
     *
     * @return true si l'area est constructible, false sinon.
     */
    public boolean isFree(){

        for(Plot p : this.plotList){
            boolean isFree = p.isWalkable();
            if(isFree == false){
                return false;
            }
        }
        return true;
    }

    public Stack<Plot> getPlotList() {
        return plotList;
    }

    //    public int pollutingEnergy(){
//        int amountOfEnergyArea = 0;
//        for(Plot currentPlot : this.plotList){
//
//            amountOfEnergyArea += currentPlot.getPollutingElectricityValue();
//        }
//        return amountOfEnergyArea;
//    }
//
//    public int greenEnergy(){
//        int amountOfEnergyArea = 0;
//        for(Plot currentPlot : this.plotList){
//
//            amountOfEnergyArea += currentPlot.getGreenElectricityValue();
//        }
//        return amountOfEnergyArea;
//    }
}
