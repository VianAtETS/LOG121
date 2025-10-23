package project;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestionnaire central pour l'exécution des algorithmes de tri. Implémente ObservateurTri pour
 * collecter toutes les étapes du tri. Coordonne la création et l'exécution des algorithmes de tri.
 */
public class GestionnaireTri implements ObservateurTri {
    private AlgorithmeTri algorithme;
    private List<EtapeTri> etapes;
    private List<Integer> collectionInitiale;

    /**
     * Constructeur du gestionnaire de tri.
     */
    public GestionnaireTri() {
        this.etapes = new ArrayList<>();
    }

    /**
     * Définit la collection d'entiers à trier.
     *
     * @param collection La collection à trier
     */
    public void setCollection(List<Integer> collection) {
        this.collectionInitiale = new ArrayList<>(collection);
    }

    /**
     * Définit le type d'algorithme à utiliser.
     *
     * @param type Le type d'algorithme (QUICK_SORT ou MERGE_SORT)
     */
    public void setAlgorithme(TypeAlgorithme type) {
        this.algorithme = creerAlgorithme(type);
    }

    /**
     * Exécute l'algorithme de tri et retourne la liste des étapes.
     *
     * @return La liste des étapes du tri
     */
    public List<EtapeTri> executerTri() {
        etapes.clear();

        if (algorithme != null) {
            algorithme.setObservateur(this);
            algorithme.executer();
        }

        return new ArrayList<>(etapes);
    }

    /**
     * Retourne la liste des étapes du tri.
     *
     * @return La liste des étapes
     */
    public List<EtapeTri> getEtapes() {
        return new ArrayList<>(etapes);
    }

    /**
     * Implémentation de ObservateurTri. Collecte une nouvelle étape du tri.
     *
     * @param etape L'étape à ajouter
     */
    @Override
    public void notifierNouvelleEtape(EtapeTri etape) {
        etapes.add(etape);
    }

    /**
     * Implémentation de ObservateurTri. Appelé lorsque le tri est terminé.
     */
    @Override
    public void notifierFinTri() {
        System.out.println("Tri terminé avec " + etapes.size() + " étapes.");
        // Afficher l'avant et après tri pour vérification
        if (!etapes.isEmpty()) {
            EtapeTri premiereEtape = etapes.get(0);
            System.out.println("Avant tri : " + premiereEtape.getCollectionState());
        }

        if (!etapes.isEmpty()) {
            EtapeTri derniereEtape = etapes.get(etapes.size() - 1);
            System.out.println("Après tri : " + derniereEtape.getCollectionState());
        }
    }

    /**
     * Fabrique pour créer l'algorithme approprié selon le type.
     *
     * @param type Le type d'algorithme à créer
     * @return L'instance de l'algorithme
     */
    private AlgorithmeTri creerAlgorithme(TypeAlgorithme type) {
        if (collectionInitiale == null) {
            throw new IllegalStateException(
                    "La collection doit être définie avant de créer l'algorithme.");
        }

        switch (type) {
            case QUICK_SORT:
                return new QuickSort(collectionInitiale);
            case MERGE_SORT:
                return new MergeSort(collectionInitiale);
            default:
                throw new IllegalArgumentException("Type d'algorithme non supporté: " + type);
        }
    }
}
