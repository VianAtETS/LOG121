package project;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class ControleurFormulairePaiement {

    @FXML
    private ComboBox<String> modePaiementCombo;

    @FXML
    private VBox carteCreditBox;

    @FXML
    private TextField numeroCreditField;

    @FXML
    private TextField dateExpirationField;

    @FXML
    private TextField codeSecuriteField;

    @FXML
    private HBox carteCadeauBox;

    @FXML
    private TextField numeroCadeauField;

    @FXML
    private TextField adresseLivraisonField;

    @FXML
    private CheckBox memeAdresseCheckBox;

    @FXML
    private TextField adresseFacturationField;

    @FXML
    private ComboBox<String> optionLivraisonCombo;

    @FXML
    private javafx.scene.control.Label labelErreur;

    private MediateurFormulairePaiement mediateur;

    @FXML
    public void initialize() {
        // Créer le médiateur
        mediateur = new MediateurFormulairePaiement();

        // Passer les références des composants au médiateur
        mediateur.setModePaiementCombo(modePaiementCombo);
        mediateur.setChampCarteCredit(
                new TextField[] {numeroCreditField, dateExpirationField, codeSecuriteField});
        mediateur.setCarteCreditBox(carteCreditBox);
        mediateur.setChampCarteCadeau(numeroCadeauField);
        mediateur.setCarteCadeauBox(carteCadeauBox);
        mediateur.setChampAdresseLivraison(adresseLivraisonField);
        mediateur.setChampAdresseFacturation(adresseFacturationField);
        mediateur.setCaseACocherMemeAdresse(memeAdresseCheckBox);
        mediateur.setOptionLivraisonCombo(optionLivraisonCombo);
        mediateur.setLabelErreur(labelErreur);

        // Configurer les listeners
        modePaiementCombo.setOnAction(event -> {
            mediateur.notifier(Events.MODE_PAIEMENT_CHANGE);
        });

        memeAdresseCheckBox.setOnAction(event -> {
            mediateur.notifier(Events.MEME_ADRESSE_CHANGE);
        });

        adresseLivraisonField.textProperty().addListener((observable, oldValue, newValue) -> {
            mediateur.notifier(Events.ADRESSE_LIVRAISON_CHANGE);
        });

        // Initialiser l'état du formulaire
        carteCreditBox.setVisible(false);
        carteCreditBox.setManaged(false);
        carteCadeauBox.setVisible(false);
        carteCadeauBox.setManaged(false);
        optionLivraisonCombo.setDisable(true);

        // Sélectionner le premier mode de paiement par défaut
        modePaiementCombo.getSelectionModel().selectFirst();
        mediateur.notifier(Events.MODE_PAIEMENT_CHANGE);
    }
}
