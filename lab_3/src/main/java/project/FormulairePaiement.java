package project;

public class FormulairePaiement {
    private ModePaiement modePaiement;
    private CarteCredit carteCredit;
    private CarteCadeau carteCadeau;
    private Adresse adresseLivraison;
    private Adresse adresseFacturation;
    private OptionLivraison optionLivraison;
    private boolean memeAdresse;

    public boolean validerFormulaire() {
        if (modePaiement == null) {
            return false;
        }

        switch (modePaiement) {
            case CARTE_CREDIT:
                return carteCredit != null && carteCredit.valider();
            case CARTE_CADEAU:
                return carteCadeau != null && carteCadeau.valider();
            case PAIEMENT_LIVRAISON:
                if (adresseLivraison == null || optionLivraison == null) {
                    return false;
                }
                if (!memeAdresse && adresseFacturation == null) {
                    return false;
                }
                return true;
            default:
                return false;
        }
    }

    public ModePaiement getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
    }

    public CarteCredit getCarteCredit() {
        return carteCredit;
    }

    public void setCarteCredit(CarteCredit carteCredit) {
        this.carteCredit = carteCredit;
    }

    public CarteCadeau getCarteCadeau() {
        return carteCadeau;
    }

    public void setCarteCadeau(CarteCadeau carteCadeau) {
        this.carteCadeau = carteCadeau;
    }

    public Adresse getAdresseLivraison() {
        return adresseLivraison;
    }

    public void setAdresseLivraison(Adresse adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public Adresse getAdresseFacturation() {
        return adresseFacturation;
    }

    public void setAdresseFacturation(Adresse adresseFacturation) {
        this.adresseFacturation = adresseFacturation;
    }

    public boolean isMemeAdresse() {
        return memeAdresse;
    }

    public void setMemeAdresse(boolean memeAdresse) {
        this.memeAdresse = memeAdresse;
    }

    public OptionLivraison getOptionLivraison() {
        return optionLivraison;
    }

    public void setOptionLivraison(OptionLivraison optionLivraison) {
        this.optionLivraison = optionLivraison;
    }
}
