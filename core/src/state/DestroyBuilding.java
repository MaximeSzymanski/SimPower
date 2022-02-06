package state;

import actors.BuildingActor;
import actors.ChoosableBuildingActor;
import actors.MiniMapActor;
import actors.SpriteChangable;
import actors.plot.GrassPlotActor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.utbm.build.BuildingManager;
import com.utbm.build.buildings.Building;
import com.utbm.build.buildings.Pylon;
import com.utbm.map.area.Area;
import com.utbm.map.plot.Plot;

/**
 Etat du State Design Pattern où le joueur a cliqué sur le buldozer, et ou peut cliquer sur les batiments a detruire.
 */
public class DestroyBuilding implements GameState {

    /**
     * Gestionnnaire des états.
     */
    GameStateManager gameStateManager;

    /**
     * Constructeur parametré.
     * @param p_gameStateManager Gestionnaire des états.
     */
    public DestroyBuilding(GameStateManager p_gameStateManager){
        this.gameStateManager = p_gameStateManager;

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
    public void ClickOnLibraryButton(AssetManager p_assetManager) {

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
     * Affiche le dessin du buldozer sur la case.
     * @param p_area Zone sur laquel la souris est.
     */
    @Override
    public void flightOverBuildableAreaEnter(SpriteChangable p_area) {
         p_area.setDrawable(new TextureRegionDrawable((Texture) gameStateManager.assetManager.get("button/BuldozerButton.png")) );

    }

    /**
     * Enleve le dessin du buldozer sur la case, et remet le sprite originel.
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
    public void clickOnBuildableArea(GrassPlotActor p_area) {

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
     * Remet dans l'etat NoAction.
     */
    @Override
    public void clickOnBuldozerButton() {
        this.gameStateManager.changeState(new NoAction(this.gameStateManager));

    }

    /**
     * Detruit un batiment et remet dans l'etat NoAction.
     * @param buildingclicked Batiment sur lequel le joueur a cliqué.
     */
    @Override
    public void clickBuildingOnMap(BuildingActor buildingclicked) {
         this.gameStateManager.buildingManager.removeBuilding(buildingclicked.getBuildingData());
        buildingclicked.remove();
        this.gameStateManager.changeState(new NoAction(this.gameStateManager));
    }

    /**
     * Ne fait rien.
     */
    @Override
    public void destroyBuilding(BuildingManager buildingManager) {

    }
}
