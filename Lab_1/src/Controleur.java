public class Controleur extends Observateur {
    private Sujet sujet;
    private boolean chauffageActif = false;
    private boolean climatisationActive = false;
    private boolean ventilationActive = false;

    public Controleur(Sujet sujet) {
        this.sujet = sujet;
        this.sujet.ajouterObservateur(this);
    }

    @Override
    public void mettreAJour() {
        System.out.println("Contrôleur : nouvelle valeur détectée : " + ((Capteur) sujet).toString());

        if (sujet instanceof CapteurTemperature) {
            CapteurTemperature capteur = (CapteurTemperature) sujet;
            double temp = capteur.getValeur();

            if (temp < 22.0) {
                demarrerChauffage();
                arreterClimatisation();
            } else if (temp > 22.0) {
                demarrerClimatisation();
                arreterChauffage();
            } else {
                arreterChauffage();
                arreterClimatisation();
            }
        }

        if (sujet instanceof CapteurCO2) {
            CapteurCO2 capteur = (CapteurCO2) sujet;
            double co2 = capteur.getValeur();

            if (co2 > 1000.0) {
                demarrerVentilation();
            } else {
                arreterVentilation();
            }
        }
    }

    private void demarrerChauffage() {
        chauffageActif = true;
        System.out.println("Chauffage démarré.");
    }

    private void arreterChauffage() {
        chauffageActif = false;
        System.out.println("Chauffage arrêté.");
    }

    private void demarrerClimatisation() {
        climatisationActive = true;
        System.out.println("Climatisation démarrée.");
    }

    private void arreterClimatisation() {
        climatisationActive = false;
        System.out.println("Climatisation arrêtée.");
    }

    private void demarrerVentilation() {
        ventilationActive = true;
        System.out.println("Ventilation démarrée.");
    }

    private void arreterVentilation() {
        ventilationActive = false;
        System.out.println("Ventilation arrêtée.");
    }
}
