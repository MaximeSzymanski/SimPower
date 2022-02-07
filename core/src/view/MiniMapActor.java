package view;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import model.map.GroundMap;
import model.map.plot.Plot;

public class MiniMapActor extends Table {

    private AssetManager assetManager;
    private GroundMap map;

    public MiniMapActor(AssetManager p_assAssetManager, GroundMap p_map){
        this.assetManager = p_assAssetManager;
        this.map = p_map;
        this.setName("Minimap");


        this.setBounds(50, 90, 120, 120);

    }

    public void createSunExpositionMap(){
        for(int y = map.getGridSizeX()-1 ; y >= 0  ; y--){
            for(int x = 0 ; x < map.getGridSizeY() ; x++){
                Plot p = map.getNodeInGrid(x, y);

                float percent = (float) p.getSunExposition()/100;
                Image img = createSprite();
                img.setColor(new Color(percent, percent, 0, 1));
                this.add(img);
            }
            this.row();
        }

        this.setName("Sun Exposition Minimap");
    }

    public void createWindExpositionMap(){
        for(int y = map.getGridSizeX()-1 ; y >= 0  ; y--){
            for(int x = 0 ; x < map.getGridSizeY() ; x++){
                Plot p = map.getNodeInGrid(x, y);

                float percent = (float) p.getWindExposition()/100;
                Image img = createSprite();
                img.setColor(new Color(percent, percent, percent, 1));

                this.add(img);
            }
            this.row();
        }

        this.setName("Wind Exposition Minimap");
    }

    public void createPollutionMap(){
        for(int y = map.getGridSizeX()-1 ; y >= 0  ; y--){
            for(int x = 0 ; x < map.getGridSizeY() ; x++){
                Plot p = map.getNodeInGrid(x, y);

                float percent = (float) p.getPollutionRate()/100;
                Image img = createSprite();
                img.setColor(new Color(percent, 1-percent, 0, 1));

                this.add(img);
            }
            this.row();
        }

        this.setName("Pollution Minimap");
    }

    private Image createSprite(){
        Image img = new Image((Texture) this.assetManager.get("material/White.png"));
        img.setScale(1f);
        return img;
    }
}
