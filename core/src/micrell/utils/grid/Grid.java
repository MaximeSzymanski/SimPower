package micrell.utils.grid;

import micrell.utils.vector.Vector2;
import java.util.ArrayList;
import static micrell.utils.math.Math.clamp01;

/**
 * Classe qui permet de construire une grille de tout éléments qui hérite de Node.
 *
 * @param <T> Le type qu'on veut mettre en grille.
 */
public abstract class Grid<T extends Node> {

    /**
     * Le radius d'un node (pour déterminer combien il y a de node en fonction de la taille de la grid)
     */
    protected float NodeRadius;
    /**
     * Correspond a NodeRadius x 2;
     */
    protected float NodeDiameter;
    /**
     * La taille de la map (Ne correspond pas au nombre de colonnes ou de ligne de la grid)
     */
    protected Vector2 worldSize;
    /**
     * List de list qui contient les éléments. Correspond à la grille.
     */
    protected ArrayList<ArrayList<T>> gridArray;

    /**
     * Nombre de colonnes et nombres de ligne de la grille.
     */
    protected int gridSizeX, gridSizeY;

    /**
     * Constructeur paramétré.
     *
     * @param p_worldSize : taille de la grid.
     * @param p_nodeRadius : radius d'un noeud.
     */
    public Grid(Vector2 p_worldSize, float p_nodeRadius){
        setWorldSize(p_worldSize);
        setNodeRadius(p_nodeRadius);
        setNodeDiameter(getNodeRadius()*2);
        setGridSizeX(Math.round(getWorldSize().getX()/getNodeDiameter()));
        setGridSizeY(Math.round(getWorldSize().getY()/getNodeDiameter()));
        setGridArray();
    }

    /**
     * Méthode qui retourne un node en fonction d'une position donnée en paramètre.
     *
     * @param p_worldPosition : position donnée.
     * @return Le node.
     */
    public T nodeFromWorldPoint(Vector2 p_worldPosition) {
        float percentX = p_worldPosition.getX() / getWorldSize().getX();
        float percentY = p_worldPosition.getY() / getWorldSize().getY();

        percentX = clamp01(percentX);
        percentY = clamp01(percentY);

        int x = (int) (gridSizeX * percentX);
        int y = (int) (gridSizeY * percentY);

        return getNodeInGrid(x, y);
    }

    /**
     * Méthode qui instancie la liste et la remplie de liste afin d'obtenir une liste de listes.
     */
    private void setGridArray() {
        this.gridArray = new ArrayList<>(getGridSizeX());

        for(int x = 0; x < getGridSizeX(); x++){
            this.gridArray.add(x, new ArrayList<T>(getGridSizeY()));
        }
    }

    public void setNodeInGrid(int p_x, int p_y, T p_node){
        this.gridArray.get(p_x).add(p_y, p_node);
    }

    public void changeNodeInGrid(int p_x, int p_y, T p_node){
        this.gridArray.get(p_x).set(p_y, p_node);
    }

    public T getNodeInGrid(int x, int y){
        return this.gridArray.get(x).get(y);
    }

    public int getGridSizeX() {
        return gridSizeX;
    }

    public void setGridSizeX(int gridSizeX) {
        this.gridSizeX = gridSizeX;
    }

    public int getGridSizeY() {
        return gridSizeY;
    }

    public void setGridSizeY(int gridSizeY) {
        this.gridSizeY = gridSizeY;
    }

    public float getNodeRadius() {
        return NodeRadius;
    }

    public void setNodeRadius(float nodeRadius) {
        NodeRadius = nodeRadius;
    }

    public float getNodeDiameter() {
        return NodeDiameter;
    }

    public void setNodeDiameter(float nodeDiameter) {
        NodeDiameter = nodeDiameter;
    }

    public Vector2 getWorldSize() {
        return worldSize;
    }

    public void setWorldSize(Vector2 worldSize) {
        this.worldSize = worldSize;
    }
//    /**
//     * Méthode de debug
//     */
//    public void draw(){
//        for(int y = 0 ; y < getGridSizeX() ; y++){
//            for(int x = 0 ; x < getGridSizeY() ; x++){
//                getNodeInGrid(x, y).draw();
//            }
//            System.out.println();
//        }
//    }
}
