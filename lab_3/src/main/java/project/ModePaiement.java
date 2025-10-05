package project;

public enum ModePaiement {
    CARTE_CREDIT("Carte de crédit"), CARTE_CADEAU("Carte cadeau"), PAIEMENT_LIVRAISON(
            "Paiement à la livraison");

    private final String label;

    ModePaiement(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
