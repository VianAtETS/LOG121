package project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classe abstraite implémentant le patron Méthode Template. Définit le squelette des algorithmes de
 * tri et laisse les sous-classes implémenter la logique spécifique de tri.
 */
public abstract class AlgorithmeTri {
    protected List<Integer> collection;
    protected ObservateurTri observateur;

    /**
     * Constructeur de l'algorithme de tri.
     *
     * @param collection La collection d'entiers à trier
     */
    public AlgorithmeTri(List<Integer> collection) {
        this.collection = new ArrayList<>(collection);
    }

    /**
     * Définit l'observateur qui sera notifié des étapes du tri.
     *
     * @param observateur L'observateur à notifier
     */
    public void setObservateur(ObservateurTri observateur) {
        this.observateur = observateur;
    }

    /**
     * MÉTHODE TEMPLATE Exécute l'algorithme de tri en suivant un processus défini: 1. Copier la
     * collection 2. Notifier l'état initial 3. Trier récursivement 4. Notifier la fin du tri
     */
    public void executer() {
        // Notifier l'état initial
        notifierEtape("État initial", null);
        
        // Trier la collection en place
        trierRecursif(collection, 0, collection.size() - 1);
        
        // Notifier la fin du tri
        notifierEtape("Tri terminé", null);
        if (observateur != null) {
            observateur.notifierFinTri();
        }
    }

    /**
     * Notifie l'observateur d'une nouvelle étape.
     *
     * @param description Description de l'étape
     * @param indices Indices importants pour cette étape
     */
    protected void notifierEtape(String description, Map<String, Integer> indices) {
        if (observateur != null) {
            EtapeTri etape = new EtapeTri(description, collection, indices);
            observateur.notifierNouvelleEtape(etape);
        }
    }

    /**
     * Crée une copie de la collection.
     *
     * @return Une copie de la collection
     */
    protected List<Integer> copierCollection() {
        return new ArrayList<>(collection);
    }

    /**
     * Méthode abstraite à implémenter par les sous-classes. Contient la logique spécifique de tri
     * récursif.
     *
     * @param liste La liste à trier
     * @param debut L'indice de début
     * @param fin L'indice de fin
     */
    protected abstract void trierRecursif(List<Integer> liste, int debut, int fin);
}
