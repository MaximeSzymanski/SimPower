package com.utbm.build.stock;

import com.utbm.Material;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe représentant un Stock de matériaux.
 */
public class Stock {

    /**
     * Un stock est représenté par une HashMap, avec comme clé un material, et comme valeur un int, qui représente la quantité du material en question. Par défaut, le stock est vide.
     */
    private Map<Material, Integer> stock;
    /**
     * Constructeur par défaut.
     */
    public Stock() {
        this.stock = new HashMap<>();
    }

    public Map<Material, Integer> getStock() {
        return stock;
    }

    /**
     * Rempli le Stock d'un material, donné en parametre, d'un montant donné également en parametre.
     * @param p_material Material a alimenter.
     * @param p_amount Montant de material a ajouter dans le Stock.
     */
    public void fill(Material p_material, int p_amount){
        if(p_amount >= 0) {

            this.stock.put(p_material, p_amount + this.getStock(p_material));

        }
    }

    /**
     * Retire un montant de material au Stock. Le material et le montant en question sont donnés en parametre.
     * @param p_material Material à diminuer.
     * @param p_amount Montant à diminuer.
     */
    public void lower(Material p_material, int p_amount){
        this.stock.put(p_material,this.getStock(p_material) - p_amount) ;
    }

    /**
     * Ajoute un cout de construction a un batiment.
     * @param p_material Material dont le cout doit etre augmenté.
     * @param p_amount Montant du cout de ce material.
     */
    public void addMaterialCost(Material p_material, int p_amount){
        if(!this.stock.containsKey(p_material)) {
            this.stock.put(p_material, p_amount);
        }
    }

    /**
     * Ajoute un Stock a un autre Stock existant.
     * @param p_stock Stock à ajouter.
     */
    public void addStock(Stock p_stock){
        for(Map.Entry<Material, Integer> material : p_stock.stock.entrySet()){
            this.fill(material.getKey(), p_stock.getStock(material.getKey()));
        }
    }

    /**
     * Remet un stock a 0. Les valeurs des montants de chaque material sont remise à 0.
     */
    public void resetStock(){
        for(Map.Entry<Material, Integer> material : this.stock.entrySet()){
            this.lower(material.getKey(),this.getStock(material.getKey()));
        }
    }

    /**
     * Soustrait un stock a un stock déja existant.
     * @param p_stock Stock à soustraire.
     */
    public void substractStock(Stock p_stock){

        for(Map.Entry<Material, Integer> material : p_stock.stock.entrySet()){
            this.lower(material.getKey(), p_stock.getStock(material.getKey()));
        }
    }

    /**
     * Renvoie le montant d'un material du Stock.
     * @param p_material Material souhaité.
     * @return Renvoie le montant du material passé en parametre sous forme de int.
     */
    public int  getStock(Material p_material) {
        if(this.stock.containsKey(p_material)){
            return stock.get(p_material);
        }else{
            return 0;
        }


    }

    /**
     * Met le montant d'un material en particulier à une valeur arbitraire.
     * @param p_material Material à modifier.
     * @param p_amount Montant arbitraire du material.
     */
    public void setStock(Material p_material, int p_amount) {
        if(p_amount >= 0) {
            this.stock.put(p_material, p_amount);
        }
    }

    /**
     * Renvoie vrai si le montant du matérial passé en parametre est superieur ou égal au montant passé en parametre, et faux sinon. Cette méthode sert à savoir si le joueur peut construire un batiment, si il a assez de ressources pour le faire.
     * @param p_material Material à tester.
     * @param p_amount Montant à tester.
     * @return Renvoie vrai si la valeur du montant du material en parametre est superieur ou égal au montant passé en parametre, sinon renvoie faux.
     */
    private boolean isMaterialAffordable(Material p_material, int p_amount){

        return this.stock.get(p_material) >= p_amount;
    }


    /**
     * Renvoie vrai si tout les montants du stock sont supérieur a tout les montants du stock entré en parametre. Cette méthode sert à savoir si le joueur a assez de ressources pour construire un batiment. Il suffit alors que le stock de L'inventaire du joueur appel cette méthode avec, comme parametre, le stock représentant le cout d'un batiment.
     * @param p_stock Stock dont il faut vérifier chaque montant.
     * @return envoie vrai si tout les montants du stock sont supérieur a tout les montants du stock entré en parametre, faux sinon.
     */
    public boolean isStockAffordable(Stock p_stock){
        boolean isAffordable = true;

        for(Map.Entry<Material, Integer> material : (p_stock.stock.entrySet())){

            if(p_stock.stock.entrySet() != null){
                if(this.stock.containsKey(material.getKey())){
                    isAffordable = this.isMaterialAffordable(material.getKey(), p_stock.stock.get(material.getKey()));
                }else{
                    return false;
                }

                if(isAffordable == false){
                    return false;
                }

            }
            return true;
        }

        return isAffordable;
    }


    @Override
    public String toString() {
        return "Stock{" +
                "stock=" + stock +
                '}';
    }
}