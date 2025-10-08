package project;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class MediateurFormulairePaiement implements Mediateur {
    private ComboBox<String> modePaiementCombo;
    private TextField[] champCarteCredit;
    private VBox carteCreditBox;
    private TextField champCarteCadeau;
    private HBox carteCadeauBox;
    private TextField champAdresseLivraison;
    private TextField champAdresseFacturation;
    private CheckBox caseACocherMemeAdresse;
    private ComboBox<String> optionLivraisonCombo;
    private FormulairePaiement formulairePaiement;
    private Label labelErreur; // Nouveau label pour les erreurs

    public MediateurFormulairePaiement() {
        this.formulairePaiement = new FormulairePaiement();
    }

    // Setters pour les composants
    public void setModePaiementCombo(ComboBox<String> combo) {
        this.modePaiementCombo = combo;
    }

    public void setChampCarteCredit(TextField[] champs) {
        this.champCarteCredit = champs;
        // Ajouter des listeners pour validation en temps réel
        for (TextField champ : champs) {
            champ.textProperty().addListener((obs, old, nouveau) -> validerFormulaire());
        }
    }

    public void setCarteCreditBox(VBox box) {
        this.carteCreditBox = box;
    }

    public void setChampCarteCadeau(TextField champ) {
        this.champCarteCadeau = champ;
        // Listener pour validation en temps réel
        champ.textProperty().addListener((obs, old, nouveau) -> validerFormulaire());
    }

    public void setCarteCadeauBox(HBox box) {
        this.carteCadeauBox = box;
    }

    public void setChampAdresseLivraison(TextField champ) {
        this.champAdresseLivraison = champ;
    }

    public void setChampAdresseFacturation(TextField champ) {
        this.champAdresseFacturation = champ;
    }

    public void setCaseACocherMemeAdresse(CheckBox checkBox) {
        this.caseACocherMemeAdresse = checkBox;
    }

    public void setOptionLivraisonCombo(ComboBox<String> combo) {
        this.optionLivraisonCombo = combo;
    }

    public void setLabelErreur(Label label) {
        this.labelErreur = label;
        this.labelErreur.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        this.labelErreur.setVisible(false);
    }

    public void notifier(Events evenement) {
        switch (evenement) {
            case MODE_PAIEMENT_CHANGE:
                gererChangementModePaiement();
                break;
            case MEME_ADRESSE_CHANGE:
                gererChangementMemeAdresse();
                break;
            case ADRESSE_LIVRAISON_CHANGE:
                gererChangementAdresseLivraison();
                break;
            default:
                break;
        }
        validerFormulaire();
    }

    public FormulairePaiement getFormulairePaiement() {
        return formulairePaiement;
    }

    private void gererChangementModePaiement() {
        String modePaiement = modePaiementCombo.getValue();

        if (modePaiement == null) {
            return;
        }

        switch (modePaiement) {
            case "Carte de crédit":
                carteCreditBox.setVisible(true);
                carteCreditBox.setManaged(true);
                carteCadeauBox.setVisible(false);
                carteCadeauBox.setManaged(false);
                optionLivraisonCombo.setDisable(false);
                champAdresseLivraison.setDisable(false);

                if (!caseACocherMemeAdresse.isSelected()) {
                    champAdresseFacturation.setDisable(false);
                }

                caseACocherMemeAdresse.setDisable(false);
                formulairePaiement.setModePaiement(ModePaiement.CARTE_CREDIT);
                break;

            case "Carte cadeau":
                carteCreditBox.setVisible(false);
                carteCreditBox.setManaged(false);
                carteCadeauBox.setVisible(true);
                carteCadeauBox.setManaged(true);
                optionLivraisonCombo.setDisable(false);
                champAdresseLivraison.setDisable(false);
                champAdresseFacturation.setDisable(true);
                caseACocherMemeAdresse.setDisable(true);
                formulairePaiement.setModePaiement(ModePaiement.CARTE_CADEAU);
                break;

            case "Paiement à la livraison":
                carteCreditBox.setVisible(false);
                carteCreditBox.setManaged(false);
                carteCadeauBox.setVisible(false);
                carteCadeauBox.setManaged(false);
                champAdresseLivraison.setDisable(false);

                if (!caseACocherMemeAdresse.isSelected()) {
                    champAdresseFacturation.setDisable(false);
                }

                caseACocherMemeAdresse.setDisable(false);
                optionLivraisonCombo.setDisable(false);
                optionLivraisonCombo.setItems(FXCollections.observableArrayList(
                        "Livraison en main propre", "Se retrouver à l'extérieur"));
                formulairePaiement.setModePaiement(ModePaiement.PAIEMENT_LIVRAISON);
                break;

            default:
                break;
        }

        mettreAJourDonneesFormulaire();
    }

    private void gererChangementMemeAdresse() {
        boolean memeAdresse = caseACocherMemeAdresse.isSelected();

        if (memeAdresse) {
            champAdresseFacturation.setDisable(true);
            champAdresseFacturation.setText(champAdresseLivraison.getText());
        } else {
            champAdresseFacturation.setDisable(false);
        }

        formulairePaiement.setMemeAdresse(memeAdresse);
        mettreAJourDonneesFormulaire();
    }

    private void gererChangementAdresseLivraison() {
        boolean memeAdresse = caseACocherMemeAdresse.isSelected();

        if (memeAdresse) {
            champAdresseFacturation.setText(champAdresseLivraison.getText());
        }

        mettreAJourDonneesFormulaire();
    }

    private void mettreAJourDonneesFormulaire() {
        String modePaiement = modePaiementCombo.getValue();
        if (modePaiement != null) {
            switch (modePaiement) {
                case "Carte de crédit":
                    formulairePaiement.setModePaiement(ModePaiement.CARTE_CREDIT);

                    if (champCarteCredit[0].getText() != null
                            && !champCarteCredit[0].getText().isEmpty()) {
                        try {
                            Date dateExp = parseDate(champCarteCredit[1].getText());
                            CarteCredit carte = new CarteCredit(champCarteCredit[0].getText(),
                                    dateExp, champCarteCredit[2].getText());
                            formulairePaiement.setCarteCredit(carte);
                        } catch (Exception e) {
                            formulairePaiement.setCarteCredit(null);
                        }
                    }
                    break;

                case "Carte cadeau":
                    formulairePaiement.setModePaiement(ModePaiement.CARTE_CADEAU);

                    if (champCarteCadeau.getText() != null
                            && !champCarteCadeau.getText().isEmpty()) {
                        CarteCadeau carte = new CarteCadeau(champCarteCadeau.getText());
                        formulairePaiement.setCarteCadeau(carte);
                    }
                    break;

                case "Paiement à la livraison":
                    formulairePaiement.setModePaiement(ModePaiement.PAIEMENT_LIVRAISON);
                    break;
            }
        }

        formulairePaiement.setMemeAdresse(caseACocherMemeAdresse.isSelected());

        String optionLivraison = optionLivraisonCombo.getValue();
        if (optionLivraison != null) {
            switch (optionLivraison) {
                case "Livraison en main propre":
                    formulairePaiement.setOptionLivraison(OptionLivraison.LIVRAISON_MAIN_PROPRE);
                    break;
                case "Se retrouver à l'extérieur":
                    formulairePaiement.setOptionLivraison(OptionLivraison.RENCONTRE_EXTERIEUR);
                    break;
                case "Laisser à la porte":
                    formulairePaiement.setOptionLivraison(OptionLivraison.LAISSER_PORTE);
                    break;
            }
        }
    }

    private Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
        sdf.setLenient(false);
        return sdf.parse(dateStr);
    }

    private void validerFormulaire() {
        if (labelErreur == null) {
            return; // Pas de label d'erreur configuré
        }

        mettreAJourDonneesFormulaire();

        String modePaiement = modePaiementCombo.getValue();
        if (modePaiement == null) {
            afficherErreur("Veuillez sélectionner un mode de paiement.");
            return;
        }

        switch (modePaiement) {
            case "Carte de crédit":
                validerCarteCredit();
                break;
            case "Carte cadeau":
                validerCarteCadeau();
                break;
            case "Paiement à la livraison":
                validerPaiementLivraison();
                break;
        }
    }

    private void validerCarteCredit() {
        String numero = champCarteCredit[0].getText();
        String dateExp = champCarteCredit[1].getText();
        String cvv = champCarteCredit[2].getText();

        // Vérifier si les champs sont vides
        if (numero == null || numero.trim().isEmpty()) {
            cacherErreur();
            return;
        }

        if (dateExp == null || dateExp.trim().isEmpty()) {
            afficherErreur("La date d'expiration est requise.");
            return;
        }

        if (cvv == null || cvv.trim().isEmpty()) {
            afficherErreur("Le code de sécurité est requis.");
            return;
        }

        // Créer et valider la carte
        try {
            Date date = parseDate(dateExp);
            CarteCredit carte = new CarteCredit(numero, date, cvv);

            if (!carte.valider()) {
                // Déterminer quel champ est invalide
                if (!carte.validerNumero()) {
                    afficherErreur(
                            "Numéro de carte invalide (doit être 16 chiffres et passer l'algorithme de Luhn).");
                } else if (!cvv.matches("\\d{3,4}")) {
                    afficherErreur("Code de sécurité invalide (doit être 3 ou 4 chiffres).");
                } else if (date.before(new Date())) {
                    afficherErreur("La carte de crédit est expirée.");
                } else {
                    afficherErreur("Informations de carte de crédit invalides.");
                }
            } else {
                cacherErreur();
            }
        } catch (ParseException e) {
            afficherErreur("Format de date invalide (utilisez MM/AA).");
        }
    }

    private void validerCarteCadeau() {
        String numero = champCarteCadeau.getText();

        if (numero == null || numero.trim().isEmpty()) {
            cacherErreur();
            return;
        }

        CarteCadeau carte = new CarteCadeau(numero);

        if (!carte.valider()) {
            afficherErreur("Numéro de carte cadeau invalide (doit être 16 chiffres).");
        } else {
            cacherErreur();
        }
    }

    private void validerPaiementLivraison() {
        // Pour le paiement à la livraison, pas de validation de carte nécessaire
        cacherErreur();
    }

    private void afficherErreur(String message) {
        if (labelErreur != null) {
            labelErreur.setText("❌ " + message);
            labelErreur.setVisible(true);
        }
    }

    private void cacherErreur() {
        if (labelErreur != null) {
            labelErreur.setVisible(false);
        }
    }
}
