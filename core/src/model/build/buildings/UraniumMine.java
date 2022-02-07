package model.build.buildings;

import model.Material;

/**
 * Classe qui représente une mine d'uranium. Elle hérite de la classe Mine.
 */
public class UraniumMine extends Mine {
    /**
     * Constructeur par défaut.
     */
    public UraniumMine() {
        super();
        this.cost.fill(Material.WOOD,10);
        this.name = "UraniumMine";

    }

    @Override
    protected void setMaterialProducted() {
        this.materialProduced = Material.URANIUM;

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
