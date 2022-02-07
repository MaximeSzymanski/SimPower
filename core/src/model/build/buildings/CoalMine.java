package model.build.buildings;

import model.Material;

/**
 * Classe qui représente les Mines de charbon, qui hérite de Mine.
 */
public class CoalMine extends Mine {
    /**
     * Constructeur par défaut.
     */
    public CoalMine() {
        super();
        this.name = "CoalMine";
    }

    @Override
    protected void setMaterialProducted() {
        this.materialProduced = Material.COAL;
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

    @Override
    public void giveBackEnergyToNetwork() {

    }




}