package actors.plot;

import actors.SpriteChangable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.utbm.map.plot.GrassPlot;
import com.utbm.map.plot.GrassPlotDeposit;
import com.utbm.map.plot.Plot;
import state.GameStateManager;

public class GrassPlotDepositActor extends Image implements SpriteChangable {

    public GrassPlotDeposit getGrassPlotDeposit() {
        return grassPlotDeposit;
    }

    GrassPlotDeposit grassPlotDeposit;

    public GrassPlotDepositActor(final GrassPlotDeposit p_grassPlot, AssetManager assetManager, final GameStateManager gameStateManager){
        this.grassPlotDeposit = p_grassPlot;
        setTouchable(Touchable.enabled);
        final SpriteChangable tempForEvent = this;
        this.setSprite(assetManager);


        addListener(new InputListener() {

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
            //    gameStateManager.flightOverBuildableAreaEnter(tempForEvent);
            }


            @Override
            public void exit (InputEvent event, float x, float y, int pointer, Actor fromActor){
              //  gameStateManager.flightOverBuildableAreaExit(tempForEvent);
            }


            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                gameStateManager.clickOnPlot(p_grassPlot);
                return true;
            }
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("up");
            }

        });


    }

    @Override
    public void setDrawable(Drawable drawable) {
        super.setDrawable(drawable);
    }

    public void setSprite(AssetManager assetManager){

        switch (this.grassPlotDeposit.getMaterialDeposit().getMaterial()){
            case WOOD:
                this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("plot/grassPlotDeposit_Forest.png"))));
                break;
            case COAL:
                this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("plot/grassPlotDeposit_Coal.png"))));
                break;
            case IRON:
                this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("plot/grassPlotDeposit_Iron.png"))));
                break;
            case STONE:
                this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("plot/grassPlotDeposit_Stone.png"))));
                break;
            case URANIUM:
                this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("plot/grassPlotDeposit_Uranium.png"))));
                break;
            case GAS:
                this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("plot/grassPlotDeposit_Gas.png"))));
                break;
            case OIL:
                this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("plot/grassPlotDeposit_Oil.png"))));
                break;
            default:
                this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("plot/plot_GrassPlot.png"))));
        }

        this.setBounds(this.grassPlotDeposit.getWorldPosition().getX(),this.grassPlotDeposit.getWorldPosition().getY(),4,4);
    }


}