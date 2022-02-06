package actors;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;


public interface SpriteChangable {

    public void setSprite(AssetManager assetManager);

    public void setDrawable(Drawable paramDrawable);

}
