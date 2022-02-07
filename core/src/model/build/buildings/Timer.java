package model.build.buildings;

/**
 * Classe qui représente la clock interne de chaque batiment. La clock générale de la partie décrémente les timer de chaque batiment a chaque tick. Quand le timer arrive a 0, le batiment produit. Cela permet d'avoir une clock generale et une clock par batiment, offrant la possibilité de faire produire les batiments a des intervalles de temps différentes, et de povuoir accelerer le temps du jeu en jouant seulement sur la clock général du jeu, la StopWatch.
 *
 */
public class Timer {
    /**
     * Montant du comptage max de tick et actuelle.
     */
    private int maxCount,currentCount;
    /**
     * Constructeur par défaut.
     */
    Timer(){
        this.maxCount = 180;
        this.currentCount = 0;
    }


    /**
     * Incrémente le chonometre interne. Si il atteint le chrono max, il repart a 0 et retourne vrai.
     * @return Retourne vrai si le chronometre interne a atteint le nombre max de tick, et faux sinon.
     */
    private boolean incrementTimer(){
        this.currentCount= this.currentCount + 1;
        if(currentCount >= maxCount){
            this.currentCount=0;
            return true;
        }else{
            return false;
        }
    }

    public boolean update(){
        return incrementTimer();
    }

    public int getMaxCount() {
        return this.maxCount;
    }

    public int getCurrentCount(){
        return this.currentCount;
    }

    public void setMaxCount(int p_count) {
        this.maxCount = p_count;
    }

}
