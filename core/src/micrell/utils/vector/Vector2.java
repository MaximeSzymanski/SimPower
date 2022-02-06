package micrell.utils.vector;

/**
 * Classe qui permet de construire un vecteur avec deux coordonnées : x et y.
 */
public class Vector2 {

    /**
     * Les coordonnées x et y.
     */
    protected float x, y;

    /**
     * Constructeur par défaut.
     */
    public Vector2(){
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructeur paramétré.
     *
     * @param p_x : La valeur pour x.
     * @param p_y : La valeur pour y.
     */
    public Vector2(float p_x, float p_y){
        this.x = p_x;
        this.y = p_y;
    }

    /**
     * Cette méthode effectue l'addition terme à terme d'un vecteur avec cette instance.
     *
     * @param p_vector : Le vecteur à additionner.
     */
    public void add(Vector2 p_vector){
        this.setX(this.getX() + p_vector.getX());
        this.setY(this.getY() + p_vector.getY());
    }

    /**
     * Cette méthode multiplie un vecteur par un scalaire donné en paramètre.
     *
     * @param p_b : Le scalaire.
     * @return : Le vecteur multiplié.
     */
    public Vector2 scale(float p_b) {
        return new Vector2(this.getX() * p_b, this.getY() * p_b);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vector2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
