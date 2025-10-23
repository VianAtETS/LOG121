package project;

import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import java.util.List;

/**
 * Contrôleur pour la vue de visualisation. Gère l'animation du tri et les interactions utilisateur.
 */
public class ControleurVisualisation {
    private GestionnaireTri gestionnaire;
    private AnimateurTri animateur;
    private VueVisualisation vueVisualisation;
    private RenderTri render;
    private Stage stage;

    /**
     * Constructeur du contrôleur de visualisation.
     * 
     * @param gestionnaire Le gestionnaire de tri
     * @param stage Le stage JavaFX
     */
    public ControleurVisualisation(GestionnaireTri gestionnaire, Stage stage) {
        this.gestionnaire = gestionnaire;
        this.stage = stage;
    }

    /**
     * Initialise le contrôleur et sa vue.
     */
    public void initialiser() {
        vueVisualisation = new VueVisualisation(stage, this);
        Canvas canvas = vueVisualisation.getCanvas();
        render = creerRender(canvas);
        animateur = new AnimateurTri(render);
        
        // Configurer le callback pour mettre à jour l'affichage de l'étape
        animateur.setCallbackEtape(etape -> {
            if (etape != null) {
                vueVisualisation.mettreAJourEtape(etape.getDescription());
            }
        });
    }

    /**
     * Démarre l'animation avec les étapes fournies.
     * 
     * @param etapes Les étapes du tri
     * @param vitesse La vitesse d'animation
     */
    public void demarrerAnimation(List<EtapeTri> etapes, double vitesse) {
        animateur.setEtapes(etapes);
        animateur.setVitesse(vitesse);
        vueVisualisation.afficher();

        // Afficher la première étape
        if (!etapes.isEmpty()) {
            render.dessiner(etapes.get(0));
            vueVisualisation.mettreAJourEtape(etapes.get(0).getDescription());
        }

        // Activer les boutons appropriés
        vueVisualisation.activerBoutonJouer(true);
        vueVisualisation.activerBoutonPause(false);
    }

    /**
     * Lance la lecture de l'animation.
     */
    public void jouer() {
        if (animateur.estEnPause()) {
            animateur.reprendre();
        } else {
            animateur.demarrer();
        }

        vueVisualisation.activerBoutonJouer(false);
        vueVisualisation.activerBoutonPause(true);

        // Mettre à jour l'affichage de l'étape courante
        mettreAJourAffichageEtape();
    }

    /**
     * Met l'animation en pause.
     */
    public void pause() {
        animateur.pause();
        vueVisualisation.activerBoutonJouer(true);
        vueVisualisation.activerBoutonPause(false);
    }

    /**
     * Arrête l'animation.
     */
    public void arreter() {
        animateur.arreter();
        vueVisualisation.activerBoutonJouer(true);
        vueVisualisation.activerBoutonPause(false);
        vueVisualisation.mettreAJourEtape("Animation arrêtée");
    }

    /**
     * Réinitialise l'animation au début.
     */
    public void reinitialiser() {
        animateur.reinitialiser();
        vueVisualisation.activerBoutonJouer(true);
        vueVisualisation.activerBoutonPause(false);
        vueVisualisation.mettreAJourEtape("Réinitialisé - Prêt à démarrer");
    }

    /**
     * Met à jour l'affichage de l'étape courante.
     */
    private void mettreAJourAffichageEtape() {
        EtapeTri etape = animateur.getEtapeCourante();
        if (etape != null) {
            vueVisualisation.mettreAJourEtape(etape.getDescription());
        }
    }

    /**
     * Crée le moteur de rendu.
     * 
     * @param canvas Le canvas sur lequel dessiner
     * @return Le moteur de rendu
     */
    private RenderTri creerRender(Canvas canvas) {
        return new RenderBarres(canvas);
    }
}
