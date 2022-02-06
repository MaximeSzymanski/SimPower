package state;

import actors.BuildingActor;
import actors.ChoosableBuildingActor;
import actors.MiniMapActor;
import actors.informationPanel.GrassPlotDepositPanelActor;
import actors.informationPanel.InformationPanelActor;
import actors.SpriteChangable;
import actors.plot.GrassPlotActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.utbm.build.BuildingManager;
import com.utbm.build.buildings.Building;
import com.utbm.map.plot.GrassPlotDeposit;
import com.utbm.map.plot.Plot;

/**
 * Etat ou un panneau d'information sur une case est affiché a l'ecran.
 */
public class PlotInfoPanelDisplayed implements GameState{

    /**
     * Gestionnaire d'état.
     */
    private GameStateManager gameStateManager;
    /**
     * Gestionnaire de sprite.
     */
    private AssetManager assetManager;

    /**
     * Panneau Libgdx.
     */
    private InformationPanelActor panel;
    /**
     * Boutton Libgdx pour fermer le panneau.
     */
    private Button closeButton;

    /**
     * Constructeur parametré.
     * @param p_gameStateManager Gestionnaire d'etat.
     * @param p_plot Case dont les informations sont affichées.
     * @param p_assetManager Gestionnaire de sprite.
     */
    public PlotInfoPanelDisplayed(GameStateManager p_gameStateManager, Plot p_plot, AssetManager p_assetManager){
        this.gameStateManager = p_gameStateManager;
        this.assetManager = p_assetManager;

        this.panel = new GrassPlotDepositPanelActor((GrassPlotDeposit) p_plot);

        //TODO extract le closeButton en un object
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

        this.gameStateManager.stage.addActor(this.panel);
        this.gameStateManager.stage.addActor(this.closeButton);

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
     * Ferme le panneau actuelle, et ouvre le panneau d'information du plot cliqué.
     * @param p_plot Case sur laquel le joueur a cliqué.
     * @param assetManager Gestionnaire de sprite.
     */
    @Override
    public void clickOnGrassPlotDeposit(Plot p_plot, AssetManager assetManager) {
        this.panel.remove();
        this.closeButton.remove();
        this.gameStateManager.changeState(new PlotInfoPanelDisplayed(this.gameStateManager, p_plot, this.assetManager));
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
     * Met la case en bleu.
     * @param p_area Zone sur laquel la souris est.
     */
    @Override
    public void flightOverBuildableAreaEnter(SpriteChangable p_area) {
        p_area.setDrawable(  new SpriteDrawable(new Sprite((Texture) this.gameStateManager.assetManager.get("plot/plot_WaterPlot.png"))));
    }

    /**
     *
     * @param p_area Zone sur laquel la souris est.
     */
    @Override
    public void flightOverBuildableAreaExit(SpriteChangable p_area) {
        p_area.setSprite(gameStateManager.assetManager);
    }

    @Override
    public void flightOverNonBuildableArea(SpriteChangable p_area) {

    }

    @Override
    public void clickOnBuldozerButton() {

    }

    @Override
    public void destroyBuilding(BuildingManager buildingManager) {

    }

    @Override
    public void closeInformationPannel() {

    }

    @Override
    public void clickOnBuldozer() {

    }
}
