package com.utbm.build.buildings;

import com.utbm.Material;

/**
 * Classe représentant les batiments qui produisent de l'energie verte. Elle hérite de EnergyProductionBuilding.
 */
public abstract class GreenEnergyBuilding extends EnergyProductionBuilding{
    /**
     * Constructeur par défaut.
     */
    GreenEnergyBuilding(){
        super();
        this.setMaterialProducted();
        this.pollutionRate = 1000;

    }

    @Override
    protected void setMaterialProducted() {
        this.materialProduced = Material.GREEN_ENERGY;
    }
}
