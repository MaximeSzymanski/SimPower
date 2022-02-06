package com.utbm.build.buildings;

import com.utbm.Material;
import com.utbm.map.plot.GrassPlotDeposit;
import com.utbm.population.Population;
import micrell.utils.grid.Node;

import java.util.List;

/**
 * Classe représentant les batiments qui produisent des ressources. Elle hérite de Building.
 */
public abstract class ProductionBuilding extends Building {


    /**
     * Constructeur de Batiment de production, avec un taux de production arbitraire passé en parametre.
     * @param productionRate Taux de production à assigner au batiment.
     */
    public ProductionBuilding(int productionRate) {
        super(productionRate);
        this.stock.resetStock();


    }
    /**
     * Constructeur par défaut.
     */
    public ProductionBuilding(){
        super();
        this.stock.resetStock();
        this.productionRate = 1000;
        this.setMaterialProducted();
    }

    protected abstract void setMaterialProducted();

    public int getProductionRate() {
        return productionRate;
    }

    @Override
    public String toString() {
        return "ProductionBuilding{" +
                "timer=" + this.getTimer()  +
                ", stock=" + this.getFullStock() +
                ", area=" + this.getArea() +
                ", productionRate="  +
                '}';
    }

    @Override
    protected boolean produce() {
        return false;
    }

    @Override
    public void giveBackEnergyToNetwork() {

    }

    /**
     * Méthode qui ajoute des travailleurs a un batiment.
     * @param p_currentpopulation Population à ajouter.
     */
    public void updateForNewPop(Population p_currentpopulation){
        if(!p_currentpopulation.isWorking()) {
            this.population.push(p_currentpopulation);
            p_currentpopulation.setWorking(true); // indique que la personne a un travail.
        }
    }



    @Override
    public void updateElectricity() {

    }

}
