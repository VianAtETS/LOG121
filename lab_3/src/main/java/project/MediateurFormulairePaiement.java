package project;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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

    public MediateurFormulairePaiement() {
        this.formulairePaiement = new FormulairePaiement();
    }

    // Setters pour les composants
    public void setModePaiementCombo(ComboBox<String> combo) {
        this.modePaiementCombo = combo;
    }

    public void setChampCarteCredit(TextField[] champs) {
        this.champCarteCredit = champs;
    }

    public void setCarteCreditBox(VBox box) {
        this.carteCreditBox = box;
    }

    public void setChampCarteCadeau(TextField champ) {
        this.champCarteCadeau = champ;
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

    @Override
    public void ajouterComposant(Composant composant) {
        composant.setMediateur(this);
    }

    @Override
    public void notifier(Composant emetteur, String evenement) {
        switch (evenement) {
            case "MOYEN_PAIEMENT_CHANGE":
                gererChangementModePaiement();
                break;
            case "MEME_ADRESSE_CHANGE":
                gererChangementMemeAdresse();
                break;
            case "ADRESSE_LIVRAISON_CHANGE":
                gererChangementAdresseLivraison();
                break;
            default:
                break;
        }
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
                // Afficher les champs de carte de crédit
                carteCreditBox.setVisible(true);
                carteCreditBox.setManaged(true);

                // Masquer les champs de carte cadeau
                carteCadeauBox.setVisible(false);
                carteCadeauBox.setManaged(false);

                // Désactiver les options de livraison
                optionLivraisonCombo.setDisable(true);
                champAdresseLivraison.setDisable(true);
                champAdresseFacturation.setDisable(true);
                caseACocherMemeAdresse.setDisable(true);

                // Mettre à jour le modèle
                formulairePaiement.setModePaiement(ModePaiement.CARTE_CREDIT);
                break;

            case "Carte cadeau":
                // Masquer les champs de carte de crédit
                carteCreditBox.setVisible(false);
                carteCreditBox.setManaged(false);

                // Afficher les champs de carte cadeau
                carteCadeauBox.setVisible(true);
                carteCadeauBox.setManaged(true);

                // Désactiver les options de livraison
                optionLivraisonCombo.setDisable(true);
                champAdresseLivraison.setDisable(true);
                champAdresseFacturation.setDisable(true);
                caseACocherMemeAdresse.setDisable(true);

                // Mettre à jour le modèle
                formulairePaiement.setModePaiement(ModePaiement.CARTE_CADEAU);
                break;

            case "Paiement à la livraison":
                // Masquer tous les champs de carte
                carteCreditBox.setVisible(false);
                carteCreditBox.setManaged(false);
                carteCadeauBox.setVisible(false);
                carteCadeauBox.setManaged(false);

                // Activer les champs d'adresse et options de livraison
                champAdresseLivraison.setDisable(false);
                champAdresseFacturation.setDisable(false);
                caseACocherMemeAdresse.setDisable(false);
                optionLivraisonCombo.setDisable(false);

                // Retirer l'option "Laisser à la porte"
                optionLivraisonCombo.setItems(FXCollections.observableArrayList(
                        "Livraison en main propre", "Se retrouver à l'extérieur"));

                // Mettre à jour le modèle
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
            // Désactiver le champ adresse de facturation
            champAdresseFacturation.setDisable(true);
            // Copier l'adresse de livraison dans l'adresse de facturation
            champAdresseFacturation.setText(champAdresseLivraison.getText());
        } else {
            // Activer le champ adresse de facturation
            champAdresseFacturation.setDisable(false);
        }

        formulairePaiement.setMemeAdresse(memeAdresse);
        mettreAJourDonneesFormulaire();
    }

    private void gererChangementAdresseLivraison() {
        boolean memeAdresse = caseACocherMemeAdresse.isSelected();

        if (memeAdresse) {
            // Si "même adresse" est cochée, mettre à jour l'adresse de facturation
            champAdresseFacturation.setText(champAdresseLivraison.getText());
        }

        mettreAJourDonneesFormulaire();
    }

    private void mettreAJourDonneesFormulaire() {
        // Mettre à jour le mode de paiement
        String modePaiement = modePaiementCombo.getValue();
        if (modePaiement != null) {
            switch (modePaiement) {
                case "Carte de crédit":
                    formulairePaiement.setModePaiement(ModePaiement.CARTE_CREDIT);

                    // Créer l'objet CarteCredit si les champs sont remplis
                    if (champCarteCredit[0].getText() != null
                            && !champCarteCredit[0].getText().isEmpty()) {
                        try {
                            Date dateExp = parseDate(champCarteCredit[1].getText());
                            CarteCredit carte = new CarteCredit(champCarteCredit[0].getText(),
                                    dateExp, champCarteCredit[2].getText());
                            formulairePaiement.setCarteCredit(carte);
                        } catch (Exception e) {
                            // Date invalide, on ne met pas à jour
                        }
                    }
                    break;

                case "Carte cadeau":
                    formulairePaiement.setModePaiement(ModePaiement.CARTE_CADEAU);

                    // Créer l'objet CarteCadeau si le champ est rempli
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

        // Mettre à jour les adresses (simplifié - normalement on devrait parser l'adresse)
        formulairePaiement.setMemeAdresse(caseACocherMemeAdresse.isSelected());

        // Mettre à jour l'option de livraison
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
        return sdf.parse(dateStr);
    }

    private void validerFormulaire() {
        boolean estValide = formulairePaiement.validerFormulaire();
        System.out.println("Formulaire valide: " + estValide);
    }
}
