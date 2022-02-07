
package model.electricity;

import model.Material;
import model.build.buildings.*;
import model.map.GroundMap;
import model.map.plot.Plot;
import model.utils.Graph;

import java.util.*;

/**
 * Classe représentant le réseau electrique de la ville. Elle hérite de Graphe, car cette classe est un graphe dont les sommets sont des batiments.
 * Lorsqu'un batiment de production d'energie stop sa production, tous les batiments rendent leur energie au réseau, puis on enleve au réseau l'energie que donnait le batiment, et enfin on redistrubue l'energie dans les batiments, autant que possible.
 */
public class ElectricityNetwork extends Graph<Building> {
    public void setMap(GroundMap map) {
        this.map = map;
    }

    /**
     * Carte du jeu, contenant toutes les cases et leurs informations.
     */
    private GroundMap map;

    /**
     * Constructeur par défaut.
     */
    public ElectricityNetwork(){
        super();
    }


    /**
     * Cette méthode renvoie tout les batiments connecté au batiment entré en parametre, grace a un algorithme de parcours en largeur.
     * @param p_root Batiment dont on veut obtenir les voisins.
     * @return Liste de batiments connectés au root.
     */
    public Set<Building> breadthFirstTraversal(Building p_root) {
        Set<Building> visited = new LinkedHashSet<>();
        Queue<Building> queue = new LinkedList<>();
        queue.add(p_root);
        visited.add(p_root);
        while (!queue.isEmpty()) {
            Building vertex = queue.poll();
            if (this.getAdjVertices(vertex) != null) {
                for (Building v : this.getAdjVertices(vertex)) {
                    if (!visited.contains(v)) {
                        visited.add(v);
                        queue.add(v);
                    }
                }
            }
        }
        visited.remove(p_root);
        return visited;
    }

    /**
     * Retire l'energie au réseau lorsqu'un batiment se connecte au réseau et consomme.
     * @param p_building Batiment qui consomme.
     * @param p_pollutingToRemove Energie polluante consommée.
     * @param p_greenToRemove Energie verte consommée.
     */
    public void retrieveElectricityOnNetwork(Building p_building, int p_pollutingToRemove,int p_greenToRemove ) {
        Set<Building> visited = this.breadthFirstTraversal(p_building);
         for(Building buildingVisited : visited){ // Pour tout les batiments connectés au root

            if(isaPylon(buildingVisited)){ // si c'est un pylone

                buildingVisited.getFullStock().lower(Material.POLLUTING_ENERGY, p_pollutingToRemove);  // on enleve l'energie necessaire
                buildingVisited.getFullStock().lower(Material.GREEN_ENERGY, p_greenToRemove); // on enleve l'energie necessaire
            }
        }
        if(p_building instanceof EnergyProductionBuilding){
            this.updateNetworkWhenUnableToProduct();
        }
    }

    /**
     * Diffuse l'energie du réseau dans les batiments.
     */
    public void diffuseEnergy(){

        for(Building currentBuilding : this.getAdjVertices().keySet()){ //On diffuse l'energie dans tout les pylones, pour qu'ils aient tous la meme energie
            this.getAdjVertices(currentBuilding).remove(currentBuilding);
            if(isaPylon(currentBuilding) && currentBuilding.getFullStock().getStock(Material.POLLUTING_ENERGY) >0){
                for(Building connected : this.getAdjVertices(currentBuilding)){
                    if(connected instanceof Pylon && connected.getFullStock().getStock(Material.POLLUTING_ENERGY) < currentBuilding.getFullStock().getStock(Material.POLLUTING_ENERGY) ){
                        connected.getFullStock().resetStock();
                        connected.getFullStock().setStock(Material.POLLUTING_ENERGY,currentBuilding.getFullStock().getStock(Material.POLLUTING_ENERGY));
                        connected.getFullStock().setStock(Material.GREEN_ENERGY,currentBuilding.getFullStock().getStock(Material.GREEN_ENERGY));

                    }
                }
            }
        }

        for(Building currentBuilding : this.getAdjVertices().keySet()){ // Ensuite, les batiments a proximité des pylones consomme l'energie du réseau
            if(isaPylon(currentBuilding) ){
                for(Building connected : getBuildingsNeighbour(currentBuilding)){
                    if(isNeedingEnergy(connected) && isEnoughElectricity(currentBuilding, connected) && !(connected instanceof Pylon)){
                        connected.getFullStock().resetStock();

                        addGreenEnergyToHouse(connected);
                        addPollutingEnergyToHouse(connected);


                    }
                }
            }
        }
        for(Building buildingInNetwork : this.getAdjVertices().keySet() ){ // Enfin, on met a jour le batiment s'il est alimenté.
            buildingInNetwork.updateElectricity();
        }
    }

