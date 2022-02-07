package model.utils;

import java.util.ArrayList;
import java.util.HashMap;


public class Graph <T> {

    public HashMap<T, ArrayList<T>> getAdjVertices() {
        return adjVertices;
    }

    private HashMap<T, ArrayList<T>> adjVertices;

    public Graph(){

        this.adjVertices = new HashMap<>();
    }

    public void addVertex(T p_node){
        if(!adjVertices.containsKey(p_node)) {
            adjVertices.put(p_node, new ArrayList<T>());
        }
    }

    public void connectTwoBuilding(T p_firstNode,T p_secondNode){


           addVertex(p_firstNode);
           addVertex(p_secondNode);

            addEdge(p_firstNode,p_secondNode);
    }


    /*public void removeVertex(T p_node){
        for(Building current : this.getAdjVertices(p_node)){
            current.getPylonNetwork().getAdjVertices().remove(p_node,current);
        }
        this.adjVertices.remove(p_node);

    }*/

    void addEdge(T p_firstNode,T p_secondNode){


        if( !adjVertices.get(p_firstNode).contains(p_secondNode)) {
            adjVertices.get(p_firstNode).add(p_secondNode);
            adjVertices.get(p_secondNode).add(p_firstNode);
        }
    }

    void removeEdge(T p_firstNode,T p_secondNode){
            Vertex firstBuild = new Vertex(p_firstNode);
            Vertex secondBuild = new Vertex(p_secondNode);
            ArrayList<T> eV1 = adjVertices.get(p_firstNode);
            ArrayList<T> eV2 = adjVertices.get(p_secondNode);
            if (eV1 != null)
                eV1.remove(p_secondNode);
            if (eV2 != null)
                eV2.remove(p_firstNode);
    }

    public ArrayList<T> getAdjVertices(T p_Node) {
        return adjVertices.get(p_Node);
    }


    public void setAdjVertices(HashMap<T, ArrayList<T>> adjVertices) {
        this.adjVertices = adjVertices;
    }
}
