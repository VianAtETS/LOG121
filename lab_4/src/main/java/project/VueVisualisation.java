package project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Vue pour la visualisation du tri. Affiche l'animation du tri et les contrôles de lecture.
 */
public class VueVisualisation {
    private Stage stage;
    private ControleurVisualisation controleur;
    private Canvas canvas;
    private Button btnJouer;
    private Button btnPause;
    private Button btnArreter;
    private Button btnReinitialiser;
    private Label lblEtape;

    /**
     * Constructeur de la vue de visualisation.
     * 
     * @param stage Le stage JavaFX
     * @param controleur Le contrôleur associé
     */
    public VueVisualisation(Stage stage, ControleurVisualisation controleur) {
        this.stage = stage;
        this.controleur = controleur;
        initialiserComposants();
    }

    /**
     * Affiche la fenêtre de visualisation.
     */
    public void afficher() {
        Scene scene = new Scene(creerLayout(), 900, 600);
        stage.setScene(scene);
        stage.setTitle("Visualisation du Tri");
        stage.show();
    }

    /**
     * Retourne le canvas.
     * 
     * @return Le canvas pour le dessin
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Met à jour le label d'étape.
     * 
     * @param description La description de l'étape courante
     */
    public void mettreAJourEtape(String description) {
        lblEtape.setText(description != null ? description : "");
    }

    /**
     * Active ou désactive le bouton Jouer.
     * 
     * @param actif true pour activer, false pour désactiver
     */
    public void activerBoutonJouer(boolean actif) {
        btnJouer.setDisable(!actif);
    }

    /**
     * Active ou désactive le bouton Pause.
     * 
     * @param actif true pour activer, false pour désactiver
     */
    public void activerBoutonPause(boolean actif) {
        btnPause.setDisable(!actif);
    }

    /**
     * Initialise les composants de l'interface.
     */
    private void initialiserComposants() {
        // Canvas pour le dessin
        canvas = new Canvas(850, 450);

        // Label pour l'étape courante
        lblEtape = new Label("Prêt à démarrer");
        lblEtape.setStyle("-fx-font-size: 14px;");

        // Boutons de contrôle
        btnJouer = new Button("▶ Jouer");
        btnJouer.setOnAction(e -> controleur.jouer());

        btnPause = new Button("⏸ Pause");
        btnPause.setOnAction(e -> controleur.pause());
        btnPause.setDisable(true);

        btnArreter = new Button("⏹ Arrêter");
        btnArreter.setOnAction(e -> controleur.arreter());

        btnReinitialiser = new Button("⟲ Réinitialiser");
        btnReinitialiser.setOnAction(e -> controleur.reinitialiser());
    }

    /**
     * Crée le layout de la fenêtre.
     * 
     * @return Le conteneur racine
     */
    private BorderPane creerLayout() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Zone de dessin au centre
        VBox centerBox = new VBox(10);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().addAll(canvas, lblEtape);
        root.setCenter(centerBox);

        // Contrôles en bas
        HBox controles = new HBox(10);
        controles.setAlignment(Pos.CENTER);
        controles.setPadding(new Insets(10));
        controles.getChildren().addAll(btnJouer, btnPause, btnArreter, btnReinitialiser);
        root.setBottom(controles);

        return root;
    }
}
