package project;

/**
 * Interface du patron Médiateur.
 */
public interface Mediateur {
    /**
     * Notifie le médiateur qu'un événement s'est produit
     *
     * @param emetteur le composant émetteur de l'événement (peut être null)
     * @param evenement le type d'événement qui s'est produit
     */
    void notifier(Events evenement);
}
