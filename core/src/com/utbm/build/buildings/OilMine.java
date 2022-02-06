package com.utbm.build.buildings;

import com.utbm.Material;
/**
 * Classe qui représente une mine de petrole. Elle hérite de la classe Mine.
 */
public class OilMine extends Mine {
    /**
     * Constructeur par défaut.
     */
    public OilMine() {
        super();
        this.name = "OilMine";
    }

    @Override
    protected void setMaterialProducted() {
        this.materialProduced = Material.OIL;
    }

    @Override
    public String toString() {
        return "GasMine{" +
                "timer=" + this.getTimer() +
                ", stock=" + this.getFullStock() +
                ", area=" + this.getArea() +
                ", productionRate=" + this.productionRate +
                '}';
    }
}
