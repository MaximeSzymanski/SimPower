package actors;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import state.GameStateManager;

public class BuldozerButton extends Image {


    public BuldozerButton(AssetManager p_assetManager, final GameStateManager p_gameStateManager){
        super((Texture) p_assetManager.get("button/BuldozerButton.png"));
        setScale(1);
        setTouchable(Touchable.enabled);

        addListener(new ClickListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                p_gameStateManager.clickOnBuldozerButton();
                return true;
            }
        });
    }
}
