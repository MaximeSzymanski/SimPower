package model.map.plot;

import model.map.plot.materialDeposit.MaterialDeposit;
import model.utils.vector.Vector2;

/**
 * Classe qui hérite de Plot. Représente un petit carré contenant un minerais sur la map.
 */
public class GrassPlotDeposit extends Plot {

    /**
     * Mine que le plot contient.
     */
    private MaterialDeposit materialDeposit;

    /**
     * Constructeur paramétré.
     *
     * @param p_worldPos : position du plot.
     * @param p_gridX : index x du plot dans la grid de la map.
     * @param p_gridY : index y du plot dans la grid de la map.
     * @param p_materialDeposit : Mine que le plot doit contenir.
     */
    public GrassPlotDeposit(Vector2 p_worldPos, int p_gridX, int p_gridY, MaterialDeposit p_materialDeposit){
        super(false, p_worldPos, p_gridX, p_gridY);
        //this.draw = "[Md]"; //(Debug)
        setMaterialDeposit(p_materialDeposit);

    }

    /**
     * Constructeur paramétré de recopie.
     *
     * @param p_plot : plot à recopier.
     * @param p_materialDeposit : mine que le plot doit contenir.
     */
    public GrassPlotDeposit(Plot p_plot, MaterialDeposit p_materialDeposit){
        super(p_plot);
        //this.draw = "[??]"; //(Debug)
        setMaterialDeposit(p_materialDeposit);
    }

    /**
     * Méthode qui permet d'extraire des ressources de la mine de l'instance.
     *
     * @param p_amountToExtract : montant à extraire de la mine.
     * @return Le montant extrait.
     */
    public int materialExtraction(int p_amountToExtract){

        if(isMiningPossible()){

            return this.materialDeposit.extract(p_amountToExtract);
        }else{

            return 0;
        }
    }

    /**
     * Vérifie si il est possible d'extraire des minerais de la mine.
     *
     * @return true si c'est possible, false sinon.
     */
    private boolean isMiningPossible(){

        return !(isNoDeposit() || isMaterialDepositEmpty()); //Si le plot contient une mine ou si la mine n'est pas vide

    }

    /**
     * Vérifie que la mine n'est pas vide.
     *
     * @return true si la mine est vide, false sinon.
     */
    public boolean isMaterialDepositEmpty() {
        if(this.materialDeposit.getCurrentAmount() <= 0){

            this.materialDeposit = null;
            return true;
        }else{
            return false;
        }
    }

    /**
     * Vérifie si l'instance contient une mine
     *
     * @return true si l'instance ne contient pas de mine, false sinon.
     */
    private boolean isNoDeposit() {
        return this.materialDeposit == null;
    }

    public void setMaterialDeposit(MaterialDeposit materialDeposit) {
        this.materialDeposit = materialDeposit;
    }

    public MaterialDeposit getMaterialDeposit() {
        return materialDeposit;
    }
}
