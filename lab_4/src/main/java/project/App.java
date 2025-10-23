package project;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Application principale pour la visualisation des algorithmes de tri. Implémente le patron
 * Template Method pour les algorithmes Quick Sort et Merge Sort. Permet la visualisation animée des
 * étapes intermédiaires du tri.
 */
public class App extends Application {
    private GestionnaireTri gestionnaire;
    private ControleurParametres controleurParametres;
    private ControleurVisualisation controleurVisualisation;

    /**
     * Point d'entrée de l'application JavaFX.
     * 
     * @param primaryStage Le stage principal
     */
    @Override
    public void start(Stage primaryStage) {
        initialiserControleurs(primaryStage);
        controleurParametres.afficherVue();
    }

    /**
     * Initialise les contrôleurs et leurs dépendances.
     * 
     * @param primaryStage Le stage principal
     */
    private void initialiserControleurs(Stage primaryStage) {
        // Créer le gestionnaire de tri
        gestionnaire = new GestionnaireTri();

        // Créer le contrôleur de visualisation
        Stage stageVisualisation = new Stage();
        controleurVisualisation = new ControleurVisualisation(gestionnaire, stageVisualisation);
        controleurVisualisation.initialiser();

        // Créer le contrôleur des paramètres
        controleurParametres = new ControleurParametres(gestionnaire);
        VueParametres vueParametres = new VueParametres(primaryStage, controleurParametres);
        controleurParametres.setVue(vueParametres);
        controleurParametres.setControleurVisualisation(controleurVisualisation);
    }

    /**
     * Point d'entrée principal de l'application.
     * 
     * @param args Arguments de la ligne de commande
     */
    public static void main(String[] args) {
        launch(args);
    }
}
