package com.utbm.build.buildings;

import com.utbm.Material;
import com.utbm.population.Population;

import java.util.List;

/**
 * Classe représentant une usine, qui hérite de Building.
 */
public class Factory extends Building {
    /**
     * Salaire produit par l'usine, qui va directement dans l'inventaire du joueur.
     */
    private int salary;
    /**
     * Constructeur de factory, avec un taux de production arbitraire passé en parametre.
     * @param p_salary Taux de production à assigner au batiment.
     */
    public Factory(int p_salary){
        super();
        this.salary = p_salary;
        this.name = "Factory";
        this.productionCost.fill(Material.POLLUTING_ENERGY,energyCost);
        this.materialProduced = Material.JUDOKOIN;
    }
    
    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Factory{" +
                "timer=" + this.getTimer() +
                ", stock=" + this.getFullStock() +
                ", area=" + this.getArea() +
                ", salary=" + salary +
                '}';
    }

    //TODO a faire
    @Override
    protected boolean produce() {
        return false;
        
    }

    //TODO a faire
    @Override
    public boolean PopulationUpdate(List<Population> p_population) {
        return false;
    }

    //TODO a faire
    @Override
    public void giveBackEnergyToNetwork() {

    }

    //TODO a faire
    @Override
    public void updateForNewPop(Population p_current) {

    }


    //TODO a faire
    @Override
    public void updateElectricity() {

    }
}
