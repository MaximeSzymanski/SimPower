package micrell.utils.math;

/**
 * Classe qui contient des méthodes pratique pour faire des calculs mathématiques.
 */
public final class Math {

    /**
     * Méthode qui force un nombre à appartenir à l'ensemble [0;1].
     *
     * @param p_f : Le nombre à serrer.
     * @return Le nombre serré.
     */
    public static float clamp01(float p_f){
        if(p_f < 0){
            return 0;
        }else if(p_f > 1){
            return 1;
        }else{
            return p_f;
        }
    }

    /**
     * Méthode qui force un nombre à appartenir à l'ensemble [p_min;p_max].
     *
     * @param p_min : La borne inférieur.
     * @param p_max : La borne supérieur.
     * @param p_f : La valeur à serrer.
     * @return : La valeur serrée.
     */
    public static float clamp(int p_min, int p_max, float p_f){
        if(p_f < p_min){
            return p_min;
        }else if(p_f > p_max){
            return p_max;
        }else{
            return p_f;
        }
    }

    /**
     * Vérifie si un ensemble [p_min;p_max] contient la valeur p_value.
     *
     * @param p_min : La borne inférieur de l'ensemble.
     * @param p_max : La borne supérieur de l'ensemble.
     * @param p_value : La valeur à tester.
     * @return true si la valeur appartient à l'ensemble, false sinon.
     */
    public static boolean seriesInclude(float p_min, float p_max, float p_value){
        return p_min <= p_value && p_value < p_max;
    }
}
