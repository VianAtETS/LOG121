package project;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class MediateurFormulairePaiement implements Mediateur {
    private ComboBox ModePaiementCombo;
    private TextField[] champCarteCredit;
    private TextField champCarteCadeau;
    private TextField champAdresseLivraison;
    private TextField champAdresseFacturation;
    private CheckBox caseACocherMemeAdresse;
    private ComboBox OptionLivraisonCombo;
    private FormulairePaiement formulairePaiement;

    public void ajouterComposant(Composant composant) {
        // Implementation to add component
    }

    public void notifier(Composant emetteur, String evenement) {
        // Implementation to notify components of events
    }

    public FormulairePaiement getFormulairePaiement() {
        return formulairePaiement;
    }

    private void gererChangementModePaiement() {
        String modePaiement = (String) ModePaiementCombo.getValue();
        switch (modePaiement) {
            case ModePaiement.CARTE_CREDIT:
                for (TextField champ : champCarteCredit) {
                    champ.setDisable(false);
                }
                champCarteCadeau.setDisable(true);
                champAdresseLivraison.setDisable(true);
                champAdresseFacturation.setDisable(true);
                caseACocherMemeAdresse.setDisable(true);
                OptionLivraisonCombo.setDisable(true);
                break;
            case ModePaiement.CARTE_CADEAU:
                for (TextField champ : champCarteCredit) {
                    champ.setDisable(true);
                }
                champCarteCadeau.setDisable(false);
                champAdresseLivraison.setDisable(true);
                champAdresseFacturation.setDisable(true);
                caseACocherMemeAdresse.setDisable(true);
                OptionLivraisonCombo.setDisable(true);
                break;
            case ModePaiement.PAIEMENT_LIVRAISON:
                for (TextField champ : champCarteCredit) {
                    champ.setDisable(true);
                }
                champCarteCadeau.setDisable(true);
                champAdresseLivraison.setDisable(false);
                champAdresseFacturation.setDisable(false);
                caseACocherMemeAdresse.setDisable(false);
                OptionLivraisonCombo.setDisable(false);
                break;
            default:
                for (TextField champ : champCarteCredit) {
                    champ.setDisable(true);
                }
                champCarteCadeau.setDisable(true);
                champAdresseLivraison.setDisable(true);
                champAdresseFacturation.setDisable(true);
                caseACocherMemeAdresse.setDisable(true);
                OptionLivraisonCombo.setDisable(true);
                break;
        }
    }

    private void gererChangementMemeAdresse() {
        boolean memeAdresse = caseACocherMemeAdresse.isSelected();
        champAdresseFacturation.setDisable(memeAdresse);
    }

    private void gererChangementAdresseLivraison() {
        boolean memeAdresse = caseACocherMemeAdresse.isSelected();
        champAdresseLivraison.setDisable(memeAdresse);
    }

    private void mettreAJourVisibiliteChamps(ModePaiement modePaiement) {
        switch (modePaiement) {
            case CARTE_CREDIT:
                // Show credit card fields
                break;
            case CARTE_CADEAU:
                // Show gift card fields
                break;
            case PAIEMENT_LIVRAISON:
                // Show delivery payment fields
                break;
            default:
                // Hide all fields
                break;
        }
    }

    private void mettreAJourOptionsLivraison(OptionLivraison optionLivraison) {
        switch (optionLivraison) {
            case LIVRAISON_MAIN_PROPRE:
                // Handle "Livraison main propre" option
                break;
            case RENCONTRE_EXTERIEUR:
                // Handle "Rencontre à l'extérieur" option
                break;
            case LAISSER_PORTE:
                // Handle "Laisser à la porte" option
                break;
            default:
                // Handle default case
                break;
        }
    }

    private void mettreAJourDonneesFormulaire() {
        formulairePaiement.setModePaiement((ModePaiement) modePaiementCombo.getValue());
        formulairePaiement.setCarteCredit(champCarteCredit.getText());
        formulairePaiement.setCarteCadeau(champCarteCadeau.getText());
        formulairePaiement.setAdresseLivraison(champAdresseLivraison.getText());
        formulairePaiement.setAdresseFacturation(champAdresseFacturation.getText());
        formulairePaiement.setMemeAdresse(caseACocherMemeAdresse.isSelected());
        formulairePaiement.setOptionLivraison((OptionLivraison) optionLivraisonCombo.getValue());
    }

    private void validerFormulaire() {
        boolean estValide = formulairePaiement.validerFormulaire();
        // Update UI based on validation result
    }
}
