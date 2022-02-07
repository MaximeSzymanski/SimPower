package model.build.buildings;

import model.Material;

/**
 * Classe qui représente une mine de fer. Elle hérite de la classe Mine.
 */
public class IronMine extends Mine {
    /**
     * Constructeur par défaut.
     */
    public IronMine() {
        super();
        this.name = "IronMine";
    }

    @Override
    protected void setMaterialProducted() {
        this.materialProduced = Material.IRON;
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