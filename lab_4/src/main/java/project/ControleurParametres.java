package project;

import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur pour la vue des paramètres. Gère la validation et le lancement du tri.
 */
public class ControleurParametres {
    private GestionnaireTri gestionnaire;
    private VueParametres vueParametres;
    private ControleurVisualisation controleurVisualisation;

    /**
     * Constructeur du contrôleur des paramètres.
     * 
     * @param gestionnaire Le gestionnaire de tri
     */
    public ControleurParametres(GestionnaireTri gestionnaire) {
        this.gestionnaire = gestionnaire;
    }

    /**
     * Définit le contrôleur de visualisation.
     * 
     * @param controleur Le contrôleur de visualisation
     */
    public void setControleurVisualisation(ControleurVisualisation controleur) {
        this.controleurVisualisation = controleur;
    }

    /**
     * Définit la vue associée.
     * 
     * @param vue La vue des paramètres
     */
    public void setVue(VueParametres vue) {
        this.vueParametres = vue;
    }

    /**
     * Affiche la vue des paramètres.
     */
    public void afficherVue() {
        if (vueParametres != null) {
            vueParametres.afficher();
        }
    }

    /**
     * Valide les entrées et lance le tri.
     */
    public void validerEtLancerTri() {
        String collectionTexte = vueParametres.getCollection();

        // Valider la collection
        if (!validerCollection(collectionTexte)) {
            vueParametres.afficherErreur(
                    "Veuillez entrer une collection valide d'entiers séparés par des virgules.");
            return;
        }

        try {
            // Parser la collection
            List<Integer> collection = parseCollection(collectionTexte);

            if (collection.size() < 2) {
                vueParametres.afficherErreur("La collection doit contenir au moins 2 éléments.");
                return;
            }

            // Configurer le gestionnaire
            gestionnaire.setCollection(collection);

            // Définir l'algorithme
            String algorithme = vueParametres.getAlgorithmeSelectionne();
            TypeAlgorithme type = algorithme.equals("Quick Sort") ? TypeAlgorithme.QUICK_SORT
                    : TypeAlgorithme.MERGE_SORT;
            gestionnaire.setAlgorithme(type);

            // Exécuter le tri
            List<EtapeTri> etapes = gestionnaire.executerTri();

            // Convertir la vitesse
            double vitesse = convertirVitesse(vueParametres.getVitesse());

            // Lancer la visualisation
            if (controleurVisualisation != null) {
                controleurVisualisation.demarrerAnimation(etapes, vitesse);
            }

        } catch (Exception e) {
            vueParametres.afficherErreur("Erreur lors du traitement: " + e.getMessage());
        }
    }

    /**
     * Annule et ferme la fenêtre.
     */
    public void annuler() {
        System.exit(0);
    }

    /**
     * Valide le format de la collection.
     * 
     * @param texte Le texte à valider
     * @return true si valide, false sinon
     */
    private boolean validerCollection(String texte) {
        if (texte == null || texte.trim().isEmpty()) {
            return false;
        }

        // Vérifier le format: nombres séparés par des virgules
        String pattern = "^\\s*-?\\d+\\s*(,\\s*-?\\d+\\s*)*$";
        return texte.matches(pattern);
    }

    /**
     * Parse la chaîne de collection en liste d'entiers.
     * 
     * @param texte Le texte à parser
     * @return La liste d'entiers
     */
    private List<Integer> parseCollection(String texte) {
        List<Integer> collection = new ArrayList<>();
        String[] parts = texte.split(",");

        for (String part : parts) {
            collection.add(Integer.parseInt(part.trim()));
        }

        return collection;
    }

    /**
     * Convertit la vitesse texte en valeur numérique.
     * 
     * @param vitesse La vitesse sous forme de texte
     * @return La durée en secondes
     */
    private double convertirVitesse(String vitesse) {
        switch (vitesse) {
            case "Lente":
                return 2.0;
            case "Rapide":
                return 0.3;
            case "Normale":
            default:
                return 1.0;
        }
    }
}
