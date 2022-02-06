package com.utbm.build.buildings;

import com.utbm.Material;


/**
 * Classe représentant les batiments qui produisent de l'energie polluante. Elle hérite de EnergyProductionBuilding.
 */
public abstract class PollutingEnergyBuilding extends EnergyProductionBuilding{
    /**
     * Constructeur par défaut.
     */
    PollutingEnergyBuilding(){
        super();
        this.setMaterialProducted();
    }


    @Override
    protected void setMaterialProducted() {
        this.materialProduced = Material.POLLUTING_ENERGY;
    }
}
