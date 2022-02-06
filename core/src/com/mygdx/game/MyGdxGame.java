package com.mygdx.game;

import actors.ActorManager;
import actors.BuildingLibraryButton;
import actors.LeftSidePanel;
import actors.plot.GrassPlotActor;
import actors.plot.GrassPlotDepositActor;
import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.utbm.GameManager;
import com.utbm.map.plot.GrassPlot;
import com.utbm.map.plot.GrassPlotDeposit;
import com.utbm.map.plot.Plot;
import micrell.utils.vector.Vector2;
import state.GameStateManager;

import java.util.ArrayList;
import java.util.Stack;



public class MyGdxGame extends ApplicationAdapter {
	private GameManager gameManager;
	public Vector3 posCameraDesired = new Vector3();
	private Stage stage, stage2;
	private final AssetManager assetManager = new AssetManager();
	private GameStateManager gameStateManager;
	private LeftSidePanel leftSidePanel;


	public void resize(int width, int height){

		stage.getViewport().update(width,height);
		stage.getCamera().position.set(stage.getCamera().viewportWidth/2,stage.getCamera().viewportHeight/2,0);
	}
	@Override
	public void create () {
		loadAssets();
		gameManager = new GameManager();

		stage = new Stage(new FillViewport(400,400, new OrthographicCamera()));
		stage2 = new Stage(new FillViewport(stage.getCamera().viewportWidth,stage.getCamera().viewportHeight),this.stage.getBatch());

		this.gameStateManager = new GameStateManager(this.assetManager, this.stage, gameManager.getBuildingManager(), gameManager.getPlayerInventory(),stage2);

		BuildingLibraryButton button = new BuildingLibraryButton(this.assetManager, this.gameStateManager);

		button.setBounds(stage.getCamera().position.x - stage.getCamera().viewportWidth/2 + 5 ,stage.getCamera().position.y- stage.getCamera().viewportHeight/2 + 100,40,40);


		stage2.addActor(button);
	    stage.getCamera().position.set( 400/ 2, 400/2, 0);

		this.leftSidePanel = new LeftSidePanel(this.assetManager, this.gameStateManager, this.gameManager);
		stage2.addActor(this.leftSidePanel);

		ActorManager actorManager = new ActorManager(assetManager);
		Stack<Actor> actors  =  actorManager.SetupPlotActors(gameManager.getMapManager().getGroundMap().getPlotList(),gameStateManager);
		for(Actor current : actors  ){
			stage.addActor(current);
		}

		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage2);
		multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		this.leftSidePanel.render();

