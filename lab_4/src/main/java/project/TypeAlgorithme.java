package project;

/**
 * Énumération des types d'algorithmes de tri disponibles.
 */
public enum TypeAlgorithme {
    QUICK_SORT("Quick Sort"), MERGE_SORT("Merge Sort");

    private final String nom;

    TypeAlgorithme(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
