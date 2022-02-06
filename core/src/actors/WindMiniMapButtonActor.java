package actors;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.utbm.map.GroundMap;
import state.GameStateManager;

public class WindMiniMapButtonActor extends Image {

    public WindMiniMapButtonActor(final AssetManager p_assetManager, final GameStateManager p_gameStateManager, final GroundMap p_map){
        super((Texture) p_assetManager.get("button/WindButton.png"));
        setScale(1);
        setTouchable(Touchable.enabled);

        addListener(new ClickListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                MiniMapActor miniMapActor = new MiniMapActor(p_assetManager, p_map);
                miniMapActor.createWindExpositionMap();

                p_gameStateManager.openMiniMap(miniMapActor);
                return true;
            }
        });
    }
}
