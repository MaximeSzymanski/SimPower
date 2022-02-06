package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class GameObject {
    public float x = 0;
    public float y = 0;
    private Texture texture;


    public GameObject(String texture_path, float posX, float posY){
        texture = new Texture(texture_path);
        x = posX;
        y = posY;
    }


}
