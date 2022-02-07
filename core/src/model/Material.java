package model;

/**
 * Enum contenant tous les matériaux du jeu.
 */
public enum Material {

    URANIUM("Uranium"),
    COAL("Coal"),
    GAS("Gas"),
    IRON("Iron"),
    STONE("Stone"),
    OIL("Oil"),
    WOOD("Wood"),
    WATER("Water"),
    POLLUTING_ENERGY("Polluting Energy"),
    GREEN_ENERGY("Green Energy"),
    JUDOKOIN("Judokoin"),
    POLLUTION("Pollution"),
    BOTH("Both");
    /**
     * Nom du matériau.
     */
    private final String name;

    /**
     * Constructeur parametré.
     * @param p_name Nom du matérial.
     */
    Material(String p_name){
        this.name = p_name;
    }

    public String getName(){
        return this.name;
    }

}

