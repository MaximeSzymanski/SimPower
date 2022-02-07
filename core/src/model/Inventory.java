package model;


import model.build.stock.Stock;

/**
 * Classe représentant l'inventaire du joueur.
 */
public class Inventory {
    /**
     * Stock du joueur.
     */
    Stock inventory;

    /**
     * Constructeur par défaut.
     */
    public Inventory(){

        this.inventory = new Stock();

    }

    public Stock getInventory() {
        return inventory;
    }

    public void setInventory(Stock inventory) {
        this.inventory = inventory;
    }
}
