package state;

import actors.*;
import actors.plot.GrassPlotActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.utbm.build.BuildingManager;
import com.utbm.build.buildings.Building;
import com.utbm.map.plot.Plot;
/**
 * Etat du State Design Pattern où le joueur doit choisir une batiment pour faire une construction.
 */
public class BuildingChoice implements GameState{

    /**
     * Gestionnaire des états.
     */
    private GameStateManager gameStateManager;
    /**
     * Gestionnaire de sprite.
     */
    private AssetManager assetManager;
    /**
     * Building library.
     */
    private final BuildingLibraryActor library;
    /**
     * Bouton pour fermer la building library.
     */
    private Button closeButton;

    /**
     * Ne fait rien.
     * @param buildingclicked Batiment sur lequel le joueur a cliqué.
     */
    @Override
    public void clickBuildingOnMap(BuildingActor buildingclicked) {

    }
    /**
     * Ne fait rien.
     */
    @Override
    public void clickOnBuldozer() {

    }

    /**
     * Constructeur parametré.
     * @param p_gameStateManager Gestionnaire des états.
     * @param p_assetManager Gestionnaire des sprites.
     * @param p_stage Stage sur lequel est la building library.
     */
    public BuildingChoice(GameStateManager p_gameStateManager, AssetManager p_assetManager, Stage p_stage){
        this.gameStateManager = p_gameStateManager;
        this.assetManager = p_assetManager;

        this.library = new BuildingLibraryActor(this.assetManager, gameStateManager);


        this.closeButton = new TextButton("X", new Skin(Gdx.files.internal("skin/default/skin/uiskin.json")));
        this.closeButton.setColor(1, 0, 0, 1);
        this.closeButton.getSkin().getFont("default-font").getData().setScale(0.3f);
        this.closeButton.setBounds(this.library.getX()+this.library.getWidth(), this.library.getY()+this.library.getHeight(), 12, 12);
        this.closeButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                library.remove();
                closeButton.remove();
                gameStateManager.changeState(new NoAction(gameStateManager));
            }
        });


        p_stage.addActor(this.library);
        p_stage.addActor(this.closeButton);
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
    public void clickOnGrassPlotDeposit(Plot p_plot, AssetManager assetManager) {

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
    public void closeInformationPannel() {
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
    public void clickOnBuildableArea(GrassPlotActor p_area) {
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
     * Lance l'état de choix de zone de construction.
     * @param p_build Batiment sur lequel le joueur a cliqué.
     */
    @Override
    public void clickOnChoosableBuildingActor(ChoosableBuildingActor p_build) {
        this.library.remove();
        this.closeButton.remove();
        this.gameStateManager.changeState(new AreaChoice(gameStateManager,p_build));
    }

    /**
     * Ne fait rien.
     */
    @Override
    public void flightOverBuildableAreaEnter(SpriteChangable p_area) {

    }

    /**
     * Ne fait rien.
     */
    @Override
    public void flightOverBuildableAreaExit(SpriteChangable p_area) {

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
    public void clickOnBuldozerButton() {

    }

    /**
     * Ne fait rien.
     */
    @Override
    public void destroyBuilding(BuildingManager buildingManager) {
    }
}
