package model.build.buildings;

import model.Material;
import model.map.plot.GrassPlotDeposit;
import model.map.plot.Plot;

public abstract class Pump extends HarvestBuilding{
    /**
     * Constructeur par défaut.
     */
    public Pump() {
        super();
    }

    /**
     * Méthode qui indique si une pompe peut  produire. Si elle se situe au dessus d'un matérial compatible, elle renvoie ce plot. Sinon, elle renvoie null.
     * @return Renvoie le plot a extraire si possible, sinon renvoie null.
     */
    public Plot NodeAbleToProduct() {
        for(Plot areaNode : this.area.getPlotList()){
            if(areaNode instanceof GrassPlotDeposit){
                if(((GrassPlotDeposit) areaNode).getMaterialDeposit().getMaterial() == this.materialProduced){
                    return areaNode;
                }
            }
        }
        return null;
    }

    @Override
    protected void setMaterialProducted() {
        this.materialProduced = Material.WATER;
    }
}
