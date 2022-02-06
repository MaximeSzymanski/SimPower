package com.utbm.build.buildings;

import com.utbm.Material;
/**
 * Classe qui représente une pompe d'eau. Elle hérite de la classe Pump.
 */
public class WaterPump extends Pump {
    /**
     * Constructeur par défaut.
     */
    public WaterPump() {
        super();
        this.name = "WaterPump";
    }

    @Override
    protected void setMaterialProducted() {
        this.materialProduced = Material.WATER;
    }

    @Override
    public String toString() {
        return "GasMine{" +
                "timer=" + this.getTimer() +
                ", stock=" + this.getFullStock() +
                ", area=" + this.getArea() +
                ", productionRate=" + productionRate +
                '}';
    }
}

