package model.build.buildings;

import model.Material;
import model.population.Population;

/**
 * Classe qui représente un pylone electrique. Elle hérite de Building.
 */
public class Pylon extends Building {
    /**
     * Constructeur par défaut.
     */
    public Pylon(){
        super();
        this.maxPopulation = 0;
        this.energyCost = 0;
        this.productionCost.getStock().put(Material.WOOD,200);
        this.stock.getStock().put(Material.GREEN_ENERGY,0);
        this.name = "Pylon";
    }

    @Override
    protected boolean produce() {
        return false;
    }

    @Override
    public void giveBackEnergyToNetwork() {

    }

    @Override
    public void updateForNewPop(Population p_current) {

    }



    @Override
    public void updateElectricity() {

    }


}
