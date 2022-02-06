package actors.informationPanel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.utbm.map.plot.GrassPlotDeposit;

public class GrassPlotDepositPanelActor extends InformationPanelActor{

    public GrassPlotDepositPanelActor(GrassPlotDeposit p_plot) {
        super(p_plot);
        String name = p_plot.getMaterialDeposit().getMaterial().getName();
        this.getTitleLabel().setText(name + " Deposit");

        Label materialAmount = new Label( name + " = " + p_plot.getMaterialDeposit().getCurrentAmount(), new Skin(Gdx.files.internal("skin/default/skin/uiskin.json")));
        materialAmount.setFontScale(0.3f);

        this.row();
        this.add(materialAmount);
    }
}
