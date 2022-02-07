package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import model.build.buildings.*;
import controller.GameStateManager;


import java.util.ArrayList;

public class BuildingLibraryActor extends Window {

    protected ArrayList<Actor> buildingList;
    protected Table inventory;


    public BuildingLibraryActor(final AssetManager assetManager, GameStateManager gameStateManager){
        super("", new Skin(Gdx.files.internal("skin/default/skin/uiskin.json")));
        this.setBounds(90 ,90,140,140);
        this.buildingList = new ArrayList<>();
        //this.setDebug(true);
        createBuildingsButtons(assetManager,gameStateManager);
        this.setBackground(new TextureRegionDrawable(new Texture("buildingLibrary/Library.png")));

        inventory = new Table();
        inventory.setDebug(false);
        inventory.defaults().pad((float) 0.7);
        fillBuildingsTable();

        this.add(inventory).expand().top().left().padTop(this.getHeight()/2).padLeft(6).padRight(6).padBottom(25);






    }

    private void fillBuildingsTable() {
        int counter = 0;
        for(Actor current : buildingList){
            this.inventory.add(current).size(13,13);
            counter++;
            if(counter == 9){
                this.inventory.row();
            }
        }
    }

    private void createBuildingsButtons(AssetManager assetManager, GameStateManager gameStateManager) {
        ChoosableBuildingActor coalMine = new ChoosableBuildingActor(new CoalMine(), assetManager,gameStateManager);
        this.buildingList.add(coalMine);
        ChoosableBuildingActor factory = new ChoosableBuildingActor(new Factory(10), assetManager,gameStateManager);
        this.buildingList.add(factory);
        ChoosableBuildingActor gasMine = new ChoosableBuildingActor(new GasMine(), assetManager,gameStateManager);
        this.buildingList.add(gasMine);
        ChoosableBuildingActor house = new ChoosableBuildingActor( new House(), assetManager,gameStateManager);
        this.buildingList.add(house);
        ChoosableBuildingActor ironMine = new ChoosableBuildingActor( new IronMine(), assetManager,gameStateManager);
        this.buildingList.add(ironMine);
        ChoosableBuildingActor nuclearPlant = new ChoosableBuildingActor( new NuclearPlant(), assetManager,gameStateManager);
        this.buildingList.add(nuclearPlant);
        ChoosableBuildingActor oilMine = new ChoosableBuildingActor( new OilMine(), assetManager,gameStateManager);
        this.buildingList.add(oilMine);
        ChoosableBuildingActor pylon = new ChoosableBuildingActor( new Pylon(), assetManager,gameStateManager);
        this.buildingList.add(pylon);
        ChoosableBuildingActor sawMill = new ChoosableBuildingActor( new SawMill(), assetManager,gameStateManager);
        this.buildingList.add(sawMill);
        ChoosableBuildingActor stoneMine = new ChoosableBuildingActor( new StoneMine(), assetManager,gameStateManager);
        this.buildingList.add(stoneMine);
        ChoosableBuildingActor uraniumMine = new ChoosableBuildingActor( new UraniumMine(), assetManager,gameStateManager);
        this.buildingList.add(uraniumMine);
        ChoosableBuildingActor waterPump = new ChoosableBuildingActor( new WaterPump(), assetManager,gameStateManager);
        this.buildingList.add(waterPump);
    }

 /*   @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch,parentAlpha);
    }*/

}
