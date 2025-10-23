package project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Vue pour la saisie des paramètres du tri. Permet à l'utilisateur de choisir l'algorithme, la
 * collection et la vitesse.
 */
public class VueParametres {
    private Stage stage;
    private ControleurParametres controleur;
    private ComboBox<String> comboAlgorithme;
    private TextField txtCollection;
    private ComboBox<String> comboVitesse;

    /**
     * Constructeur de la vue des paramètres.
     * 
     * @param stage Le stage JavaFX
     * @param controleur Le contrôleur associé
     */
    public VueParametres(Stage stage, ControleurParametres controleur) {
        this.stage = stage;
        this.controleur = controleur;
        initialiserComposants();
    }

    /**
     * Affiche la fenêtre des paramètres.
     */
    public void afficher() {
        Scene scene = new Scene(creerLayout(), 500, 300);
        stage.setScene(scene);
        stage.setTitle("Paramètres de Tri");
        stage.show();
    }

    /**
     * Ferme la fenêtre.
     */
    public void fermer() {
        stage.close();
    }

    /**
     * Retourne l'algorithme sélectionné.
     * 
     * @return Le nom de l'algorithme
     */
    public String getAlgorithmeSelectionne() {
        return comboAlgorithme.getValue();
    }

    /**
     * Retourne la collection saisie.
     * 
     * @return La chaîne représentant la collection
     */
    public String getCollection() {
        return txtCollection.getText();
    }

    /**
     * Retourne la vitesse sélectionnée.
     * 
     * @return La vitesse sous forme de chaîne
     */
    public String getVitesse() {
        return comboVitesse.getValue();
    }

    /**
     * Affiche un message d'erreur.
     * 
     * @param message Le message à afficher
     */
    public void afficherErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Initialise les composants de l'interface.
     */
    private void initialiserComposants() {
        // ComboBox pour l'algorithme
        comboAlgorithme = new ComboBox<>();
        comboAlgorithme.getItems().addAll("Quick Sort", "Merge Sort");
        comboAlgorithme.setValue("Quick Sort");

        // TextField pour la collection
        txtCollection = new TextField();
        txtCollection.setPromptText("Ex: 5, 2, 8, 1, 9, 3");

        // ComboBox pour la vitesse
        comboVitesse = new ComboBox<>();
        comboVitesse.getItems().addAll("Lente", "Normale", "Rapide");
        comboVitesse.setValue("Normale");
    }

    /**
     * Crée le layout de la fenêtre.
     * 
     * @return Le conteneur racine
     */
    private GridPane creerLayout() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Titre
        Label titre = new Label("Configuration du Tri");
        titre.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        grid.add(titre, 0, 0, 2, 1);

        // Algorithme
        Label lblAlgorithme = new Label("Algorithme:");
        grid.add(lblAlgorithme, 0, 1);
        grid.add(comboAlgorithme, 1, 1);

        // Collection
        Label lblCollection = new Label("Collection d'entiers:");
        grid.add(lblCollection, 0, 2);
        grid.add(txtCollection, 1, 2);

        Label lblAide = new Label("(Séparez les nombres par des virgules)");
        lblAide.setStyle("-fx-font-size: 10px; -fx-text-fill: gray;");
        grid.add(lblAide, 1, 3);

        // Vitesse
        Label lblVitesse = new Label("Vitesse:");
        grid.add(lblVitesse, 0, 4);
        grid.add(comboVitesse, 1, 4);

        // Boutons
        Button btnLancer = new Button("Lancer le tri");
        btnLancer.setOnAction(e -> controleur.validerEtLancerTri());
        btnLancer.setDefaultButton(true);

        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.setOnAction(e -> controleur.annuler());

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().addAll(btnLancer, btnAnnuler);
        grid.add(hbBtn, 0, 5, 2, 1);

        return grid;
    }
}
