package view.plot;

import view.SpriteChangable;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import model.map.plot.WaterPlot;
import controller.GameStateManager;

public class WaterPlotActor extends Image implements SpriteChangable {

    WaterPlot waterPlot;


    public WaterPlotActor(WaterPlot p_waterPlot, AssetManager assetManager, final GameStateManager gameStateManager){
        super();
        this.waterPlot = p_waterPlot;
        setTouchable(Touchable.enabled);
        this.setBounds(this.waterPlot.getWorldPosition().getX(),this.waterPlot.getWorldPosition().getY(),4,4);

        this.setSprite(assetManager);
        final SpriteChangable tempForEvent = this;

        this.setPosition(this.waterPlot.getWorldPosition().getX(),this.waterPlot.getWorldPosition().getY() );

        addListener(new InputListener() {

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                gameStateManager.flightOverBuildableAreaEnter(tempForEvent);
            }


            @Override
            public void exit (InputEvent event, float x, float y, int pointer, Actor fromActor){
                gameStateManager.flightOverBuildableAreaExit(tempForEvent);
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                String actorName = "WaterPlot";
                System.out.println("Touch down asset with name "+ actorName);
                return true;
            }
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("up");
            }

        });

    }

    public void setDrawable(Drawable paramDrawable){
        super.setDrawable(paramDrawable);
    }

    public void setSprite(AssetManager assetManager){
        this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("plot/plot_WaterPlot.png"))));
    }

}
