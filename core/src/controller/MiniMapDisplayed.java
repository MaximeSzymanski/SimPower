package controller;

import view.BuildingActor;
import view.ChoosableBuildingActor;
import view.MiniMapActor;
import view.SpriteChangable;
import view.plot.GrassPlotActor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import model.build.BuildingManager;
import model.map.plot.Plot;

/**
 Etat du State Design Pattern où la minimap est affiché.
 */
public class MiniMapDisplayed implements GameState{

    /**
     * Gestionnaire des états.
     */
    private GameStateManager gameStateManager;
    /**
     * Button libgdx pour fermer la mini map.
     */
    private Button closeButton;
    /**
     * Panneau d'information Libgdx.
     */
    private Window panel;

    /**
     * Constructeur parametré.
     * @param p_gameStateManager Gestionnaire des états.
     * @param p_miniMap Acteur de mini map.
     */
    public MiniMapDisplayed(GameStateManager p_gameStateManager, MiniMapActor p_miniMap){
        this.gameStateManager = p_gameStateManager;

        this.panel = new Window(p_miniMap.getName(), new Skin(Gdx.files.internal("skin/default/skin/uiskin.json")));
        this.panel.setPosition(100, 100);
        this.panel.getTitleLabel().setFontScale(0.3f);
        this.panel.setMovable(false);
        this.panel.add(p_miniMap);

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

        this.gameStateManager.stage2.addActor(this.panel);
        this.gameStateManager.stage2.addActor(this.closeButton);

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
    public void clickOnGrassPlotDeposit(Plot p_plot, AssetManager assetManager) {

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
    public void clickOnBuldozer() {

    }
}
