package com.utbm.map;

import com.utbm.Material;
import com.utbm.map.plot.GrassPlot;
import com.utbm.map.plot.GrassPlotDeposit;
import com.utbm.map.plot.Plot;
import com.utbm.map.plot.WaterPlot;
import com.utbm.map.plot.materialDeposit.MaterialDeposit;
import micrell.utils.perlinNoise.PerlinNoise;
import micrell.utils.vector.Vector2;

import java.util.Random;

import static micrell.utils.math.Math.seriesInclude;

/**
 * Class qui permet de générer une map.
 */
public class MapGenerator {

    /**
     * Objet qui permet de générer un bruit de perlin pour générer la map aléatoirement.
     */
    private PerlinNoise perlinNoise;

    /**
     * Construteur par défault du MapGenerator.
     */
    public MapGenerator(){
    }

    /**
     * Méthode qui permet de créer un objet de type GroundMap et de générer la map
     *
     * @param p_worldSize : taille de la map
     * @param p_plotRadius : radius d'un plot
     * @param p_seed : graine de la map pour la génération
     * @return map : map générée par la méthode
     */
    public GroundMap generateMap(Vector2 p_worldSize, float p_plotRadius, int p_seed){

        GroundMap map = new GroundMap(p_worldSize, p_plotRadius);
        map = fillGridWithPlot(map);
        map = generateLand(map, p_seed);
        map = generateUnderground(map, p_seed);

        return map;
    }

    /**
     * Cette méthode remplie la groundmap, en paramètre, d'herbe et d'eau en fonction d'une bruit de perlin généré
     *
     * @param p_map : la map à remplir
     * @param p_seed : la seed de la map
     * @return : la map remplie d'herbe et d'eau
     */
    private GroundMap generateLand(GroundMap p_map, int p_seed){
        this.perlinNoise = new PerlinNoise(p_seed);

        for(int x = 0 ; x < p_map.getGridSizeX() ; x++){
            for(int y = 0 ; y < p_map.getGridSizeY() ; y++){
                p_map.getNodeInGrid(x, y).setNoiseValue(this.perlinNoise.noise((float) x/100, (float) y/100, 0));
                p_map.replacePlotToAnother(p_map.getNodeInGrid(x, y), getPlotAccordingToNoise(p_map.getNodeInGrid(x, y)));
            }
        }
        return p_map;
    }

    /**
     * Cette méthode ajoute les minerais à la map en remplaçant de l'herbe par un minerais.
     * Un nouveau bruit de Perlin est généré afin d'obtenir une génération plus sympathique
     *
     * @param p_map : la map à laquelle on veut ajouter des minerais
     * @param p_seed : la seed de la map
     * @return : la map avec les minerais ajouté
     */
    private GroundMap generateUnderground(GroundMap p_map, int p_seed){
        int newSeed = new Random(p_seed).nextInt();
        this.perlinNoise = new PerlinNoise(newSeed);
        for(int x = 0 ; x < p_map.getGridSizeX() ; x++){
            for(int y = 0 ; y < p_map.getGridSizeY() ; y++){
                p_map.getNodeInGrid(x, y).setNoiseValue(this.perlinNoise.noise((float) x/100, (float) y/100, 0));
                if(checkGrassPlot(p_map.getNodeInGrid(x, y))){
                    p_map.replacePlotToAnother(p_map.getNodeInGrid(x, y), getGrassPlotDepositAccordingToNoise(p_map.getNodeInGrid(x, y)));
                }
            }
        }
        return p_map;
    }

    /**
     * Vérifie si le plot pris en paramètre est un GrassPlot.
     *
     * @param p_plot : Plot qu'on veut vérifier.
     * @return true si c'est un GrassPlot, false sinon.
     */
    private boolean checkGrassPlot(Plot p_plot) {
        return p_plot instanceof GrassPlot;
    }

    /**
     * Méthode qui permet de définir si le plot pris en paramètre sera de l'herbe ou de l'eau en fonction du bruit de Perlin.
     *
     * @param p_Plot : Le plot qu'on veut changer soit en herbe, soit en eau
     * @return : Le plot modifié
     */
    private Plot getPlotAccordingToNoise(Plot p_Plot){
        Plot newPlot;

        if(seriesInclude(0f, 3f, p_Plot.getNoiseValue())){
            newPlot = new WaterPlot(p_Plot);
        }else{
            newPlot = new GrassPlot(p_Plot);
        }
        return newPlot;
    }

    /**
     * Méthode qui permet de définir le type de minérai que contiendra le plot pris en paramètre.
     * Le plot pris en paramètre sera alors modifié en GrassPlotDeposit et contiendra le minerai défini.
     *
     * @param p_Plot : Le plot à modifier en GrassPlotDeposit.
     * @return : Le plot modifié.
     */
    private Plot getGrassPlotDepositAccordingToNoise(Plot p_Plot){
        Plot newPlot;
        int random = new Random().nextInt(5);
        if(random == 0){
            if(seriesInclude(8f, 10f, p_Plot.getNoiseValue())) {
                newPlot = new GrassPlotDeposit(p_Plot, new MaterialDeposit(Material.STONE, 666));
            }else if(seriesInclude(-2f, 0f, p_Plot.getNoiseValue())){
                newPlot = new GrassPlotDeposit(p_Plot, new MaterialDeposit(Material.COAL, 666));
            }else if(seriesInclude(-4f, -2f, p_Plot.getNoiseValue())){
                newPlot = new GrassPlotDeposit(p_Plot, new MaterialDeposit(Material.IRON, 666));
            }else if(seriesInclude(-6f, -4f, p_Plot.getNoiseValue())){
                newPlot = new GrassPlotDeposit(p_Plot, new MaterialDeposit(Material.OIL, 666));
            }else if(seriesInclude(-8f, -6f, p_Plot.getNoiseValue())){
                newPlot = new GrassPlotDeposit(p_Plot, new MaterialDeposit(Material.GAS, 666));
            }else if(seriesInclude(-10f, -8f, p_Plot.getNoiseValue())){
                newPlot = new GrassPlotDeposit(p_Plot, new MaterialDeposit(Material.URANIUM, 666));
            }else{
                newPlot = new GrassPlotDeposit(p_Plot, new MaterialDeposit(Material.WOOD, 666));
            }
        }else{
            newPlot = p_Plot;
        }
        return newPlot;
    }

    /**
     * Rempli la grille de la map en paramètre avec des instances de la classe Plot
     *
     * @param p_map : La map qu'on veut remplir
     * @return : La map rempli de Plot
     */
    private GroundMap fillGridWithPlot(GroundMap p_map){
        for(int x = 0 ; x < p_map.getGridSizeX() ; x++){
            for(int y = 0 ; y < p_map.getGridSizeY() ; y++){
                p_map.setNodeInGrid(x, y, new GrassPlot(true, new Vector2(x*4, y*4), x, y));
            }
        }
        p_map.setNodeNeighbours();
        return p_map;
    }
}
