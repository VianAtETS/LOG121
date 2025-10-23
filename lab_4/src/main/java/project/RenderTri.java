package project;

/**
 * Interface définissant le contrat pour le rendu visuel des étapes de tri. Permet de découpler la
 * logique de tri de la visualisation.
 */
public interface RenderTri {
    /**
     * Dessine l'état actuel du tri à l'écran.
     * 
     * @param etape L'étape du tri à visualiser
     */
    void dessiner(EtapeTri etape);

    /**
     * Efface le contenu actuel du rendu.
     */
    void effacer();
}
