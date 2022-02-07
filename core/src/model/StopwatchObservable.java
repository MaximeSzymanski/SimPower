package model;

import model.build.buildings.Building;

/**
 * Cette interface est l'implémentation d'un Design Pattern Observer, pour que les batiments puissent observer le scheduler.
 * Cette interface est implémenté dans la classe Stopwatch, qui est observable.
 */
public interface StopwatchObservable {
    /**
     * Notifie les batiments lorsque le scheduler du jeu s'update.
     */
    void notifyAllObserver();

    /**
     * Ajoute un observateur au scheduler.
     * @param p_observer Batiment à ajouter.
     */
    void addObserver(Building p_observer);

    /**
     * Retire un obserateur au scheduler.
     * @param p_observer Batiment a retirer.
     */
    void removeObserver(Building p_observer);

}
