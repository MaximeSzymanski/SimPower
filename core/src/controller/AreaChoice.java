package controller;

import view.BuildingActor;
import view.ChoosableBuildingActor;
import view.MiniMapActor;
import view.SpriteChangable;
import view.plot.GrassPlotActor;
import com.badlogic.gdx.assets.AssetManager;
import model.build.BuildingManager;
import model.map.area.Area;
import model.map.plot.Plot;

/**
 * Etat du State Design Pattern où le joueur doit choisir une zone pour construire un batiment déja selectionné.
 */
public class AreaChoice implements GameState {

    /**
     * Gestionnaire des états.
     */
    GameStateManager gameStateManager;

    /**
     * Batiment choisis par le joueur pour etre construit.
     */
    ChoosableBuildingActor building;

    /**
     * Constructeur parametré.
     * @param p_gameStateManager Gestionnaire d'etats.
     * @param p_building Batiment choisis pour etre construit.
     */
    public AreaChoice(GameStateManager p_gameStateManager, ChoosableBuildingActor p_building){
        this.gameStateManager = p_gameStateManager;
        this.building = p_building;
        System.out.println("AreaChoice");
    }

    /**
     * Ne fait rien.
     */
    @Override
    public void closeInformationPannel() {

    }

    /**
     * Le joueur retourne a la selection de batiment.
     * @param p_assetManager Gestionnaire de sprite.
     */
    @Override
    public void ClickOnLibraryButton(AssetManager p_assetManager) {
        this.gameStateManager.changeState(new BuildingChoice(this.gameStateManager,p_assetManager,this.gameStateManager.stage2));
    }

    /**
     * Ne fait rien.
     */
    @Override
    public void clickOnGrassPlotDeposit(Plot p_plot, AssetManager assetManager) {

    }

    /**
     * Ne fait rien.
     */
    @Override
    public void nothing() {

    }

    /**
     * Ne fait rien.
     */
    @Override
    public void clickOnUnaffordableBuild() {

    }

    /**
     * Ne fait rien.
     */
    @Override
    public void clickOnChoosableBuildingActor(ChoosableBuildingActor p_build) {
    }

    /**
     * Affiche sur la case le batiment choisis.
     * @param p_area Zone sur laquel la souris est.
     */
    @Override
    public void flightOverBuildableAreaEnter(SpriteChangable p_area) {
        p_area.setDrawable(this.building.getDrawable());
    }

    /**
     * Enleve sur la case le batiment choisis, en remettant le sprite de base.
     * @param p_area Zone sur laquel la souris est.
     */
    @Override
    public void flightOverBuildableAreaExit(SpriteChangable p_area) {
        p_area.setSprite(gameStateManager.assetManager);
    }


    /**
     * Ne fait rien.
     */
    @Override
    public void flightOverNonBuildableArea(SpriteChangable p_area) {

    }

    /**
     * Ne fait rien.
     */
    @Override
    public void openMiniMap(MiniMapActor p_miniMap) {

    }

    /**
     * Ne fait rien.
     */
    @Override
    public void clickBuildingOnMap(BuildingActor buildingclicked) {

    }

    /**
     * Construit le batiment a l'endroit choisis, et ajoute un nouvel acteur au stage.
     * @param p_area Zone sur laquel le joueur a cliqué.
     */
    @Override
    public void clickOnBuildableArea(GrassPlotActor p_area) {
        if(this.gameStateManager.playerInventory.getInventory().isStockAffordable(this.building.getBuildingData().getCost())){

            BuildingActor newBuild = new BuildingActor(this.building.getBuildingData(),this.gameStateManager.assetManager, this.gameStateManager);
            newBuild.setBounds(p_area.getX(),p_area.getY(),4,4);
            this.gameStateManager.buildingManager.addNewBuilding(this.building.getBuildingData(),new Area(p_area.getGrassplot()));
//            newBuild.setVisible(true);
//            newBuild.setDebug(true);
            this.gameStateManager.stage.addActor(newBuild);
            System.out.println(newBuild.getX() + "  " + newBuild.getY());

        }else{
            System.out.println("PAS CONSTRUCTIBLE");
        }

        this.gameStateManager.changeState(new BuildingChoice(this.gameStateManager,this.gameStateManager.assetManager, this.gameStateManager.stage2));
    }

    /**
     * Ne fait rien.
     */
    @Override
    public void clickOnBuldozer() {

    }

    /**
     * Ne fait rien.
     */
    @Override
    public void clickOnUnbuildableArea() {

    }

    /**
     * Ne fait rien.
     */
    @Override
    public void closeLibrary() {

    }

    /**
     * Ne fait rien.
     */
    @Override
    public void clickOnBuldozerButton() {

    }

    /**
     * Ne fait rien.
     */
    @Override
    public void destroyBuilding(BuildingManager buildingManager) {

    }
}
