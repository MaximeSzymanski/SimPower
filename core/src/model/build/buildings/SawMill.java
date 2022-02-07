package model.build.buildings;

import model.Material;
/**
 * Classe qui représente une scierie de bois. Elle hérite de la classe Mine.
 */
public class SawMill extends Mine {
    /**
     * Constructeur par défaut.
     */
    public SawMill() {
        super();
        this.cost.addMaterialCost(Material.WOOD,500);
        this.setMaterialProducted();

        this.productionRate = 300;
        this.maxPopulation = 30;
        this.name = "SawMill";


    }


    @Override
    protected void setMaterialProducted() {
        this.materialProduced = Material.WOOD;
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
