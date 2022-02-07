package model.utils;


public class Vertex <T> {

    private T node;

    Vertex(T p_node){
        this.node = p_node;
    }


    public T getBuilding() {
        return node;
    }
}
