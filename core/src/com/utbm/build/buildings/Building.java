package com.utbm.build.buildings;

import com.utbm.Inventory;
import com.utbm.Material;
import com.utbm.build.stock.Stock;
import com.utbm.electricity.ElectricityNetwork;
import com.utbm.map.area.Area;
import com.utbm.population.Population;
import com.utbm.population.PopulationObserver;

import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Classe qui représente un batiment. Cette classe implémente l'interface PopulationObserver, pour notifier le PopulationManager si besoin. Elle est abstraite car elle va differer selon le type de batiment qui seront créées. (Design Pattern Observer)
 */
public abstract class Building implements PopulationObserver {


    public Stock getProductionCost() {
        return productionCost;
    }

    /**
     * Cout de chaque production.
     */
    protected Stock productionCost;
    /**
     * Timer pour la production du batiment.
     */
    protected Timer timer;
    /**
     * Stock interne du batiment.
     */
    protected Stock stock;
    /**
     * Cout de construction du batiment.
     */
    protected Stock cost;
    /**
     * Zone de placement du batiment.
     */
    protected Area area;
    /**
     * Rayon du cercle de pollution autour du batiment.
     */
    protected int pollutionRadius;
    /**
     * Cout en energie pour produire.
     */
    protected int energyCost;


    public boolean isAlimented() {
        return isAlimented;
    }

    /**
     * Booléen valant vrai si le batiment est alimenté, faux sinon. Utilisé pour les maisons et les usines.
     */
    protected boolean isAlimented;
    /**
     * Materiel produit par le batiment.
     */
    public Material materialProduced;
    /**
     * Réference sur le réseau electrique de la ville. Elle peremet au batiment de pouvoir diffuser son electricité, ou de pouvoir en prendre au réseau.
     */
    protected ElectricityNetwork pylonNetwork;
    /**
     * Nombre maximal d'occupant du batiment.
     */
    protected int maxPopulation;
    /**
     * Taux de pollution du batiment.
     */
    protected int pollutionRate;
    /**
     * Réference sur l'inventaire du joueur. Elle permet au batiment de pouvoir envoyer son stock directement a l'inventaire une fois la production faite.
     */
    protected Inventory inventory;
    /**
     * Nom du batiment.
     */
    public String name;

    public int getProductionRate() {
        return productionRate;
    }

    /**
     * Taux de production du batiment.
     */
    protected int productionRate;
    /**
     * Tas de Population, représentant les habitants du batiment.
     */
    protected Stack<Population> population;

    /**
     * Constructeur par défaut.
     */
    public Building() {
        this.timer = new Timer();
        this.cost = new Stock();
        this.area = new Area();
        this.productionCost = new Stock();
        this.stock = new Stock();
        this.population = new Stack<>();
        this.stock.getStock().put(Material.GREEN_ENERGY,0);
        this.stock.getStock().put(Material.POLLUTING_ENERGY,0);

        this.pollutionRate = 0;
        this.pollutionRadius = 0;
    }

    /**
     * Constructeur de batiment, avec un taux de production arbitraire passé en parametre.
     * @param productionRate Taux de production à assigner au batiment.
     */
    public Building(int productionRate) {
        this();
        this.productionRate = productionRate;

    }

    public int getPollutionRadius() {
        return pollutionRadius;
    }

    public Stack<Population> getPopulation() {
        return population;
    }

    /**
     * Envoie le stock du building vers l'inventaire, une fois que celui ci a produit.
     */
    private void sendStockToInventory(){
        System.out.println(""+this.name + " Sent " + this.stock);
        this.inventory.getInventory().addStock(this.stock);
        this.stock.setStock(this.materialProduced,0);
     //   this.stock.resetStock();
    }

    public void setPylonNetwork(ElectricityNetwork pylonNetwork) {
        this.pylonNetwork = pylonNetwork;
    }

    /**
     * Méthode appelé quand l'horloge à tick. L'horloge est observé par les buildings, selon le Design Pattern Observer.
     */
    public void stopwatchUpdate(){

        if(this.timer.update() == true){

            if(this.produce() && this.inventory.getInventory().isStockAffordable(this.productionCost)){

                this.area.setPollution(this.pollutionRate);
                this.removeUsedRessources();
                this.sendStockToInventory();
            }
        }
    }

    @Override
    public String toString() {
        return "Building{" +
                "timer=" + timer +
                ", stock b=" + stock +
                ", area=" + area +
                '}';
    }

    /**
     * Methode abstraite qui produit le matériaux du batiment. Elle n'est pas définie ici car elle differe selon le type de batiment.
     * @return Retourne vrai si le batiment a produit, faux sinon.
     */
    protected abstract boolean produce();

    /**
     * Méthode qui enleve les matériaux utilisé pour la production au batiement.
     */
    protected void removeUsedRessources() {
        for(Map.Entry<Material, Integer> material : this.productionCost.getStock().entrySet()){

            if(this.productionCost.getStock().entrySet() != null){
                this.inventory.getInventory().lower(material.getKey(),material.getValue());
            }
        }
    }

    public Material getMaterialProduced() {
        return materialProduced;
    }

    public ElectricityNetwork getPylonNetwork() {
        return pylonNetwork;
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }

    public Stock getCost() {
        return cost;
    }


    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public Stock getFullStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
            this.area = area;


    }

    public int getPollutionRate() {
        return pollutionRate;
    }



    public void setInventory(Inventory p_inventory){
        this.inventory = p_inventory;
    }


    /**
     * Ajoute de la population au batiment si c'est necessaire.
     * @param p_population Population qui est en attente d'etre intégré a un batiment.
     * @return Retourne vrai si le batiment est complet, faux sinon.
     */
    @Override
    public boolean PopulationUpdate(List<Population> p_population) {

        for(Population currentpopulation : p_population){
                if(isBuildingncomplete()){
                    return isBuildingncomplete();
                }

            updateForNewPop(currentpopulation);
            }
        return isBuildingncomplete();
    }

    /**
     * Méthode qui permet au batiment de rendre l'energie qu'il consomme au réseau.
     */
    public abstract void giveBackEnergyToNetwork();

    public boolean isBuildingncomplete() {
        return this.population.size() >= this.maxPopulation;
    }

    public abstract void updateForNewPop(Population p_current);

    /**
     * Méthode qui permet de mettre a jour l'electricité du batiment, en regardant s'il y a assez d'électricité sur le réseau.
     */
    public abstract void updateElectricity();

    /**
     * Méthode qui calcul le taux moyen de satisfaction d'un batiment.
     * @return Retourne la valeur moyenne de satisfaction du batiment.
     */
    public int computeAverageSatisfaction(){
        int totalSatisfaction = 0;
        int numberOfPopulation = this.population.size();
        for(Population currentPopulation : this.population){
            totalSatisfaction += currentPopulation.getSatisfaction();
        }
        if(numberOfPopulation ==0){
            return 0;
        }else{
            return totalSatisfaction / numberOfPopulation;
        }
    }


    }
