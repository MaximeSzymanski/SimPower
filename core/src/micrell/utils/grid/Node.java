package micrell.utils.grid;

import micrell.utils.vector.Vector2;

/**
 * Classe qui représente un noeud.
 */
public abstract class Node{

    /**
     * Attribut qui permet de savoir si le node est constructible.
     */
    protected boolean walkable; //TODO a mettre dans la classe Plot
    /**
     * Position du node.
     */
    protected Vector2 worldPosition;
    /**
     * index x du node dans la grid.
     */
    protected int gridX;
    /**
     * index y du node dans la grid.
     */
    protected int gridY;
    /**
     * Attribut utilisé par le pathfinder.
     */
    protected int gCost;
    /**
     * Attribut utilisé par le pathfinder.
     */
    protected int hCost;
    /**
     * Prend la valeur du bruit de Perlin pour la création de la map.
     */
    private float noiseValue;

    /**
     * Constructeur paramétré.
     *
     * @param p_walkable : boolean pour savoir s'il est constructible.
     * @param p_worldPos : position du node.
     * @param p_gridX : index x du node dans la grid.
     * @param p_gridY : index y du node dans la grid.
     */
    public Node(boolean p_walkable, Vector2 p_worldPos, int p_gridX, int p_gridY) {
        setWalkable(p_walkable);
        setWorldPosition(p_worldPos);
        setGridX(p_gridX);
        setGridY(p_gridY);
    }

    /**
     * Constructeur par recopie.
     *
     * @param p_n : Le node à recopier.
     */
    public Node(Node p_n){
        setWalkable(p_n.isWalkable());
        setWorldPosition(p_n.getWorldPosition());
        setGridX(p_n.getGridX());
        setGridY(p_n.getGridY());
        setNoiseValue(p_n.getNoiseValue());
    }

    public float getNoiseValue() {
        return noiseValue;
    }

    public void setNoiseValue(float p_noiseValue) {
        this.noiseValue = p_noiseValue;
    }

    public int getGridX() {
        return gridX;
    }

    public void setGridX(int gridX) {
        this.gridX = gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public void setGridY(int gridY) {
        this.gridY = gridY;
    }

    public int getGCost() {
        return this.gCost;
    }

    public int getHCost() {
        return this.hCost;
    }

    public void setGCost(int gCost) {
        this.gCost = gCost;
    }

    public void setHCost(int hCost) {
        this.hCost = hCost;
    }

    public int getFCost(){
        return this.gCost + this.hCost;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean p_walkable) {
        this.walkable = p_walkable;
    }

    public Vector2 getWorldPosition() {
        return this.worldPosition;
    }

    public void setWorldPosition(Vector2 p_worldPosition) {
        this.worldPosition = p_worldPosition;
    }

    @Override
    public String toString() {
        return "Node{" +
                "walkable=" + walkable +
                ", worldPosition=" + worldPosition +
                ", gridX=" + gridX +
                ", gridY=" + gridY +
                ", gCost=" + gCost +
                ", hCost=" + hCost +
                '}';
    }

//    /**
//     * Méthode de debug.
//     */
//    public void draw(){
//        System.out.print("["+ getGridX() + "-" + getGridY() + "]");
//    }
//
//    public void drawNoiseValue(){
//        System.out.print("["+ (int) getNoiseValue() + "]");
//    }
}
