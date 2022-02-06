package com.utbm;

import com.utbm.build.BuildingManager;
import com.utbm.build.buildings.CoalMine;
import com.utbm.build.buildings.House;
import com.utbm.build.buildings.NuclearPlant;
import com.utbm.electricity.ElectricityNetwork;
import com.utbm.map.MapManager;
import com.utbm.map.area.Area;
import com.utbm.map.plot.GrassPlot;
import com.utbm.map.plot.Plot;
import com.utbm.population.PopulationManager;
import micrell.utils.vector.Vector2;

import java.util.Stack;

/**
 * Classe représentant le Game Manager. Il gère tout le déroulement de la partie.
 */
public class GameManager {

    /**
     * Building Manager qui gère tous les batiments.
     */
    private BuildingManager buildingManager;
    /**
     * Scheduler general du jeu.
     */
    private Stopwatch stopwatch;

    /**
     * Manager qui gère tout le Réseau electrique.
     */
    private ElectricityNetwork electricityNetwork;

    /**
     * Manager qui gère la carte, et tous les éléments présent dessus.
     */
    private MapManager mapManager;

    /**
     * Manager qui gère la population.
     */
    private PopulationManager populationManager;

    /**
     * Constructeur par défaut.
     */
    public GameManager(){
        this.stopwatch = new Stopwatch();
        this.mapManager = new MapManager(new Vector2(1000, 1000), 5);
        this.electricityNetwork = new ElectricityNetwork();
        this.electricityNetwork.setMap(mapManager.getGroundMap());
        this.populationManager = new PopulationManager();

        this.buildingManager = new BuildingManager(this.populationManager,this.stopwatch,this.electricityNetwork, this.playerInventory);
        this.playerInventory = new Inventory();
        this.playerInventory.getInventory().fill(Material.WOOD,10000);
        this.buildingManager.setInventory(this.playerInventory);
    }

    public BuildingManager getBuildingManager() {
        return buildingManager;
    }

    public void setBuildingManager(BuildingManager buildingManager) {
        this.buildingManager = buildingManager;
    }

    public Stopwatch getStopwatch() {
        return stopwatch;
    }

    public void setStopwatch(Stopwatch stopwatch) {
        this.stopwatch = stopwatch;
    }

    public ElectricityNetwork getElectricityNetwork() {
        return electricityNetwork;
    }

    public void setElectricityNetwork(ElectricityNetwork electricityNetwork) {
        this.electricityNetwork = electricityNetwork;
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public void setMapManager(MapManager mapManager) {
        this.mapManager = mapManager;
    }

    public PopulationManager getPopulationManager() {
        return populationManager;
    }

    public void setPopulationManager(PopulationManager populationManager) {
        this.populationManager = populationManager;
    }

    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(Inventory playerInventory) {
        this.playerInventory = playerInventory;
    }

    private Inventory playerInventory;

}
