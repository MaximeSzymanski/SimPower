package model.map.plot;

/**
 * Classe qui hérite de Plot, représente un carré d'eau sur la map.
 */
public class WaterPlot extends Plot {

    /**
     * Constructeur paramétré. Un WaterPlot est par défaut non constructible.
     *
     * @param p_wP
     */
    public WaterPlot(Plot p_wP){
        super(p_wP);
        this.setWalkable(false);
        //this.draw = new String("[Wa]"); (Debug)
    }
}
