package com.utbm.population;

import java.util.List;
import java.util.Stack;

/**
 * Cette interface est l'implémentation d'un Design Pattern Observer, pour que les batiments qui ne sont pas complet en terme de population observent le population manager, qui va les notifier lorsqu'il y a de la population sans emploi ou sans travaille.
 * Cette interface est implémenté dans les batiments, qui sont observer.
 */
public interface PopulationObserver {

    /**
     * Méthode appelé lorsque le population Manager notifie les batiments, afin qu'ils acceuillent les nouveaux habitants.
     * @param p_population Liste représentant la nouvelle population.
     * @return Retourne vrai si le batiment est complet, faux sinon. S'il est complet, on sait que l'on peut le retirer de la liste des observers.
     */
    boolean PopulationUpdate(List<Population> p_population);
}
