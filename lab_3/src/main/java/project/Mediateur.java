package project;

/**
 * Interface du patron Médiateur.
 */
public interface Mediateur {
    /**
     * Ajoute un composant au médiateur et lui assigne ce médiateur
     *
     * @param composant le composant à ajouter
     */
    void ajouterComposant(Composant composant);

    /**
     * Notifie le médiateur qu'un événement s'est produit
     *
     * @param emetteur le composant émetteur de l'événement (peut être null)
     * @param evenement le type d'événement qui s'est produit
     */
    void notifier(Events evenement);
}