		//((OrthographicCamera)stage.getCamera()).zoom -= 0.02f;
		this.gameManager.getPopulationManager().addNewPopulation();
//		System.out.println("taille reaseu : " + this.gameManager.getElectricityNetwork().getAdjVertices().size());
		game();
	//	removeEmptyGrassPlotDeposit();
		this.gameManager.getStopwatch().tick();
		this.gameManager.getPopulationManager().computeAverageSatisfaction();
		stage.act(Gdx.graphics.getDeltaTime());
		stage2.act(Gdx.graphics.getDeltaTime());
		stage.getCamera().update();
		stage.getBatch().setProjectionMatrix(stage.getCamera().combined);
		stage.getViewport().apply();
		stage.draw();
		stage2.act();
		stage2.getViewport().apply();
		stage2.draw();




	}

	public void game() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) { // or A

			//if(stage.getCamera().position.x - stage.getCamera().viewportWidth/2 > 100){
				stage.getCamera().position.x -= 1; // if conditions are ok, move the camera to the front.
			//}
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {


			//if(stage.getCamera().position.x + stage.getCamera().viewportWidth/2 <= 400){
				stage.getCamera().position.x += 1; // if conditions are ok, move the camera to the front.
			//}



		}else if(Gdx.input.isKeyPressed(Input.Keys.UP)){

			//if(stage.getCamera().position.y + stage.getCamera().viewportHeight/4 <= 400){
				stage.getCamera().position.y += 1; // if conditions are ok, move the camera to the front.
			//}
		}else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {


			//if(stage.getCamera().position.y - stage.getCamera().viewportHeight/4 > 0) {
				stage.getCamera().position.y -= 1; // if conditions are ok, move the camera to the front.
			//}
		} else if (Gdx.input.isKeyPressed(Input.Keys.Z) && ((OrthographicCamera)stage.getCamera()).zoom < 1.9) {


			((OrthographicCamera)stage.getCamera()).zoom += 0.02;

		} else if (Gdx.input.isKeyPressed(Input.Keys.D)&& ((OrthographicCamera)stage.getCamera()).zoom > 0.02) {


			((OrthographicCamera)stage.getCamera()).zoom -= 0.02;

		}

	}
	@Override
	public void dispose () {
		this.stage.dispose();
		this.stage2.dispose();
		this.assetManager.dispose();
	}

	private void removeEmptyGrassPlotDeposit(){
		for(Actor currentActor : stage.getActors()){

				if(currentActor instanceof GrassPlotDepositActor){
					if(((GrassPlotDepositActor) currentActor).getGrassPlotDeposit().isMaterialDepositEmpty() ){
						GrassPlotActor newActor = new GrassPlotActor(new GrassPlot(true, ((GrassPlotDepositActor) currentActor).getGrassPlotDeposit().getWorldPosition(), ((GrassPlotDepositActor) currentActor).getGrassPlotDeposit().getGridX(),((GrassPlotDepositActor) currentActor).getGrassPlotDeposit().getGridY()),this.assetManager,this.gameStateManager);
						this.stage.addActor(newActor);
						this.gameManager.getMapManager().getGroundMap().getPlotList().get(newActor.getGrassplot().getGridX()).add(newActor.getGrassplot());
						this.gameManager.getMapManager().getGroundMap().getPlotList().get(newActor.getGrassplot().getGridX()).remove(currentActor);
						for(Plot currentNeighboor : ((GrassPlotDepositActor) currentActor).getGrassPlotDeposit().getNeighbours()){
							currentNeighboor.setNeighbours(this.gameManager.getMapManager().getGroundMap().searchNeighbours(currentNeighboor));
						}
						currentActor.remove();
					}
				}
			}
		}

	private void processCameraMovement(){
		/// make some camera movement
		posCameraDesired.x+=100.0f * Gdx.graphics.getDeltaTime();
		posCameraDesired.y+=100.0f * Gdx.graphics.getDeltaTime();
	}

	private void loadAssets(){
		assetManager.load("material/White.png", Texture.class);
		assetManager.load("material/Material_Wood.png", Texture.class);
		assetManager.load("material/Material_Stone.png", Texture.class);
		assetManager.load("material/Material_Oil.png", Texture.class);
		assetManager.load("material/Material_Gas.png", Texture.class);
		assetManager.load("material/Material_Coal.png", Texture.class);
		assetManager.load("material/Material_Iron.png", Texture.class);
		assetManager.load("material/Material_Uranium.png", Texture.class);
		assetManager.load("material/Material_Judokoin.png", Texture.class);
		assetManager.load("button/closeButton.png", Texture.class);
		assetManager.load("button/SunButton.png", Texture.class);
		assetManager.load("button/WindButton.png", Texture.class);
		assetManager.load("button/PollutionButton.png", Texture.class);
		assetManager.load("button/BuildingLibraryButton.png", Texture.class);
		assetManager.load("button/BuldozerButton.png", Texture.class);

		assetManager.load("plot/plot_WaterPlot.png", Texture.class);
		assetManager.load("plot/plot_GrassPlot.png", Texture.class);
		assetManager.load("plot/grassPlotDeposit_Forest.png", Texture.class);
		assetManager.load("plot/grassPlotDeposit_Coal.png", Texture.class);
		assetManager.load("plot/grassPlotDeposit_Iron.png", Texture.class);
		assetManager.load("plot/grassPlotDeposit_Stone.png", Texture.class);
		assetManager.load("plot/grassPlotDeposit_Uranium.png", Texture.class);
		assetManager.load("plot/grassPlotDeposit_Gas.png", Texture.class);
		assetManager.load("plot/grassPlotDeposit_Oil.png", Texture.class);

		assetManager.load("building/NuclearPlant.png", Texture.class);
		assetManager.load("buildingLibrary/CoalMine.png", Texture.class);
		assetManager.load("buildingLibrary/Factory.png", Texture.class);
		assetManager.load("buildingLibrary/GasMine.png", Texture.class);
		assetManager.load("buildingLibrary/House.png", Texture.class);
		assetManager.load("buildingLibrary/IronMine.png", Texture.class);
		assetManager.load("buildingLibrary/Library.png", Texture.class);
		assetManager.load("buildingLibrary/NuclearPlant.png", Texture.class);
		assetManager.load("buildingLibrary/OilMine.png", Texture.class);
		assetManager.load("buildingLibrary/Pylon.png", Texture.class);
		assetManager.load("buildingLibrary/SawMill.png", Texture.class);
		assetManager.load("buildingLibrary/StoneMine.png", Texture.class);
		assetManager.load("buildingLibrary/UraniumMine.png", Texture.class);
		assetManager.load("buildingLibrary/WaterPump.png", Texture.class);

		assetManager.finishLoading();
	}

}
