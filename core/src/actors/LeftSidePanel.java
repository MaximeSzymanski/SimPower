package actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.utbm.GameManager;
import com.utbm.Material;
import state.GameStateManager;

import java.util.ArrayList;

public class LeftSidePanel extends Table {

    private final GameStateManager gameStateManager;
    private AssetManager assetManager;
    private GameManager gameManager;
    private Table resourcesPanel;

    public LeftSidePanel(AssetManager p_AssetManager, GameStateManager p_gameStateManager, GameManager p_gameGameManager){
        super();
        this.assetManager = p_AssetManager;
        this.gameStateManager = p_gameStateManager;
        this.gameManager = p_gameGameManager;
        this.setTouchable(Touchable.enabled);
        this.setBounds(5, 90, 70, 210);
        this.setBackground(createBackgroundSprite());
        this.setZIndex(1);
        createResourcesPanel();

        this.add(this.resourcesPanel);
        this.row();
        this.add(createToolsPanel()).height(20);
        this.row();
        this.add(createMiniMapButtonPanel()).height(20);
    }

    private Table createMiniMapButtonPanel(){
        Table miniMapButton = new Table();
        miniMapButton.add(new SunMiniMapButtonActor(this.assetManager, this.gameStateManager, this.gameManager.getMapManager().getGroundMap())).width(20);
        miniMapButton.add(new WindMiniMapButtonActor(this.assetManager, this.gameStateManager, this.gameManager.getMapManager().getGroundMap())).width(20);
        miniMapButton.add(new PollutionMiniMapButtonActor(this.assetManager, this.gameStateManager, this.gameManager.getMapManager().getGroundMap())).width(20);

        return miniMapButton;
    }

    private Table createToolsPanel(){
        Table tool = new Table();
        tool.add(new BuildingLibraryButton(this.assetManager,this.gameStateManager)).width(20);
        tool.add(new BuldozerButton(this.assetManager,this.gameStateManager)).width(20);

        return tool;
    }

    private Table createSingleMaterialRow(Material p_material){

        Table table = new Table();
        table.columnDefaults(0).width(50);

        int stock = this.gameManager.getPlayerInventory().getInventory().getStock(p_material);

        Label text = new Label(p_material.getName() + ": "+stock, new Skin(Gdx.files.internal("skin/default/skin/uiskin.json")));
        text.setFontScale(0.3f);

        Image image = new Image((Texture) this.assetManager.get("material/Material_"+ p_material.getName() +".png"));
        image.setScale(0.8f);

        table.add(text);
        table.add(image);

        return table;
    }

    private void createResourcesPanel(){
        this.resourcesPanel = new Table();
        this.resourcesPanel.defaults().height((this.getHeight()/2)/5);

        this.resourcesPanel.add(createSingleMaterialRow(Material.WOOD)).expandX();
        this.resourcesPanel.row();
        this.resourcesPanel.add(createSingleMaterialRow(Material.STONE)).expandX();
        this.resourcesPanel.row();
        this.resourcesPanel.add(createSingleMaterialRow(Material.COAL)).expandX();
        this.resourcesPanel.row();
        this.resourcesPanel.add(createSingleMaterialRow(Material.IRON)).expandX();
        this.resourcesPanel.row();
        this.resourcesPanel.add(createSingleMaterialRow(Material.OIL)).expandX();
        this.resourcesPanel.row();
        this.resourcesPanel.add(createSingleMaterialRow(Material.GAS)).expandX();
        this.resourcesPanel.row();
        this.resourcesPanel.add(createSingleMaterialRow(Material.URANIUM)).expandX();
        this.resourcesPanel.row();
        this.resourcesPanel.add(createSingleMaterialRow(Material.JUDOKOIN)).expandX();
    }