    /**
     * Méthode qui indique si un pylone contient assez d'electricité pour alimenter un batiment.
     * @param currentBuilding Pylone qui contient de l'electricité.
     * @param connected Batiment à alimenter.
     * @return Renvoie vrai si le batiment peut etre alimenté, faux sinon.
     */
    private boolean isEnoughElectricity(Building currentBuilding, Building connected) {
        if(currentBuilding.getFullStock().getStock().get(Material.POLLUTING_ENERGY) > 0) {
            return currentBuilding.getFullStock().isStockAffordable(connected.getProductionCost());
        }else{
            return false;
        }
    }


    /**
     * Méthode qui indique si le batiment a besoin d'énergie pour fonctionner.
     * @param connected Batiment dont on veut savoir le besoin en energie.
     * @return Renvoie vrai si le batiment requiert de l'energie pour fonctionner, faux sinon.
     */
    private boolean isNeedingEnergy(Building connected) {
        return ((connected instanceof House || connected instanceof Factory) && !connected.isAlimented()) ;
    }

    /**
     * Méthode qui indique si un batiment est une instance de Pylon.
     * @param currentBuilding Batiment dont on veut connaitre la nature.
     * @return Renvoie vrai si le batiment est un pylon, faux sinon.
     */
    private boolean isaPylon(Building currentBuilding) {
        return currentBuilding instanceof Pylon;
    }

    /**
     * Redonne au réseau l'energie consommé par le batiment entré en parametre. Lorsqu'un batiment de production d'energie stop sa production, tous les batiments rendent leur energie aux pylones auxquel ils sont connectés, puis on enleve au réseau l'energie que donnait le batiment, et enfin on redistrubue l'energie dans les batiments, autant que possible.
     * @param p_build Batiment qui rend son energie.
     * @param p_amountOfGreenEnergy Energie polluante à rendre.
     * @param p_amountOfPollutingEnergy Energie verte à rendre.
     */
    public void giveBackEnergyToNetwork(Building p_build,int p_amountOfGreenEnergy, int p_amountOfPollutingEnergy){
        Set<Building> visited = breadthFirstTraversal(p_build);
        for(Building buildingVisited : visited ) {
            if (isaPylon(buildingVisited)) {
                buildingVisited.getFullStock().fill(Material.POLLUTING_ENERGY, p_amountOfPollutingEnergy);
                buildingVisited.getFullStock().fill(Material.GREEN_ENERGY, p_amountOfGreenEnergy);
            }
        }

    }


    /**
     * Méthode qui diffuse l'energie produite par un batiment. L'énergie produite par le batiment passé en parametre est diffusée à tout les pylones auquel il est connecté. L'energie est ensuite diffusée dans les différents batiments grace à la méthode this.diffuseEnergy().
     * @param p_building Batiment qui propage son énergie.
     */
    public void broadcastElectricity(Building p_building) {

        Set<Building> visited = breadthFirstTraversal(p_building);
        for(Building buildingVisited : visited){
                if(isaPylon(buildingVisited)) {
                    giveEnergyToPylon(p_building, buildingVisited);
                }
        }
        this.diffuseEnergy();

    }

    /**
     * Méthode qui permet de donner de l'energie à un pylone.
     * @param p_building Batiment donnant son energie au pylone.
     * @param buildingVisited Pylone recevant l'energie.
     */
    private void giveEnergyToPylon(Building p_building, Building buildingVisited) {
        buildingVisited.getFullStock().fill(p_building.getMaterialProduced(), p_building.getProductionRate());
    }

    /**
     * Met a jour le réseau quand un batiment de production d'energie cesse de produire. D'abord, tous les batiments du réseau rendent leur energie, ensuite on retire du réseau l'energie apporté par le batiment de production qui s'est éteint, et enfin on diffuse l'energie restante dans les différents batiments du réseau.
     */
    public void updateNetworkWhenUnableToProduct(){
        for(Building buildingInNetwork : this.getAdjVertices().keySet() ){
            buildingInNetwork.giveBackEnergyToNetwork();
        }
       this.diffuseEnergy();



    }

    public void updateNetworkWhenDemolish(){
        for(Building buildingInNetwork : this.getAdjVertices().keySet() ){
            this.connectToNeighbour(buildingInNetwork);
        }
    }

