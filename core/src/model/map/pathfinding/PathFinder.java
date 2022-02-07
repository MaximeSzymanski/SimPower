package model.map.pathfinding;

import model.map.GroundMap;
import model.map.plot.Plot;
import model.utils.grid.Node;
import model.utils.vector.Vector2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Permet de trouver le chemin le plus court entre deux node de la carte.
 */
public class PathFinder{

    /**
     * La map du jeu.
     */
    private GroundMap grid;
    /**
     * Le chemin construit par le pathfinder.
     */
    private ArrayList<Plot> path;

    /**
     * Constructeur par défaut.
     *
     * @param p_grid : La carte du jeu.
     */
    public PathFinder(GroundMap p_grid){
        this.grid = p_grid;
    }

    /**
     * Méthode qui trouve le chemin entre deux points de la carte.
     *
     * @param p_startPos : point de départ.
     * @param p_targetPos : point d'arrivée.
     */
    public void findPath(Vector2 p_startPos, Vector2 p_targetPos){
        Plot startNode = this.grid.nodeFromWorldPoint(p_startPos);
        Plot targetNode = this.grid.nodeFromWorldPoint(p_targetPos);
        ArrayList<Plot> openSet = new ArrayList<>();
        HashSet<Plot> closeSet = new HashSet<>();

        openSet.add(startNode);

        while (!openSet.isEmpty()){
            Plot currentNode = openSet.get(0);
            for(int i = 1; i < openSet.size(); i++){
                if(openSet.get(i).getFCost() < currentNode.getFCost()){
                    currentNode = openSet.get(i);
                }else if(openSet.get(i).getFCost() == currentNode.getFCost() && openSet.get(i).getHCost() < currentNode.getHCost()){
                    currentNode = openSet.get(i);
                }
            }

            openSet.remove(currentNode);
            closeSet.add(currentNode);

            if(currentNode == targetNode){
                retracePath(startNode, targetNode);
                return;
            }
            for (Plot neighbour : this.grid.searchNeighbours(currentNode)) {
                if(!neighbour.isWalkable() || closeSet.contains(neighbour)){
                    continue;
                }

                int newMvtCostNeighbours = currentNode.getGCost() + getDistance(currentNode, neighbour);
                if (newMvtCostNeighbours < neighbour.getGCost() || !openSet.contains(neighbour)){
                    neighbour.setGCost(newMvtCostNeighbours);
                    neighbour.setHCost(getDistance(neighbour, targetNode));
                    neighbour.setParent(currentNode);

                    if(!openSet.contains(neighbour)){
                        openSet.add(neighbour);
                    }
                }
            }
        }
    }

    /**
     * Méthode qui retrace de parent en parent pour récuperer le chemin
     *
     * @param p_startNode : Node de départ
     * @param p_targetNode : Node d'arrivée
     */
    public void retracePath(Plot p_startNode, Plot p_targetNode){
        ArrayList<Plot> path = new ArrayList<>();
        Plot currentNode = p_targetNode;

        while(currentNode != p_startNode){
            path.add(currentNode);
            currentNode = currentNode.getParent();
        }
        Collections.reverse(path);
        this.path = path;
    }

    /**
     * Méthode qui retourne la distance (pathfinder A*) entre 2 nodes
     *
     * @param p_nodeA
     * @param p_nodeB
     * @return
     */
    private int getDistance(Node p_nodeA, Node p_nodeB){
        int dstX = Math.abs(p_nodeA.getGridX() - p_nodeB.getGridX());
        int dstY = Math.abs(p_nodeA.getGridY() - p_nodeB.getGridY());

        if(dstX > dstY){
            return 14*dstY + 10*(dstX-dstY);
        }else{
            return 14*dstX + 10*(dstY-dstX);
        }
    }

    public ArrayList<Plot> getPath() {
        return path;
    }
}
