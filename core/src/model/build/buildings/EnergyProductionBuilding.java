package model.build.buildings;

/**
 * Classe abstraite représentant un Batiment de production d'energie, qui hérite de batiment de production.
 */
public abstract class EnergyProductionBuilding extends ProductionBuilding{

    /**
     * Booleen valant vrai si le batiment a produit à l'update précedent, faux sinon.
     */
    private boolean hasWorked;

    public boolean isHasWorked() {
        return hasWorked;
    }
    /**
     * Constructeur par défaut.
     */
    EnergyProductionBuilding(){
        super();
        this.hasWorked = false;

    }

    /**
     * Méthode de production du batiment de production d'énergie. Si le batiment a assez de ressource pour fonctionner et qu'il est connecté au réseau electrique, on regarde s'il a déja donné son energie au réseau. Si oui, on ne fait rien. Si non, on diffuse son energie sur le réseau.
     * Si le batiment n'a pas assez de stock pour produire, ou s'il n'est pas connecté au réseau, il regarde s'il a produit a la tick précedente de l'horloge. Si oui, il enleve au réseau l'energie qu'il a produit. Si non, il ne fait rien.
     * @return retourne vrai si le batiment a produit, faux sinon.
     */
    @Override
    protected boolean produce() {

        if(this.inventory.getInventory().isStockAffordable(this.productionCost) && this.pylonNetwork.isConnectedToPylon(this)) { // Si le batiment peut produire

            if(hasWorked == false) { // S'il n'a pas produit au tick d'horloge précedent
                this.stock.fill(this.materialProduced,this.productionRate); // On ajoute au stock du batiment l'energie qu'il a produit.
                this.pylonNetwork.broadcastElectricity(this); // Le batiment diffuse ensuite son energie dans le réseau
            }
            hasWorked = true;

        }else{ // Si le batiment ne peut pas produire
            if(hasWorked == true){ // Si il a produit au tick d'horloge précedent
                switch (this.materialProduced){ // On enleve du réseau l'energie qu'il a produit, en fonction de son type de production.
                    case POLLUTING_ENERGY:
                        this.pylonNetwork.retrieveElectricityOnNetwork(this, this.productionRate, 0);
                        break;

                    case GREEN_ENERGY:
                        this.pylonNetwork.retrieveElectricityOnNetwork(this, 0, this.productionRate);
                        break;
                }
            }
            hasWorked = false;
        }
         return hasWorked;
    }


}
