package state;

import actors.BuildingActor;
import actors.ChoosableBuildingActor;
import actors.MiniMapActor;
import actors.SpriteChangable;
import actors.plot.GrassPlotActor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.utbm.build.BuildingManager;
import com.utbm.build.buildings.Building;
import com.utbm.map.plot.Plot;
/**
 Etat du State Design Pattern où le joueur ne fait rien.
 */
public class NoAction implements GameState{
    /**
     * Gestionnaire des états.
     */
    GameStateManager gameStateManager;

    /**
     * Constructeur parametré.
     * @param p_gameStateManager Gestionnaire d'etats.
     */
    public NoAction(GameStateManager p_gameStateManager){
        this.gameStateManager = p_gameStateManager;
    }

    /**
     * Affiches les infos du plot.
     * @param p_plot Case sur laquel le joueur a cliqué.
     * @param p_assetManager Gestionnaire de sprite.
     */
    @Override
    public void clickOnGrassPlotDeposit(Plot p_plot, AssetManager p_assetManager) {
        this.gameStateManager.changeState(new PlotInfoPanelDisplayed(this.gameStateManager, p_plot, p_assetManager));
    }

    /**
     * Affiche la building library.
     * @param p_assetManager Gestionnaire de sprite.
     */
    @Override
    public void ClickOnLibraryButton(AssetManager p_assetManager) {
        this.gameStateManager.changeState(new BuildingChoice(this.gameStateManager, p_assetManager, this.gameStateManager.stage2));
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
     * Met dans l'etat de destuction de batiment.
     */
    @Override
    public void clickOnBuldozerButton() {
        this.gameStateManager.changeState(new DestroyBuilding(this.gameStateManager));

    }

    /**
     * Affiche le panneau des informations d'un batiment.
     * @param buildingclicked Batiment sur lequel le joueur a cliqué.
     */
    @Override
    public void clickBuildingOnMap(BuildingActor buildingclicked) {
        this.gameStateManager.changeState(new BuildingInfoPanelDisplayed(this.gameStateManager, this.gameStateManager.stage2, buildingclicked.getBuildingData(), this.gameStateManager.assetManager));

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
    public void clickOnBuildableArea(GrassPlotActor p_area) {

    }

    /**
     * Ne fait rien.
     */
    @Override
    public void clickOnChoosableBuildingActor(ChoosableBuildingActor p_build) {

    }

    /**
     * Change la couleur de la case en bleue.
     * @param p_area Zone sur laquel la souris est.
     */
    @Override
    public void flightOverBuildableAreaEnter(SpriteChangable p_area) {
        p_area.setDrawable(  new SpriteDrawable(new Sprite((Texture) this.gameStateManager.assetManager.get("plot/plot_WaterPlot.png"))));
    }

    /**
     * Remet la couleur de la case a la couleur normal.
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
    public void clickOnUnbuildableArea() {

    }

    /**
     * Ne fait rien.
     */
    @Override
    public void closeLibrary() {

    }

    /**
     * Ouvre la mini map.
     * @param p_miniMap Acteur de la mini-map.
     */
    @Override
    public void openMiniMap(MiniMapActor p_miniMap) {
        this.gameStateManager.changeState(new MiniMapDisplayed(this.gameStateManager, p_miniMap));
    }


    /**
     * Ne fait rien.
     */
    @Override
    public void closeInformationPannel() {

    }

    /**
     * Ne fait rien.
     */
    @Override
    public void destroyBuilding(BuildingManager buildingManager) {

    }
}
