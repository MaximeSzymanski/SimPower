package actors.plot;

import actors.SpriteChangable;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.utbm.map.plot.GrassPlot;
import state.GameStateManager;

public class GrassPlotActor extends Image implements SpriteChangable {

    public GrassPlot getGrassplot() {
        return grassplot;
    }

    GrassPlot grassplot;



    public GrassPlotActor(final GrassPlot p_grassPlot, final AssetManager assetManager, final GameStateManager gameStateManager){
        this.grassplot = p_grassPlot;
        setTouchable(Touchable.enabled);
        this.setBounds(this.grassplot.getWorldPosition().getX(),this.grassplot.getWorldPosition().getY(),4,4);

        final GrassPlotActor tempForEvent = this;
        this.setSprite(assetManager);
        addListener(new ClickListener() {

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
                    if(p_grassPlot.isWalkable()){
                        gameStateManager.clickOnBuildableArea(tempForEvent);
                    }
                return false;
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
        this.setDrawable(new SpriteDrawable(new Sprite((Texture) assetManager.get("plot/plot_GrassPlot.png"))));
    }

}
