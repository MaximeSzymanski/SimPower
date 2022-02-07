package controller;

import view.BuildingActor;
import view.ChoosableBuildingActor;
import view.MiniMapActor;
import view.SpriteChangable;
import view.informationPanel.InformationPanelActor;
import view.plot.GrassPlotActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import model.build.BuildingManager;
import model.build.buildings.Building;
import model.map.plot.Plot;

/**
 Etat du State Design Pattern où le joueur a cliqué sur un batiment construit sur la carte, et donc il s'affiche un panneau d'information.
 */
public class BuildingInfoPanelDisplayed implements GameState{
    /**
     * Gestionnaire des états.
     */
    private GameStateManager gameStateManager;

    /**
     * Gestionnaire de sprite.
     */
    private AssetManager assetManager;

    /**
     * Panel Libgdx qui contient les informations du batiment.
     */
    private InformationPanelActor panel;
    /**
     * Button Libgdx qui sert a fermer le panneau.
     */
    private Button closeButton;

    /**
     * Constructeur parametré.
     * @param p_gameStateManager Gestionnaire d'états.
     * @param p_stage Stage sur lequel se trouvera le panel.
     * @param p_building Batiment dont les informations sont affichées.
     * @param p_assetManager Gestionnaire de sprite.
     */
    public BuildingInfoPanelDisplayed(GameStateManager p_gameStateManager, Stage p_stage, Building p_building, AssetManager p_assetManager){
        this.gameStateManager = p_gameStateManager;
        this.assetManager = p_assetManager;

        this.panel = new InformationPanelActor(p_building);

        this.closeButton = new TextButton("X", new Skin(Gdx.files.internal("skin/default/skin/uiskin.json")));
        this.closeButton.setColor(1, 0, 0, 1);
        this.closeButton.getSkin().getFont("default-font").getData().setScale(0.3f);
        this.closeButton.setBounds(this.panel.getX()+this.panel.getWidth(), this.panel.getY()+this.panel.getHeight(), 12, 12);
        this.closeButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                panel.remove();
                closeButton.remove();
                gameStateManager.changeState(new NoAction(gameStateManager));
            }
        });

        p_stage.addActor(this.panel);
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
    public void nothing() {

    }

    /**
     * Ne fait rien.
     */
    @Override
    public void clickOnChoosableBuildingActor(ChoosableBuildingActor p_build) {

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
    public void ClickOnLibraryButton(AssetManager p_assetManager) {

    }

    /**
     * Ne fait rien.
     */
    @Override
    public void closeLibrary() {

    }

    /**
     * Met la zone sur laquel la souris est en bleu.
     * @param p_area Zone sur laquel la souris est.
     */
    @Override
    public void flightOverBuildableAreaEnter(SpriteChangable p_area) {
        p_area.setDrawable(  new SpriteDrawable(new Sprite((Texture) this.gameStateManager.assetManager.get("plot/plot_WaterPlot.png"))));
    }

    /**
     * Remet le sprite originel de la zone;
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
    public void destroyBuilding(BuildingManager buildingManager) {

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
    public void clickOnBuldozerButton() {

    }


    /**
     * Ferme ce panneau et ouvre un panneau contenant les informations de l'autre batiment.
     * @param buildingclicked Batiment sur lequel le joueur a cliqué.
     */
    @Override
    public void clickBuildingOnMap(BuildingActor buildingclicked) {
        this.panel.remove();
        this.closeButton.remove();
        this.gameStateManager.changeState(new BuildingInfoPanelDisplayed(this.gameStateManager, this.gameStateManager.stage2, buildingclicked.getBuildingData(), this.assetManager));
    }

    /**
     * Ne fait rien.
     */
    @Override
    public void clickOnBuldozer() {

    }
}
