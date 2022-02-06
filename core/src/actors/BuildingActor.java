package actors;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.utbm.build.buildings.Building;
import state.GameStateManager;

public class BuildingActor extends Image {
    public Building getBuildingData() {
        return buildingData;
    }

    Building buildingData;



    public BuildingActor(final Building p_building, AssetManager assetManager, final GameStateManager gameStateManager) {
        super();
        this.buildingData = p_building;
        this.setSize(13,13);
        switch (p_building.name){
            case "CoalMine" :
                this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("buildingLibrary/CoalMine.png"))));
                break;
            case "Factory" :
                this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("buildingLibrary/Factory.png"))));
                break;
            case "GasMine" :
                this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("buildingLibrary/GasMine.png"))));
                break;
            case "House" :
                this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("buildingLibrary/House.png"))));
                break;
            case "IronMine" :
                this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("buildingLibrary/IronMine.png"))));
                break;
            case "NuclearPlant" :
                this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("buildingLibrary/NuclearPlant.png"))));
                break;
            case "OilMine" :
                this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("buildingLibrary/OilMine.png"))));
                break;
            case "Pylon" :
                this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("buildingLibrary/Pylon.png"))));
                break;
            case "SawMill" :
                this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("buildingLibrary/SawMill.png"))));
                break;
            case "StoneMine" :
                this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("buildingLibrary/StoneMine.png"))));
                break;
            case "UraniumMine" :
                this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("buildingLibrary/UraniumMine.png"))));
                break;
            case "WaterPump" :
                this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("buildingLibrary/WaterPump.png"))));
                break;
        }

        setTouchable(Touchable.enabled);

        final BuildingActor tempForEvent = this;
        this.addListener(new InputListener() {


            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gameStateManager.clickBuildingOnMap(tempForEvent);
                return true;
            }
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            }

        });
    }




    @Override
    public void act(float delta) {

        super.act(delta);
    }


}