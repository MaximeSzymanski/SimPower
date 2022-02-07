package model.build.buildings;

import model.Material;
/**
 * Classe qui représente une mine de pierre. Elle hérite de la classe Mine.
 */
public class StoneMine extends Mine {
    /**
     * Constructeur par défaut.
     */
    public StoneMine() {
        super();
        this.name = "StoneMine";

    }

    @Override
    protected void setMaterialProducted() {
        this.materialProduced = Material.STONE;
    }

    @Override
    public String toString() {
        return "StoneMine{" +
                "timer=" + this.getTimer() +
                ", stock=" + this.getFullStock() +
                ", area=" + this.getArea() +
                ", productionRate=" + productionRate +
                '}';
    }
}
