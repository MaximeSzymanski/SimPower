package com.utbm;
import com.utbm.build.buildings.Building;
import com.utbm.population.PopulationManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant le scheduler général du jeu. Elle implémente l'interface du Design Pattern Observer, qui lui permet d'etre observée par les batiments.
 */
public class Stopwatch implements StopwatchObservable {
    /**
     * Valeur du scheduler.
     * Si l'on accelerer le temps du jeu, il suffit de multiplier cette valeur par l'acceleration désiré.
     */
    private int value;

    /**
     * Liste des batiments qui écoutent le scheduler.
     */
    private List<Building> listeners = new ArrayList<>();


    public Stopwatch() {
        this.value = 0;
    }

    public Stopwatch(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    /**
     * Constructeur par défaut.
     */
    public void tick() {
        notifyAllObserver();
    }

    @Override
    public String toString() {
        return "Stopwatch{" +
                "value=" + value +
                '}';
    }

    /**
     * Méthode qui notifie les batiments lorsque le scheduler tick.
     */
    @Override
    public void notifyAllObserver() {
        for (Building listener : listeners) {
            listener.stopwatchUpdate();
        }


    }



    @Override
    public void addObserver(Building p_observer) {
        this.listeners.add(p_observer);
    }


    @Override
    public void removeObserver(Building p_observer) {
        this.listeners.remove(p_observer);
    }

}
