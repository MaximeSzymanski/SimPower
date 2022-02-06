package com.utbm.build;

import com.utbm.Inventory;
import com.utbm.build.buildings.Building;
import com.utbm.map.area.Area;

/**
 * Cette classe représente l'objet servant à construire un batiment.
 */
public class BuildingBuilder {


    public BuildingBuilder() {
    }

    /**
     * Renvoie vrai si le l'inventaire du joueur, passé en parametre,  a assez de ressources pour constuire le batiment passé en parametre, sinon renvoie faux.  Cette méthode sert a savoir si le joueur peut constuire un certain batiment. Elle regarde les material requis pour la construction du batiment, et les compares avec l'inventaire du joueur.
     * @param p_buildType Batiment que le joueur veut construire.
     * @param p_Inventory Inventaire du joueur.
     * @return Renvoie vrai si le l'inventaire du joueur, passé en parametre,  a assez de ressources pour constuire le batiment passé en parametre, sinon renvoie faux.
     */
    public boolean isEnoughRessources(Building p_buildType, Inventory p_Inventory){
        return p_Inventory.getInventory().isStockAffordable(p_buildType.getCost());
    }

    /**
     * Renvoie la possibilité de constuire sur l'Area passé en parametre.
     * @param p_area Area à tester.
     * @return Renvoie vrai si l'Area est constructible, sinon renvoie faux.
     */
    public boolean isAreaConstructible(Area p_area){
        return p_area.isFree();
    }

    /**
     * Cette méthode sert a construire un batiement, donné en parametre. La méthode retire le cout de la construction à l'inventaire, puis assigne l'inventaire du joueur au batiment.
     * @param p_building Batiment à construire.
     * @param p_inventory Inventaire du joueur.
     */
    public void buildBuilding(Building p_building, Inventory p_inventory){

        p_inventory.getInventory().substractStock(p_building.getCost());
        p_building.setInventory(p_inventory);


    }
}
