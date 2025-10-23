package project;

/**
 * Interface Observer pour le patron Observateur. Permet aux observateurs d'être notifiés des étapes
 * du tri.
 */
public interface ObservateurTri {
    /**
     * Notifie l'observateur d'une nouvelle étape dans le processus de tri.
     * 
     * @param etape L'étape du tri qui vient d'être effectuée
     */
    void notifierNouvelleEtape(EtapeTri etape);

    /**
     * Notifie l'observateur que le tri est terminé.
     */
    void notifierFinTri();
}
