package model.build.buildings;



import model.Material;
import model.population.Population;

import java.util.Random;

/**
 * Batiment représentant une maison.
 */
public class House extends Building {
    /**
     * Entier représentant les taxes de la maison.
     */
    private int taxes;
    /**
     * Quantité d'energie polluante requise pour etre alimentée.
     */
    public int pollutingEnergyRequired;
    /**
     * Quantité d'energie verte requise pour etre alimentée.
     */
    public int greenEnergyRequired;

    public Material getCitizenChoice() {
        return citizenChoice;
    }

    /**
     * Représente le type de material necessaire a l'alimentation de la maison. Les citoyens choisissent aléaoirement entre energie renouvelable, energie verte ou les deux (BOTH).
     */
    private Material citizenChoice;
    /**
     * Constructeur par défaut.
     */
    public House(){

        super();
        Random rand = new Random();
        //generate random values from 0-24



        this.productionCost.fill(Material.POLLUTING_ENERGY,energyCost);
        this.materialProduced = Material.JUDOKOIN;
        this.energyCost = 50;
        this.maxPopulation = 50;
        this.pollutionRate = 10;
        this.pollutionRadius = 1;

        this.wantPollutingEnergy();
        this.cost.addMaterialCost(Material.WOOD,500);

        this.taxes = 600;
        this.getFullStock().setStock(Material.POLLUTING_ENERGY,0);
        this.getFullStock().setStock(Material.GREEN_ENERGY,0);
        this.name = "House";
        this.productionRate = taxes;
        int int_random = rand.nextInt(3);

        switch (int_random){
            case 0:
                this.wantGreenEnergy();
            break;
            case 1:
                this.wantPollutingEnergy();
                break;
            case 2:
                this.wantPollutingAndGreenEnergy();
        }

    }

    /**
     * Méthode qui est appelé quand les habitants ont choisis d'etre alimenté avec de l'energie polluante.
     */
    private void wantPollutingEnergy(){
        this.productionCost.resetStock();
        this.citizenChoice = Material.POLLUTING_ENERGY;
        this.productionCost.fill(Material.POLLUTING_ENERGY,energyCost);
        this.productionCost.fill(Material.GREEN_ENERGY,0);
        this.pollutingEnergyRequired = this.energyCost;
        this.greenEnergyRequired = 0;
    }

    /**
     * Méthode qui est appelé quand les habitants ont choisis d'etre alimenté avec de l'energie verte.
     */
    private void wantGreenEnergy(){
        this.productionCost.resetStock();
        this.citizenChoice = Material.GREEN_ENERGY;
        this.productionCost.fill(Material.GREEN_ENERGY,energyCost);
        this.productionCost.fill(Material.POLLUTING_ENERGY,0);
    }

    /**
     * Méthode qui est appelé quand les habitants ont choisis d'etre alimenté avec de l'energie polluante ou verte.
     */
    private void wantPollutingAndGreenEnergy(){
        this.productionCost.resetStock();
        this.citizenChoice = Material.BOTH;
        this.productionCost.fill(Material.POLLUTING_ENERGY,energyCost/2);
        this.productionCost.fill(Material.GREEN_ENERGY,energyCost/2);

    }


    /**
     * Si la maison a assez d'energie et n'est pas alimenté, elle prend l'energie nécessaire au réseau, et le bonheur de la population augmente.
     * Sinon, la maison n'est pas alimenté et le bonheur des citoyens diminue.
     */
    public void updateElectricity(){
        if(this.hasEnoughEnergy() && isAlimented == false){
            this.isAlimented = true;
            for(Population currentPopulation : this.population){
                currentPopulation.addSatisfaction(20);
            }
            this.pylonNetwork.retrieveElectricityOnNetwork(this,this.pollutingEnergyRequired,this.greenEnergyRequired);
        }else if(!this.hasEnoughEnergy()){
            this.isAlimented = false;
            for(Population currentPopulation : this.population){
                currentPopulation.removeSatisfaction(20);
            }
        }
    }

    /**
     *Méthode qui produit des pieces, en fonction de la taxe et du nombre d'habitant dans la maison.
     * @return Retourne vrai si la maison a produit des pieces, et faux sinon.
     */
    protected boolean produce() {
        this.stock.fill(Material.JUDOKOIN,taxes * this.getPopulation().size()) ;
        return true;
    }

    /**
     * Rend l'energie au réseeau, lorsque la maison n'est plus alimenté.
     */
    @Override
    public void giveBackEnergyToNetwork() {
        if(this.isAlimented){

            this.pylonNetwork.giveBackEnergyToNetwork(this,this.productionCost.getStock(Material.GREEN_ENERGY), this.productionCost.getStock(Material.POLLUTING_ENERGY));
            this.getFullStock().resetStock();
            isAlimented = false;

        }
    }

    /**
     * Fonction qui vérifie que la maison a assez d'energie pour fonctionner, en fonction du choix des citoyens.
     * @return Retourne vrai si elle peut fonctionner, et faux sinon.
     */

    private boolean hasEnoughEnergy(){
        switch (citizenChoice){
            case BOTH:
                if(this.stock.getStock(Material.GREEN_ENERGY) >= this.productionCost.getStock(Material.GREEN_ENERGY) && this.stock.getStock(Material.POLLUTING_ENERGY) >= this.productionCost.getStock(Material.POLLUTING_ENERGY)) {
                return true;
            }
            break;

            case POLLUTING_ENERGY:
                if(this.stock.getStock().get(Material.POLLUTING_ENERGY) >= this.productionCost.getStock(Material.POLLUTING_ENERGY)) {
                return true;
            }
            break;

            case GREEN_ENERGY:
                if(this.stock.getStock(Material.GREEN_ENERGY) >= this.productionCost.getStock(Material.GREEN_ENERGY)) {
                    return true;
                }
            break;
            }
        return false;

        }


    /**
     * Méthode qui ajoute des habitants a la maison.
     * @param p_currentpopulation Population a ajouter.
     */
    public void updateForNewPop(Population p_currentpopulation){

        if(!p_currentpopulation.isHasHouse()) {
            this.population.push(p_currentpopulation);
            p_currentpopulation.setHasHouse(true); // indique que le citoyen est logé

        }
    }




}
