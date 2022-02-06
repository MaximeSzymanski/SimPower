package com.utbm.build.buildings;

import com.utbm.Material;
import com.utbm.build.stock.Stock;

/**
 * Classe représentant une centrale nucléaire. Elle hérite de PollutingEnergyBuilding.
 */
public class NuclearPlant extends PollutingEnergyBuilding{



    /**
     * Constructeur par défaut.
     */
    public NuclearPlant(){
        super();
        this.productionCost = new Stock();
        this.stock.resetStock();
        this.maxPopulation = 50;
        this.productionRate = 100;
        this.productionCost.addMaterialCost(Material.WOOD,500);
        this.materialProduced = Material.POLLUTING_ENERGY;
        this.name ="NuclearPlant";
        this.timer.setMaxCount(600);

        this.pollutionRate = 80;
        this.pollutionRadius = 5;

    }


}
