package model.map;

import model.map.plot.Plot;
import model.utils.perlinNoise.PerlinNoise;
import model.utils.math.Math;

import java.util.Random;

/**
 * Cette classe permet de générer un envrionnement sur la map.
 */
public class EnvironmentManager { //TODO Faire changer le temps au cours de la partie

    /**
     * Référence sur la map du jeu.
     */
    GroundMap groundMap;
    /**
     * Bruit de Perlin qui permet de générer un ensoleillement plus réaliste.
     */
    PerlinNoise sunPerlinNoise;
    /**
     * Bruit de Perlin qui permet de générer du vent de manière plus réaliste.
     */
    PerlinNoise windPerlinNoise;

    /**
     * Constructeur paramétré
     *
     * @param p_groundMap : La map du jeu
     */
    public EnvironmentManager(GroundMap p_groundMap){
        this.groundMap = p_groundMap;
    }

    /**
     * Cette méthode change l'expositiion au vent de chaque plot de la map
     */
    public void changeEnvironmentExposition(){
        setNewRandomPerlinNoise();

        for(int x = 0 ; x < this.groundMap.getGridSizeX() ; x++){
            for(int y = 0 ; y < this.groundMap.getGridSizeY() ; y++){
                changeSunExposition(this.groundMap.getNodeInGrid(x, y));
                changeWindExposition(this.groundMap.getNodeInGrid(x, y));
            }
        }
    }

    /**
     * Cette méthode créée des nouveaux bruits de Perlin aléatoire pour l'exposition au vent et au soleil.
     */
    private void setNewRandomPerlinNoise() {
        int randomSunExpositionSeed = new Random().nextInt();
        int randomWindExpositionSeed = new Random().nextInt();
        this.sunPerlinNoise = new PerlinNoise(randomSunExpositionSeed);
        this.windPerlinNoise = new PerlinNoise(randomWindExpositionSeed);
    }

    /**
     * Cette méthode change l'exposition au soleil pour un plot donné en paramètre.
     *
     * @param p_plot : Plot à modifier.
     */
    private void changeSunExposition(Plot p_plot){
        p_plot.setSunExposition(getSunExpositionValue(p_plot.getGridX() ,p_plot.getGridY()));
    }

    /**
     * Cette méthode change l'expositino au vent pour un plot donné en paramètre.
     *
     * @param p_plot
     */
    private void changeWindExposition(Plot p_plot){
        p_plot.setWindExposition(getWindExpositionValue(p_plot.getGridX() ,p_plot.getGridY()));
    }

    /**
     * Cette méthode récupère un bruit de Perlin pour l'ensoleillement entre 0 et 100 (pour simuler un pourcentage).
     *
     * @param p_x : la coordonné x
     * @param p_y : la coordonné y
     * @return un bruit entre 0 et 100
     */
    private int getSunExpositionValue(int p_x, int p_y){
        float noiseValue = 0;
        noiseValue = this.sunPerlinNoise.noise((float) p_x/100, (float) p_y/100, 0);
        return setNoiseAsRate(noiseValue);
    }

    /**
     * Cette méthode récupère un bruit de Perlin pour le vent entre 0 et 100 (pour simuler un pourcentage).
     *
     * @param p_x : la coordonné x.
     * @param p_y : la coordonné y.
     * @return un bruit entre 0 et 100.
     */
    private int getWindExpositionValue(int p_x, int p_y){
        float noiseValue = 0;
        noiseValue = this.windPerlinNoise.noise((float) p_x/100, (float) p_y/100, 0);
        return setNoiseAsRate(noiseValue);
    }

    /**
     * Cette méthode prend un bruit en paramètre et le force à appartenir à l'ensemble [0;100].
     *
     * @param p_noiseValue : le bruit à modifier.
     * @return : le bruit modifié.
     */
    private int setNoiseAsRate(float p_noiseValue) {
        p_noiseValue = Math.clamp(0, 100, p_noiseValue);
        return (int) p_noiseValue;
    }
}
