package com.utbm.build.buildings;

import com.utbm.Material;

/**
 * Classe qui représente les Mines de Gaz, qui hérite de Mine.
 */
public class GasMine extends Mine {
    /**
     * Constructeur par défaut.
     */
    public GasMine() {
        super();
        this.name = "GasMine";
    }

    @Override
    protected void setMaterialProducted() {
        this.materialProduced = Material.GAS;
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
