package controller;

import view.BuildingActor;
import view.ChoosableBuildingActor;
import view.MiniMapActor;
import view.SpriteChangable;
import view.plot.GrassPlotActor;
import com.badlogic.gdx.assets.AssetManager;
import model.build.BuildingManager;
import model.map.plot.Plot;

/**
 * Implémentation d'un state design pattern pour gerer les evenements.Chaque etat du jeu est un état, et chaque action de l'utilisateur est une transition entre état. Cela permet de restreindre les actions de l'utilisateur en fonction de l'etat du jeu.
 */
public interface GameState {

     /**
      * Aucune action.
      */
     void nothing();

     /**
      * Clique sur le bouton buldozer.
      */
     void clickOnBuldozerButton();

     /**
      * Clique sur un batiment dans la building library, qui est constructible en terme de matériaux.
      * @param p_build Batiment sur lequel le joueur a cliqué.
      */
     void clickOnChoosableBuildingActor(ChoosableBuildingActor p_build);

     /**
      * Clique sur un batiment dans la building library, mais qui n'est pas constructible en terme de matériaux.
      */
     void clickOnUnaffordableBuild();

     /**
      * Clique sur une zone constructible.
      * @param p_area Zone sur laquel le joueur a cliqué.
      */
     void clickOnBuildableArea(GrassPlotActor p_area);

     /**
      * Clique sur une zone non-constructible.
      */
     void clickOnUnbuildableArea();

     /**
      * Clique sur le bouton de la building library.
      * @param p_assetManager Gestionnaire de sprite.
      */
     void ClickOnLibraryButton(AssetManager p_assetManager);

     /**
      * Clique sur bouton pour fermer la building library.
      */
     void closeLibrary();

     /**
      * Survole une zone constructible (entré)
      * @param p_area Zone sur laquel la souris est.
      */
     void flightOverBuildableAreaEnter(SpriteChangable p_area);

     /**
      * Survole une zone constructible (sortie)
      * @param p_area Zone sur laquel la souris est.
      */
     void flightOverBuildableAreaExit(SpriteChangable p_area);

     /**
      * Survole une zone non constructible
      * @param p_area Zone sur laquel la souris est.
      */
     void flightOverNonBuildableArea(SpriteChangable p_area);

     /**
      * Clique sur une case contenant des ressources.
      * @param p_plot Case sur laquel le joueur a cliqué.
      * @param assetManager Gestionnaire de sprite.
      */
     void clickOnGrassPlotDeposit(Plot p_plot, AssetManager assetManager);

     /**
      * Clique sur l'icone de la mini-map.
      * @param p_miniMap Acteur de la mini-map.
      */
     void openMiniMap(MiniMapActor p_miniMap);

     /**
      * Clique sur un building sur la carte.
      * @param buildingclicked Batiment sur lequel le joueur a cliqué.
      */
     void clickBuildingOnMap(BuildingActor buildingclicked);

     void destroyBuilding(BuildingManager buildingManager);

     void closeInformationPannel();

    void clickOnBuldozer();
}
