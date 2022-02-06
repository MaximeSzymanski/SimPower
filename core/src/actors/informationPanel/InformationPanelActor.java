package actors.informationPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.utbm.Material;
import com.utbm.build.buildings.Building;
import com.utbm.build.buildings.House;
import com.utbm.map.plot.Plot;

public class InformationPanelActor extends Window{

    public InformationPanelActor(Plot p_plot) {

        super("Plot", new Skin(Gdx.files.internal("skin/default/skin/uiskin.json")));
        this.getSkin().getFont("default-font").getData().setScale(0.5f);
        setBoundsToBeAbleToSeeTheEntireWindow(p_plot);
        this.setMovable(false);
        this.top().left();

        Label sunExposition = new Label("Sun Exposition = " + p_plot.getSunExposition(), new Skin(Gdx.files.internal("skin/default/skin/uiskin.json")));
        sunExposition.setFontScale(0.3f);

        Label windExposition = new Label("Wind Exposition = " + p_plot.getWindExposition(), new Skin(Gdx.files.internal("skin/default/skin/uiskin.json")));
        windExposition.setFontScale(0.3f);

        Label pollutionRate = new Label("Pollution Rate = " + p_plot.getPollutionRate(), new Skin(Gdx.files.internal("skin/default/skin/uiskin.json")));
        pollutionRate.setFontScale(0.3f);

        this.add(windExposition);
        this.row();
        this.add(sunExposition);
        this.row();
        this.add(pollutionRate);

    }

    public InformationPanelActor(Building p_building) {

        super("Building", new Skin(Gdx.files.internal("skin/default/skin/uiskin.json")));
        this.getSkin().getFont("default-font").getData().setScale(0.5f);
        setBoundsToBeAbleToSeeTheEntireWindow(p_building.getArea().getPlotList().get(0));
        this.setMovable(false);
        this.top().left();

        Label productionRate = new Label("Production rate = " + p_building.getProductionRate(), new Skin(Gdx.files.internal("skin/default/skin/uiskin.json")));
        productionRate.setFontScale(0.3f);

        Label productionTimer = new Label("Production time = " + p_building.getTimer().getCurrentCount()+ "/"+p_building.getTimer().getMaxCount() , new Skin(Gdx.files.internal("skin/default/skin/uiskin.json")));
        productionTimer.setFontScale(0.3f);

        Label materialExtracted = new Label("Material extracted = " + p_building.getMaterialProduced(), new Skin(Gdx.files.internal("skin/default/skin/uiskin.json")));
        materialExtracted.setFontScale(0.3f);

        Label alimented = new Label("Alimented : = " + p_building.isAlimented(), new Skin(Gdx.files.internal("skin/default/skin/uiskin.json")));
        alimented.setFontScale(0.3f);

        Label worker = new Label("Worker  = " + p_building.getPopulation().size() +"/"+ p_building.getMaxPopulation() , new Skin(Gdx.files.internal("skin/default/skin/uiskin.json")));
        worker.setFontScale(0.3f);

        Label satisfaction = new Label("Satisfaction  = " + p_building.computeAverageSatisfaction() +"/"+ "100", new Skin(Gdx.files.internal("skin/default/skin/uiskin.json")));
        satisfaction.setFontScale(0.3f);



        Label pollutingEnergy = new Label("pollutingEnergy  = " + p_building.getFullStock().getStock(Material.POLLUTING_ENERGY), new Skin(Gdx.files.internal("skin/default/skin/uiskin.json")));
        pollutingEnergy.setFontScale(0.3f);



        String name = p_building.name;
        this.getTitleLabel().setText(name + " Building");

        this.add(productionRate);
        this.row();
        this.add(productionTimer);
        this.row();
        this.add(materialExtracted);
        this.row();
        this.add(alimented);
        this.row();
        this.add(worker);
        this.row();
        this.add(satisfaction);
        this.row();
        if(p_building instanceof House){
            Label material = new Label("Type Of Energy  = " + ((House) p_building).getCitizenChoice(), new Skin(Gdx.files.internal("skin/default/skin/uiskin.json")));
            material.setFontScale(0.3f);
            this.add(material);
            this.row();
        }
        this.add(pollutingEnergy);
    }

    public void setBoundsToBeAbleToSeeTheEntireWindow(Plot p_plot){
        this.setHeight(80);
        this.setWidth(100);

        if(p_plot.getWorldPosition().getY()+this.getHeight() > 300){
            this.setY(p_plot.getWorldPosition().getY()-this.getHeight() - 50);
        }else{
            this.setY(p_plot.getWorldPosition().getY());
        }

        if(p_plot.getWorldPosition().getX()+this.getWidth() > 500){
            this.setX(p_plot.getWorldPosition().getX()-this.getWidth() - 50);
        }else{
            this.setX(p_plot.getWorldPosition().getX() - 50);
        }

    }


}
