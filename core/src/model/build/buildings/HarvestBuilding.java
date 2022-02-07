package model.build.buildings;

import model.map.plot.GrassPlotDeposit;
import model.map.plot.Plot;

/**
 * Classe représentant les batiments de récolte. Elle hérite de ProdctionBuilding.
 */
public abstract class HarvestBuilding extends ProductionBuilding {

    public HarvestBuilding(int productionRate) {
        super(productionRate);
    }
    /**
     * Constructeur par défaut.
     */
    HarvestBuilding(){
        super();
        this.pollutionRate = 20;
        this.pollutionRadius = 2;
        this.productionRate = 150;
    }


    /**
     * Méthode qui permet au batiment de produire. Si il y a des ressources autour du batiment, il les récolte.
     * @return retourne vrai si le batiment a produit, faux sinon.
     */
    public boolean produce(){
        GrassPlotDeposit plotToExtract = (GrassPlotDeposit) NodeAbleToProduct();

        if(plotToExtract != null){
            this.stock.fill(plotToExtract.getMaterialDeposit().getMaterial(), plotToExtract.materialExtraction(this.productionRate));
            return true;
        }
        return false;
    }

    /**
     * Méthode qui vérifie si il y a des ressources exploitables a proximité du batiment.
     * @return retourne vrai s'il y a des ressources exploitables a proximité du batiment, faux sinon.
     */
    public abstract Plot NodeAbleToProduct();

    /**
     * Méthode qui met le bon matériel de production au batiment de récolte.
     */
    protected abstract void setMaterialProducted();

    @Override
    public String toString() {
        return "HarvestBuilding{" +
                "timer=" + this.getTimer() +
                ", stock=" + this.getFullStock() +
                ", area=" + this.getArea() +
                ", productionRate=" + productionRate +
                '}';
    }
}