    private TextureRegionDrawable createBackgroundSprite(){
        Pixmap bgPixmap = new Pixmap(1,1, Pixmap.Format.RGB565);
        bgPixmap.setColor(Color.GRAY);
        bgPixmap.fill();
        TextureRegionDrawable background = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));

        return background;
    }


    public void render(){

       /* ArrayList<Material> materialsArrayList = new ArrayList<>();
        materialsArrayList.add(Material.WOOD);
        materialsArrayList.add(Material.STONE);
        materialsArrayList.add(Material.COAL);
        materialsArrayList.add(Material.IRON);
        materialsArrayList.add(Material.OIL);
        materialsArrayList.add(Material.GAS);
        materialsArrayList.add(Material.URANIUM);
        materialsArrayList.add(Material.JUDOKOIN);

        for(Material currentMat : materialsArrayList){
            int stock = this.gameManager.getPlayerInventory().getInventory().getStock(currentMat);
            Table materialTable = (Table) this.resourcesPanel.getChild(0);
            Label materialText = (Label) materialTable.getChild(0);
            materialText.setText(currentMat.getName() + ": "+stock);
        }
*/
        int stock = this.gameManager.getPlayerInventory().getInventory().getStock(Material.WOOD);
        Table wood = (Table) this.resourcesPanel.getChild(0);
        Label woodText = (Label) wood.getChild(0);
        woodText.setText(Material.WOOD.getName() + ": "+stock);

        int stock1 = this.gameManager.getPlayerInventory().getInventory().getStock(Material.STONE);
        Table stone = (Table) this.resourcesPanel.getChild(1);
        Label stoneText = (Label) stone.getChild(0);
        stoneText.setText(Material.STONE.getName() + ": "+stock1);

        int stock2 = this.gameManager.getPlayerInventory().getInventory().getStock(Material.COAL);
        Table coal = (Table) this.resourcesPanel.getChild(2);
        Label coalText = (Label) coal.getChild(0);
        coalText.setText(Material.COAL.getName() + ": "+stock2);

        int stock3 = this.gameManager.getPlayerInventory().getInventory().getStock(Material.IRON);
        Table iron = (Table) this.resourcesPanel.getChild(3);
        Label ironText = (Label) iron.getChild(0);
        ironText.setText(Material.IRON.getName() + ": "+stock3);

        int stock4 = this.gameManager.getPlayerInventory().getInventory().getStock(Material.OIL);
        Table oil = (Table) this.resourcesPanel.getChild(4);
        Label oilText = (Label) oil.getChild(0);
        oilText.setText(Material.OIL.getName() + ": "+stock4);

      /*  int stock5 = this.gameManager.getPlayerInventory().getInventory().getStock(Material.GAS);
        Table gas = (Table) this.resourcesPanel.getChild(5);
        Label gasText = (Label) gas.getChild(0);
        gasText.setText(Material.GAS.getName() + ": "+stock5);

        int stock6 = this.gameManager.getPlayerInventory().getInventory().getStock(Material.URANIUM);
        Table uranium = (Table) this.resourcesPanel.getChild(6);
        Label uraniumText = (Label) uranium.getChild(0);
        uraniumText.setText(Material.URANIUM.getName() + ": "+stock6);*/

        int stock7 = this.gameManager.getPlayerInventory().getInventory().getStock(Material.JUDOKOIN);
        Table judokoin = (Table) this.resourcesPanel.getChild(7);
        Label judokoinText = (Label) judokoin.getChild(0);
        judokoinText.setText(Material.JUDOKOIN.getName() + ": "+stock7);

        int stock8 = this.gameManager.getPopulationManager().getNewPopulation().size() +this.gameManager.getPopulationManager().getEmployedPopulation().size() ;
        Table totalpopulation = (Table) this.resourcesPanel.getChild(6);
        Label totalpopulationText = (Label) totalpopulation.getChild(0);
        totalpopulationText.setText("Total population " + ": "+stock8);

        int stock9 = this.gameManager.getPopulationManager().getNewPopulation().size() ;
        Table unnemployedPopulation = (Table) this.resourcesPanel.getChild(5);
        Label unnemployedPopulationText = (Label) unnemployedPopulation.getChild(0);
        unnemployedPopulationText.setText("Unemployed population " + ": "+stock9);

    }
}
