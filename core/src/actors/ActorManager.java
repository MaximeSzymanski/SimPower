package actors;

import actors.plot.GrassPlotActor;
import actors.plot.GrassPlotDepositActor;
import actors.plot.WaterPlotActor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.utbm.map.plot.GrassPlot;
import com.utbm.map.plot.GrassPlotDeposit;
import com.utbm.map.plot.Plot;
import com.utbm.map.plot.WaterPlot;
import state.GameStateManager;

import java.util.ArrayList;
import java.util.Stack;

public class ActorManager {

    AssetManager assetManager;

    public ActorManager(AssetManager p_assetManager){
        this.assetManager = p_assetManager;
    }

    public Stack<Actor> SetupPlotActors(ArrayList<ArrayList<Plot>> p_plotStack, GameStateManager gameStateManager){
        Stack<Actor> plotActorStack = new Stack<>();

        for(ArrayList<Plot> list : p_plotStack) {
            for (Plot actualPlot : list) {
                if (actualPlot instanceof GrassPlot) {
                    GrassPlotActor currentActor = new GrassPlotActor((GrassPlot) actualPlot, this.assetManager,gameStateManager);

                    plotActorStack.push(currentActor);
                } else if (actualPlot instanceof GrassPlotDeposit) {
                    GrassPlotDepositActor currentActor = new GrassPlotDepositActor((GrassPlotDeposit) actualPlot, this.assetManager,gameStateManager);
                    plotActorStack.push(currentActor);

                } else if (actualPlot instanceof WaterPlot) {
                    WaterPlotActor currentActor = new WaterPlotActor((WaterPlot) actualPlot,this.assetManager,gameStateManager);

                    plotActorStack.push(currentActor);
                }
            }
        }
        return plotActorStack;
    }
}
