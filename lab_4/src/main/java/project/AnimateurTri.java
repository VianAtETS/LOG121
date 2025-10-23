package project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Gestionnaire d'animation pour visualiser les étapes du tri. Utilise Timeline de JavaFX pour
 * animer progressivement les étapes. Permet de contrôler la vitesse, mettre en pause, reprendre et
 * arrêter l'animation.
 */
public class AnimateurTri {
    private List<EtapeTri> etapes;
    private int indexEtapeCourante;
    private Timeline timeline;
    private double vitesse; // En secondes entre chaque étape
    private boolean enPause;
    private RenderTri renderTri;
    private Consumer<EtapeTri> callbackEtape;

    /**
     * Constructeur de l'animateur.
     * 
     * @param renderTri Le moteur de rendu pour afficher les étapes
     */
    public AnimateurTri(RenderTri renderTri) {
        this.renderTri = renderTri;
        this.etapes = new ArrayList<>();
        this.indexEtapeCourante = 0;
        this.vitesse = 1.0; // Vitesse normale par défaut (1 seconde)
        this.enPause = false;
    }

    /**
     * Définit un callback appelé à chaque nouvelle étape.
     * 
     * @param callback Le callback à appeler
     */
    public void setCallbackEtape(Consumer<EtapeTri> callback) {
        this.callbackEtape = callback;
    }

    /**
     * Définit les étapes à animer.
     * 
     * @param etapes La liste des étapes du tri
     */
    public void setEtapes(List<EtapeTri> etapes) {
        this.etapes = new ArrayList<>(etapes);
        this.indexEtapeCourante = 0;
    }

    /**
     * Définit la vitesse d'animation.
     * 
     * @param vitesse La durée en secondes entre chaque étape
     */
    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;

        // Si une animation est en cours, recréer la timeline avec la nouvelle vitesse
        if (timeline != null && timeline.getStatus() == Timeline.Status.RUNNING) {
            timeline.stop();
            creerTimeline();
            timeline.play();
        }
    }

    /**
     * Démarre l'animation depuis le début.
     */
    public void demarrer() {
        if (etapes.isEmpty()) {
            return;
        }

        indexEtapeCourante = 0;
        enPause = false;
        creerTimeline();
        timeline.play();
    }

    /**
     * Met l'animation en pause.
     */
    public void pause() {
        if (timeline != null && timeline.getStatus() == Timeline.Status.RUNNING) {
            timeline.pause();
            enPause = true;
        }
    }

    /**
     * Reprend l'animation après une pause.
     */
    public void reprendre() {
        if (timeline != null && enPause) {
            timeline.play();
            enPause = false;
        }
    }

    /**
     * Arrête complètement l'animation.
     */
    public void arreter() {
        if (timeline != null) {
            timeline.stop();
        }
        indexEtapeCourante = 0;
        enPause = false;
        renderTri.effacer();
    }

    /**
     * Réinitialise l'animation au début.
     */
    public void reinitialiser() {
        arreter();
        if (!etapes.isEmpty()) {
            indexEtapeCourante = 0;
            renderTri.dessiner(etapes.get(0));
        }
    }

    /**
     * Affiche l'étape suivante de l'animation.
     */
    private void afficherEtapeSuivante() {
        if (indexEtapeCourante < etapes.size()) {
            EtapeTri etape = etapes.get(indexEtapeCourante);
            renderTri.dessiner(etape);
            
            // Notifier le callback si défini
            if (callbackEtape != null) {
                callbackEtape.accept(etape);
            }
            
            indexEtapeCourante++;

            // Arrêter l'animation si on a atteint la fin
            if (indexEtapeCourante >= etapes.size()) {
                timeline.stop();
            }
        }
    }

    /**
     * Crée la timeline JavaFX pour l'animation.
     */
    private void creerTimeline() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(vitesse), event -> afficherEtapeSuivante()));
        timeline.setCycleCount(etapes.size());
    }

    /**
     * Vérifie si l'animation est en cours.
     * 
     * @return true si l'animation est en cours, false sinon
     */
    public boolean estEnCours() {
        return timeline != null && timeline.getStatus() == Timeline.Status.RUNNING;
    }

    /**
     * Vérifie si l'animation est en pause.
     * 
     * @return true si l'animation est en pause, false sinon
     */
    public boolean estEnPause() {
        return enPause;
    }

    /**
     * Retourne l'étape courante.
     * 
     * @return L'étape courante ou null si aucune étape
     */
    public EtapeTri getEtapeCourante() {
        if (indexEtapeCourante > 0 && indexEtapeCourante <= etapes.size()) {
            return etapes.get(indexEtapeCourante - 1);
        }
        return null;
    }
}
