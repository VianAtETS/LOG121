package project;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.List;
import java.util.Map;

/**
 * Implémentation du rendu sous forme de barres verticales. Utilise JavaFX Canvas pour dessiner la
 * visualisation. Chaque entier est représenté par une barre dont la hauteur est proportionnelle à
 * sa valeur.
 */
public class RenderBarres implements RenderTri {
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final Color couleurNormale;
    private final Color couleurComparaison;
    private final Color couleurPivot;
    private final Color couleurFusion;

    /**
     * Constructeur du rendu par barres.
     * 
     * @param canvas Le canvas JavaFX sur lequel dessiner
     */
    public RenderBarres(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.couleurNormale = Color.STEELBLUE;
        this.couleurComparaison = Color.ORANGE;
        this.couleurPivot = Color.RED;
        this.couleurFusion = Color.GREEN;
    }

    /**
     * Dessine l'état actuel du tri sous forme de barres.
     * 
     * @param etape L'étape du tri à visualiser
     */
    @Override
    public void dessiner(EtapeTri etape) {
        effacer();

        List<Integer> collection = etape.getCollectionState();
        Map<String, Integer> indices = etape.getIndices();

        if (collection.isEmpty()) {
            return;
        }

        // Calculer les dimensions
        int nbElements = collection.size();
        double largeurBarre = calculerDimensionsBarre(nbElements);
        double espacement = 2.0;
        int maxValeur = collection.stream().max(Integer::compareTo).orElse(1);

        // Dessiner chaque barre
        for (int i = 0; i < collection.size(); i++) {
            int valeur = collection.get(i);
            double x = i * (largeurBarre + espacement) + 20;
            double hauteur = calculerHauteur(valeur, maxValeur);
            double y = canvas.getHeight() - hauteur - 50;

            // Déterminer la couleur selon les indices
            Color couleur = determinerCouleur(i, indices);

            // Dessiner la barre
            dessinerBarre(x, y, largeurBarre, hauteur, couleur);

            // Dessiner la valeur
            dessinerValeur(valeur, x, canvas.getHeight() - 30, largeurBarre);
        }
    }

    /**
     * Efface le canvas.
     */
    @Override
    public void effacer() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    /**
     * Calcule la largeur d'une barre selon le nombre d'éléments.
     * 
     * @param nbElements Le nombre d'éléments dans la collection
     * @return La largeur d'une barre
     */
    private double calculerDimensionsBarre(int nbElements) {
        double largeurDisponible = canvas.getWidth() - 40;
        return Math.max((largeurDisponible / nbElements) - 2, 10);
    }

    /**
     * Calcule la hauteur d'une barre selon sa valeur.
     * 
     * @param valeur La valeur de l'élément
     * @param maxValeur La valeur maximale dans la collection
     * @return La hauteur de la barre en pixels
     */
    private double calculerHauteur(int valeur, int maxValeur) {
        double hauteurMax = canvas.getHeight() - 100;
        return (valeur * hauteurMax) / maxValeur;
    }

    /**
     * Détermine la couleur d'une barre selon les indices de l'étape.
     * 
     * @param index L'indice de la barre
     * @param indices Les indices importants de l'étape
     * @return La couleur à utiliser
     */
    private Color determinerCouleur(int index, Map<String, Integer> indices) {
        if (indices == null || indices.isEmpty()) {
            return couleurNormale;
        }

        // Pivot (rouge)
        if (indices.containsKey("pivot") && indices.get("pivot") == index) {
            return couleurPivot;
        }

        // Indices de comparaison ou d'échange (orange)
        if ((indices.containsKey("i") && indices.get("i") == index)
                || (indices.containsKey("j") && indices.get("j") == index)) {
            return couleurComparaison;
        }

        // Zones de fusion (vert)
        if (indices.containsKey("debut") && indices.containsKey("fin")) {
            int debut = indices.get("debut");
            int fin = indices.get("fin");
            if (index >= debut && index <= fin) {
                return couleurFusion;
            }
        }

        return couleurNormale;
    }

    /**
     * Dessine une barre rectangulaire.
     * 
     * @param x Position X
     * @param y Position Y
     * @param largeur Largeur de la barre
     * @param hauteur Hauteur de la barre
     * @param couleur Couleur de la barre
     */
    private void dessinerBarre(double x, double y, double largeur, double hauteur, Color couleur) {
        // Remplir la barre
        gc.setFill(couleur);
        gc.fillRect(x, y, largeur, hauteur);

        // Bordure
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeRect(x, y, largeur, hauteur);
    }

    /**
     * Dessine la valeur d'un élément sous sa barre.
     * 
     * @param valeur La valeur à afficher
     * @param x Position X
     * @param y Position Y
     * @param largeur Largeur de la zone de texte
     */
    private void dessinerValeur(int valeur, double x, double y, double largeur) {
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font(10));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText(String.valueOf(valeur), x + largeur / 2, y);
    }
}
