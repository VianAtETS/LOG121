public class Controleur implements Observateur {
    private boolean chauffageActif = false;
    private boolean climatisationActive = false;
    private boolean ventilationActive = false;

    public Controleur() {
        // Constructeur par défaut - les sujets seront ajoutés via ajouterObservateur()
    }

    @Override
    public void mettreAJour(Sujet sujet) {
        System.out.println("Contrôleur : nouvelle valeur détectée : " + ((Capteur) sujet).toString());

        if (sujet instanceof CapteurTemperature) {
            double temperature = ((CapteurTemperature) sujet).getValeur();

            if (temperature < 22.0) {
                if (!chauffageActif) {
                    demarrerChauffage();
                }
                if (climatisationActive) {
                    arreterClimatisation();
                }
            } else if (temperature > 22.0) {
                if (!climatisationActive) {
                    demarrerClimatisation();
                }
                if (chauffageActif) {
                    arreterChauffage();
                }
            } else {
                if (chauffageActif) {
                    arreterChauffage();
                }
                if (climatisationActive) {
                    arreterClimatisation();
                }
            }
        }

        if (sujet instanceof CapteurCO2) {
            double concentration = ((CapteurCO2) sujet).getValeur();

            if (concentration > 1000.0) {
                if (!ventilationActive) {
                    demarrerVentilation();
                }
            } else {
                if (ventilationActive) {
                    arreterVentilation();
                }
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
