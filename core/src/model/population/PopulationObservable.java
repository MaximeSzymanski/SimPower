package model.population;


import model.build.buildings.Building;

/**
 * Cette interface est l'implémentation d'un Design Pattern Observer, pour que les batiments qui ne sont pas complet en terme de population observent le population manager, qui va les notifier lorsqu'il y a de la population sans emploi ou sans travaille.
 * Cette interface est implémenté dans le populationmanager, qui est observable.
 */
public interface PopulationObservable {

    /**
     * Méthode qui notifie les observateurs qu'ils doivent integrer une nouvelle population au sein de leurs batiments.
     */
    void notifyAllObserver();


    /**
     * Méthode pour ajouter un observateur.
     * @param p_observer Batiment qui observe le population manager.
     */
    void addObserver(Building p_observer);

    /**
     * Méthode qui enleve un observateur du population manager.
     * @param p_observer Batiment a retirer.
     */
    void removeObserver(Building p_observer);

}
