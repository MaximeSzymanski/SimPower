package com.utbm.build.buildings;

import com.utbm.map.plot.GrassPlotDeposit;
import com.utbm.map.plot.Plot;

/**
 * Classe abstraite qui représente les différentes mines du jeu. Elle hérite de HarvestBuilding car ce sont des batiments de récolte.
 */
public abstract class Mine extends HarvestBuilding {
    /**
     * Constructeur par défaut.
     */
    public Mine() {
        super();
    }

    /**
     * Méthode qui indique la possibilité d'une mine d'extraire une ressource. Elle va regarder les 8 cases autours de la mine.
     * @return Retourne vrai si le batiment peut extaire un de ses voisins, faux sinon.
     */
    @Override
    public Plot NodeAbleToProduct() {
        for(Plot areaNode : this.area.getPlotList()){
            for(Plot neighbour : areaNode.getNeighbours()){
                if(neighbour instanceof GrassPlotDeposit){
                    if(isMaterialCompatible((GrassPlotDeposit) neighbour)){
                        return neighbour;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Méthode qui indique la compatibilité entre un stock de matériaux a extaire et une mine.
     * @param neighbour Plot dans lequel les matériaux sont stockées.
     * @return Retourne vrai si les matériaux sont compatible avec le type de mine, faux sinon.
     */
    private boolean isMaterialCompatible(GrassPlotDeposit neighbour) {
        return neighbour.getMaterialDeposit().getMaterial() == this.materialProduced && neighbour.getMaterialDeposit().getCurrentAmount() != 0;
    }

    @Override
    public String toString() {
        return "Mine{" +
                "timer=" + this.getTimer() +
                ", stock=" + this.getFullStock() +
                ", area=" + this.getArea() +
                ", productionRate=" + productionRate +
                '}';
    }

    @Override
    public void updateElectricity() {

    }
}
