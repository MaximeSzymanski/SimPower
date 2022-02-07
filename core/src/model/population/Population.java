package model.population;

/**
 * Classe représentant un habitant de la ville.
 */
public class Population {
    /**
     * Entier représentant la satisfaction d'un habitant. Valeur entre 0 et 100.
     */
    private int satisfaction;

    /**
     * Booléen indiquant si le citoyen travail ou non.
     */
    private boolean isWorking;

    /**
     * Booléen indiquant si le citoyen a un domicile ou non.
     */
    private boolean hasHouse;
    /**
     * Constructeur par défaut.
     */
    public Population(){
        this.isWorking = false;
        this.hasHouse = false;
        this.satisfaction = 50;


    }


    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }

    /**
     * Méthode qui ajoute de la satisfaction à un habitant.
     * @param p_satisfaction Satisfaction à ajouter à l'habitant.
     */
    public void addSatisfaction(int p_satisfaction){
        this.satisfaction += p_satisfaction;
        if(satisfaction > 100){
            satisfaction = 100;
        }
    }

    /**
     * Méthode qui retire de la satisfaction à un habitant.
     * @param p_satisfaction Satisfaction à retirer à l'habitant.
     */
    public void removeSatisfaction(int p_satisfaction){
        this.satisfaction -= p_satisfaction;
        if(satisfaction < 0){
            satisfaction = 0;
        }
    }

    public boolean isWorking() {
        return isWorking;
    }


    public void setWorking(boolean working) {
        isWorking = working;
        if(working == true){
            this.addSatisfaction(20);
        }else{
            this.removeSatisfaction(20);
        }
    }

    public boolean isHasHouse() {
        return hasHouse;
    }

    public void setHasHouse(boolean hasHouse) {
        this.hasHouse = hasHouse;
    }
}
