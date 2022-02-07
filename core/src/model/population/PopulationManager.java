package model.population;

import model.StopwatchObservable;
import model.build.buildings.ProductionBuilding;
import model.build.buildings.Building;
import model.build.buildings.House;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static java.lang.Math.min;

/**
 * Classe représentant le manager de population. Il sert à faire venir de nouveaux habitants lorsque des habitations sont disponible, et gère l'employabilité.
 */
public class PopulationManager implements PopulationObservable, StopwatchObservable {
    /**
     * Liste représentant la population ayant déja un emploi.
     */
    Stack<Population> employedPopulation;

    public Stack<Population> getEmployedPopulation() {
        return employedPopulation;
    }

    public List<Population> getNewPopulation() {
        return newPopulation;
    }

    /**
     * Liste représentant la nouvelle population, n'ayant pas de travail ou pas d'emploi.
     */
    List<Population> newPopulation;

    public List<Building> getListeners() {
        return listeners;
    }

    /**
     * Liste de batiment qui ont de la place pour du travail ou un logement. Ce sont des Listeners, selon le Design Pattern Observer.
     */
    List<Building> listeners;

    /**
     * Constructeur par défaut.
     */
    public PopulationManager() {
        this.employedPopulation = new Stack<>();
        this.newPopulation = new Stack<>();
        this.listeners = new ArrayList<>();

    }


    /**
     * Méthode qui ajoute une nouvelle population.
     */
    public void addNewPopulation() {


        if (newPopulation.isEmpty() && !this.listeners.isEmpty()) {

            for (int i = 0; i <= this.countNewPopulation() - 1; i++) {
                this.newPopulation.add(new Population());
            }
        }
        notifyAllObserver();
    }

    /**
     * Ajoute un batiment qui a besoin de population, pour se loger ou pour travailler. C'est un observer selon le Design Pattern Observer.
     * @param p_observer Batiment à Ajouter
     */
    @Override
    public void addObserver(Building p_observer) {
        this.listeners.add(p_observer);
    }


    /**
     * Retire un batiment qui n'a plus besoin de population, pour se loger ou pour travailler. C'est un observer selon le Design Pattern Observer.
     * @param p_observer Batiment à retirer de la liste.
     */
    @Override
    public void removeObserver(Building p_observer) {
        this.listeners.remove(p_observer);
    }

    /**
     * Méthode qui calcul la moyenne de satisfaction de toute la ville.
     * @return Retourne un entier représentant la moyenne de satisfaction, comprise entre  0 et 100.
     */
    public int computeAverageSatisfaction(){
        int totalSatisfaction = 0;
        int numberOfPopulation = this.employedPopulation.size() + this.newPopulation.size();
        for(Population currentPopulation : this.employedPopulation){
            totalSatisfaction += currentPopulation.getSatisfaction();
        }

        for(Population currentPopulation : this.newPopulation){
            totalSatisfaction += currentPopulation.getSatisfaction();
        }

        if(numberOfPopulation ==0){
            return 0;
        }else{
            return totalSatisfaction / numberOfPopulation;
        }
    }

    /**
     * Méthode qui compte la population qui doit arriver dans la ville. La ville ne peut pas acceuillir de chomeur, ni de gens sans maison. On prend donc le plus petit nombre entre les places d'habitation disponible et les places de travail disponible.
     * @return Entier représentant la population à ajouter.
     */
    private int countNewPopulation() {
        int numberOfJobAvailable = 0;
        int numberOfHouseAvailable = 0;
        for (Building currentbuilding : this.listeners) {
            if (currentbuilding instanceof ProductionBuilding) {
                numberOfJobAvailable += currentbuilding.getMaxPopulation() - currentbuilding.getPopulation().size();
            } else if (currentbuilding instanceof  House) {
                numberOfHouseAvailable += currentbuilding.getMaxPopulation() - currentbuilding.getPopulation().size();
            }
        }
        return min(numberOfHouseAvailable, numberOfJobAvailable);
    }

    /**
     * Enleve la population de la ville lorsque les citoyens ne sont pas assez heureux, ou lorsque leur habitation est détruite.
     * @param p_populationStack Population à renvoyer.
     */
    public void removePopulation(Stack<Population> p_populationStack){
            this.newPopulation.removeAll(p_populationStack);
            this.employedPopulation.removeAll(p_populationStack);
            removeWorkerandHoused();
    }

    /**
     * Méthode qui retire de la liste de nouvelle population les habitants ayant trouvé un logement et un emploi.
     */
    private void removeWorkerandHoused() {
        for (Population currentpopulation : newPopulation) {
            if (currentpopulation.isWorking()) {
                this.employedPopulation.push(currentpopulation);

            }
        }
        for (Population current_employed : this.employedPopulation) {
            if (this.newPopulation.contains(current_employed) && current_employed.isHasHouse()) {
                this.newPopulation.remove(current_employed);
            }
        }
    }

    public void addChomeur(Population chomeur){
        chomeur.setWorking(false);
        chomeur.setHasHouse(false);
        this.newPopulation.add(chomeur);
    }

    /**
     * Méthode qui notifie les bâtiments que des habitants cherchent un emploi ou un logement. Selon le Design Pattern Observer.
     */
    @Override
    public void notifyAllObserver() {
        ArrayList<Building> toRemove = new ArrayList<>();
        boolean finish = false;


        for(Building currentListener : listeners) {
            if (currentListener.PopulationUpdate(this.newPopulation)) {
                toRemove.add(currentListener);
            }
            removeWorkerandHoused();
            if (this.newPopulation.isEmpty()) {
                finish = true;
            }

        }
        this.listeners.remove(toRemove);


    }


    /* private int unemploymentPercent() {
         return (int) (((float) this.newPopulation.size() / (float) this.employedPopulation.size()) * 100);
     }*/
}
