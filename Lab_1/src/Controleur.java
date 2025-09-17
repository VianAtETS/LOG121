public class Controleur extends Observateur {
    private Sujet sujet;
    private boolean chauffageActif;
    private boolean climatisationActive;
    private boolean ventilationActive;

    public Controleur(Sujet sujet) {
        this.sujet = sujet;
        this.sujet.ajouterObservateur(this);
    }

    @Override
    public void mettreAJour() {
        System.out.println("Le contrôleur a été mis à jour en fonction des changements dans le sujet.");
    }

    public void demarrerChauffage() {
        chauffageActif = true;
        System.out.println("Chauffage démarré.");
    }

    public void arreterChauffage() {
        chauffageActif = false;
        System.out.println("Chauffage arrêté.");
    }

    public void demarrerClimatisation() {
        climatisationActive = true;
        System.out.println("Climatisation démarrée.");
    }

    public void arreterClimatisation() {
        climatisationActive = false;
        System.out.println("Climatisation arrêtée.");
    }

    public void demarrerVentilation() {
        ventilationActive = true;
        System.out.println("Ventilation démarrée.");
    }

    public void arreterVentilation() {
        ventilationActive = false;
        System.out.println("Ventilation arrêtée.");
    }
}
