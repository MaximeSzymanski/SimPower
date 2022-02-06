package com.utbm.build;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.utbm.Inventory;
import com.utbm.Stopwatch;
import com.utbm.build.buildings.Building;
import com.utbm.build.buildings.EnergyProductionBuilding;
import com.utbm.build.buildings.Pylon;
import com.utbm.electricity.ElectricityNetwork;
import com.utbm.map.area.Area;
import com.utbm.map.plot.Plot;
import com.utbm.population.PopulationManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui gère tout les batiments, notamment la construction, la pollution, et l'update des batiments.
 * Cette classe implémente StopwatchObserver car elle observe le chronometre générale du jeu, afin de savoir quand update tous les timer interne aux batiments, selon le Observer Design Pattern.
 */
public class BuildingManager  {

    /**
     * Liste des batiments construit.
     */
    List<Building> buildingList;

    /**
     * Objet servant à construire les batiments.
     */
    public BuildingBuilder builderTool;

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Inventaire du joueur.
     */
    private Inventory inventory;
    /**
     * Gestionnaire de population.
     */
    protected PopulationManager populationManager;

    /**
     * Horloge générale du jeu que cette classe observe.
     */
    private Stopwatch stopwatch;

    /**
     * Gestionnaire du réseau electrique.
     */
    private ElectricityNetwork pylonNetwork;


    /**
     * Constucteur parametré du building manager.
     * @param p_populationManager Population manager, créée dans le game manager.
     * @param p_stopwatch Scheduler général du jeu.
     * @param p_pylonNetwork Réseau electrique du jeu.
     * @param p_playerInv Inventaire du joueur.
     */
    public BuildingManager(PopulationManager p_populationManager, Stopwatch p_stopwatch, ElectricityNetwork p_pylonNetwork, Inventory p_playerInv){
        this.buildingList = new ArrayList<Building>();
        this.builderTool = new BuildingBuilder();
        this.populationManager = p_populationManager;
        this.stopwatch = p_stopwatch;
        this.pylonNetwork = p_pylonNetwork;
        this.inventory = p_playerInv;

    }


    /**
     * Ajoute un nouveau batiment, entré en parametre, sur l'emplacement rentré en parametre. Cette méthode construit le batiment au bon endroit, l'ajoute en tant qu'observateur du gestionnaire de population, et l'ajoute au réseau electrique s'il est proche d'un pylone.
     * @param p_build Batiment à construire.
     * @param p_area Emplacement sur lequel constuire le batiment.
     */
    public void addNewBuilding(Building p_build, Area p_area){
        if(builderTool.isEnoughRessources(p_build,this.inventory) && this.builderTool.isAreaConstructible(p_area)){
            p_build.setArea(p_area);


                this.builderTool.buildBuilding(p_build,this.inventory);
                p_build.getArea().setBuildingOnPlot(p_build);
                this.populationManager.addObserver(p_build);

                this.stopwatch.addObserver(p_build);
                this.buildingList.add(p_build);
                p_build.setPylonNetwork(this.pylonNetwork);
                if(p_build instanceof Pylon && !this.pylonNetwork.containsPylon()){
                    this.pylonNetwork.addVertex(p_build);
                }
                this.pylonNetwork.addVertex(p_build);
                if(p_build instanceof Pylon) {
                    this.pylonNetwork.connectToPylons(p_build);
                }
                this.pylonNetwork.connectToNeighbour(p_build);

                //p_build.getPylonNetwork().getAdjVertices(p_build).removeAll(Collections.singletonList(p_build));

                this.pylonNetwork.diffuseEnergy();

                setPollutionAccordingToPlotRadius(p_build);

            }

        }


    /**
     * Diffuse la pollution du batiment sur les cases autour de celui ci, en fonction de son rayon de polution.
     * @param p_building Batiment polluant.
     */
    private void setPollutionAccordingToPlotRadius(Building p_building) {

        for(Plot startPlot : p_building.getArea().getPlotList()){
            startPlot.setPollutionRate(startPlot.getPollutionRate()+p_building.getPollutionRate());
            for(int i=0;i<p_building.getPollutionRadius();i++){
                for(Plot crownPlot : startPlot.getPlotCrown(i+1)){
                    crownPlot.setPollutionRate(crownPlot.getPollutionRate()+(p_building.getPollutionRate()/(i+2)));
                }
            }
        }
    }


    /**
     * Détruit un batiment du jeu. Cette méthode rend au réseau electrique l'energie consommé par le batiment en question, remet son emplacement constructible, enleve le batiment du réseau et enfin retire le batiment de la liste des batiments construit.
     * @param p_build Batiment à detruire.
     */
    public void removeBuilding(Building p_build){
        if(p_build instanceof EnergyProductionBuilding && ((EnergyProductionBuilding) p_build).isHasWorked()) {

            switch ( p_build.materialProduced) {
                case POLLUTING_ENERGY:
                    this.pylonNetwork.retrieveElectricityOnNetwork(p_build, p_build.getProductionRate(), 0);
                     break;

                case GREEN_ENERGY:
                    this.pylonNetwork.retrieveElectricityOnNetwork(p_build, 0, p_build.getProductionRate());
                    break;
            }
        }
        p_build.getArea().setWalkable();

        p_build.getPylonNetwork().removeVertex(p_build);
        this.stopwatch.removeObserver(p_build);
/* TODO quand on enleve un pylon, ca uopdate pas le network*/

/*TODO faire les polltions enlever*/
        this.populationManager.removePopulation(p_build.getPopulation());
        buildingList.remove(p_build);
        this.pylonNetwork.diffuseEnergy();
    }


    @Override
    public String toString() {
        return "BuildingManager{" +
                "buildingList=" + buildingList +
                '}';
    }




}
