package controller;

import view.BuildingActor;
import view.ChoosableBuildingActor;
import view.MiniMapActor;
import view.SpriteChangable;
import view.plot.GrassPlotActor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import model.Inventory;
import model.build.BuildingManager;
import model.build.buildings.Building;
import model.map.plot.Plot;

import java.util.ArrayList;

/**
 * Manager des états du State Design Pattern.
 */
public class GameStateManager {
    /**
     * Etat actuelle.
     */
    GameState state;

    /**
     * Batiment sur lequel on agit.
     */
    Building building;
    /**
     * Gestionnaire de sprite.
     */
    AssetManager assetManager;
    /**
     * Stage sur lequel on agit.
     */
    Stage stage;
    /**
     * Gestionnaire de batiment.
     */
    BuildingManager buildingManager;
    /**
     * Inventaire du joueur.
     */
    Inventory playerInventory;
    /**
     * Liste des batiments (acteurs Libgdx).
     */
    ArrayList<BuildingActor> buildingActorArrayList;
    /**
     * Stage HUD.
     */
    Stage stage2;

    /**
     * Constructeur paramétré.
     * @param p_assetManager Gestionnaire de sprite.
     * @param p_stage Stage sur lequel on agit.
     * @param buildingManager Gestionnaire de batiment.
     * @param playerInventory Inventaire du joueur.
     * @param stage2 Stage HUD.
     */
    public GameStateManager(AssetManager p_assetManager, Stage p_stage,BuildingManager buildingManager, Inventory playerInventory, Stage stage2){
        this.buildingActorArrayList = new ArrayList<>();
        this.stage2 = stage2;
        this.playerInventory = playerInventory;
        this.buildingManager = buildingManager;
        this.state = new NoAction(this);
        this.assetManager = p_assetManager;
        this.building = null;
        this.stage = p_stage;
    }

    /**
     * Change l'etat actuelle pour celui passé en parametre.
     * @param p_state Etat à prendre.
     */
    public void changeState(GameState p_state){
        this.state = p_state;
    }

    /**
     * Clique sur l'icone de la mini-map.
     * @param p_miniMapActor Acteur de la mini-map.
     */
    public void openMiniMap(MiniMapActor p_miniMapActor){
        this.state.openMiniMap(p_miniMapActor);
    }


    /**
     * Clique sur un batiment dans la building library, qui est constructible en terme de matériaux.
     * @param p_build Batiment sur lequel le joueur a cliqué.
     */
    public void clickOnChoosableBuildingActor(ChoosableBuildingActor p_build) {
        this.state.clickOnChoosableBuildingActor(p_build);
    }

    public void clickOnBuildableArea(GrassPlotActor p_area) {
        this.state.clickOnBuildableArea(p_area);
    }

    /**
     * Clique sur le bouton de la building library.
     */
    public void ClickOnLibraryButton(){
        this.state.ClickOnLibraryButton(this.assetManager);
    }

    /**
     * Clique sur un building sur la carte.
     * @param p_buildingCliked Batiment sur lequel le joueur a cliqué.
     */
    public void clickBuildingOnMap(BuildingActor p_buildingCliked) {
        this.state.clickBuildingOnMap(p_buildingCliked);
    }



    /**
     * Survole une zone constructible (entré)
     * @param p_area Zone sur laquel la souris est.
     */
    public void flightOverBuildableAreaEnter(SpriteChangable p_area) {
        this.state.flightOverBuildableAreaEnter(p_area);
    }
    /**
     * Survole une zone constructible (sortie)
     * @param p_area Zone sur laquel la souris est.
     */
    public void flightOverBuildableAreaExit(SpriteChangable p_area) {
        this.state.flightOverBuildableAreaExit(p_area);
    }

    /**
     * Clique sur une case.
     * @param p_plot Case sur laquel le joueur a cliqué.
     */
    public void clickOnPlot(Plot p_plot){
        this.state.clickOnGrassPlotDeposit(p_plot, this.assetManager);
    }

    /**
     * Clique sur le bouton buldozer.
     */
    public void clickOnBuldozerButton(){
        this.state.clickOnBuldozerButton();
    }

}