    /**
     * Méthode qui connecte un batiment aux pylones du réseau. Le batiment se connecte aux pylones qui sont à moins de 10 cases de distances.
     * @param p_building Batiment à connecter aux pylones.
     */
    public void connectToPylons(Building p_building){
            ArrayList<Building> buildToLink = new ArrayList<>();
            for(Building current : this.getAdjVertices().keySet()){
                if(isaPylon(current) && isaPylon(p_building) && this.map.distanceBetweenTwoBuilding(p_building, current) <= 10 ){
                    buildToLink.add(current);
                }
            }

            for(Building current : buildToLink){
                    this.connectTwoBuilding(p_building,current);

            }
        if(this.getAdjVertices().containsKey(p_building)){
        }

    }


    public boolean isInNetwork(Building p_input){
        if(!containsPylon()){
            return true;
        }

        for(Building currentBuild : this.getAdjVertices().keySet()){
            if(isaPylon(currentBuild) && this.map.distanceBetweenTwoBuilding(currentBuild,p_input) <= 10){
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode servant à savoir si le réseau contient au moins un pylone.
     * @return Renvoie vrai si le réseau contient au moins un pylone, faux sinon.
     */
    public boolean containsPylon(){
        for(Building current : this.getAdjVertices().keySet()){
            if(isaPylon(current)){
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode donnant une liste, représentant les batiments autour du batiment passé en parametre.
     * @param p_building Batiment dont on veut connaitre les batiments adjacents.
     * @return Liste contenant les batiments adjacents au batiment passé en parametre.
     */
    private  ArrayList<Building> getBuildingsNeighbour(Building p_building){
        ArrayList<Building> buildingList = new ArrayList();

        for(Plot area : p_building.getArea().getPlotList()){
            for(Plot neighbour : area.getNeighbours()){

                if(neighbour.getBuildingOnit() != null){
                    buildingList.add(neighbour.getBuildingOnit());

                }
            }
        }
        return buildingList;
    }

    /**
     * Méthode qui connecte un batiment aux batiments adjacents.
     * @param p_building Batiments à connecter.
     */
    public void connectToNeighbour(Building p_building){
        ArrayList<Building> neighbour = getBuildingsNeighbour(p_building);
        for(Building currentNeighbour : neighbour){
            this.addVertex(p_building);
            this.connectTwoBuilding(p_building,currentNeighbour);
        }

    }

    /**
     * Méthode indiquant si le batiment passé en paramètre est connecté à au moins un pylone.
     * @param p_building Batiment dont on veut connaitre la connexion avec un pylone.
     * @return Renvoie vrai si le batiment passé en paramètre est connecté à au moins un pylone.
     */
    public boolean isConnectedToPylon(Building p_building){
        ArrayList<Building> neighbour = getBuildingsNeighbour(p_building);
        for(Building currentNeighbour : neighbour){
            if(currentNeighbour instanceof Pylon){
                return true;
            }
        }
        return false;
    }

    /*private void removeGreenEnergyToPylon(Building currentBuilding, Building connected) {
        currentBuilding.getFullStock().lower(Material.GREEN_ENERGY, connected.getProductionCost().getStock(Material.GREEN_ENERGY));
    }

    private void removePollutingEnergyToPylon(Building currentBuilding, Building connected) {
       currentBuilding.getFullStock().lower(Material.POLLUTING_ENERGY, connected.getProductionCost().getStock(Material.POLLUTING_ENERGY));
    }*/

    /**
     * Méthode qui ajoute de l'energie polluante à une maison.
     * @param connected Maison a laquelle on veut ajouter de l'energie polluante.
     */
    private void addPollutingEnergyToHouse(Building connected) {
        connected.getFullStock().fill(Material.POLLUTING_ENERGY,connected.getProductionCost().getStock(Material.POLLUTING_ENERGY));
    }
    /**
     * Méthode qui ajoute de l'energie verte à une maison.
     * @param connected Maison a laquelle on veut ajouter de l'energie verte.
     */
    private void addGreenEnergyToHouse(Building connected) {
        connected.getFullStock().fill(Material.GREEN_ENERGY,connected.getProductionCost().getStock(Material.GREEN_ENERGY));
    }


    /**
     * Méthode qui enleve un batiment au réseau. Autrement dit, on enleve un sommet au graphe.
     * @param p_node Batiment à retirer du réseau.
     */
    public void removeVertex(Building p_node){
        ArrayList<Building> Toremove = new ArrayList<>();

        for(Building current : this.getAdjVertices(p_node)){

            if (  current.getPylonNetwork().getAdjVertices().get(current) != null) {
                Toremove.add(current);

            }
        }
        for(Building current :Toremove){

            current.getPylonNetwork().getAdjVertices().get(current).remove(p_node);

        }
        this.getAdjVertices().remove(p_node);
    }
}
