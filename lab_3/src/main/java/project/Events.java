package project;

/**
 * Énumération des événements possibles dans le formulaire de paiement.
 *
 * Utilisée par le médiateur pour gérer les interactions entre composants.
 */
public enum Events {
    /**
     * Événement déclenché lorsque le mode de paiement change (Carte de crédit, Carte cadeau,
     * Paiement à la livraison)
     */
    MOYEN_PAIEMENT_CHANGE,

    /**
     * Événement déclenché lorsque l'adresse de livraison est modifiée
     */
    ADRESSE_LIVRAISON_CHANGE,

    /**
     * Événement déclenché lorsque l'adresse de facturation est modifiée
     */
    ADRESSE_FACTURATION_CHANGE,

    /**
     * Événement déclenché lorsque l'option de livraison change (Livraison en main propre, Se
     * retrouver à l'extérieur, Laisser à la porte)
     */
    OPTION_LIVRAISON_CHANGE,

    /**
     * Événement déclenché lorsque la case "même adresse" est cochée/décochée
     */
    MEME_ADRESSE_CHANGE
}
