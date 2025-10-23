package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implémentation de l'algorithme Merge Sort. Utilise le patron Méthode Template défini dans
 * AlgorithmeTri.
 *
 * Le Merge Sort divise la collection en deux moitiés égales, les trie récursivement, puis fusionne
 * les deux moitiés triées.
 */
public class MergeSort extends AlgorithmeTri {

    /**
     * Constructeur du Merge Sort.
     * 
     * @param collection La collection d'entiers à trier
     */
    public MergeSort(List<Integer> collection) {
        super(collection);
    }

    /**
     * Méthode récursive de tri Merge Sort. Divise la liste en deux moitiés, trie chaque moitié
     * récursivement, puis fusionne les deux moitiés triées.
     *
     * @param liste La liste à trier
     * @param debut L'indice de début de la section à trier
     * @param fin L'indice de fin de la section à trier
     */
    @Override
    protected void trierRecursif(List<Integer> liste, int debut, int fin) {
        if (debut < fin) {
            // Calculer le milieu
            int milieu = (debut + fin) / 2;

            // Notifier la division
            Map<String, Integer> indices = new HashMap<>();
            indices.put("debut", debut);
            indices.put("milieu", milieu);
            indices.put("fin", fin);
            notifierEtape("Division [" + debut + "..." + milieu + "] et [" + (milieu + 1) + "..."
                    + fin + "]", indices);

            // Trier récursivement les deux moitiés
            trierRecursif(liste, debut, milieu);
            trierRecursif(liste, milieu + 1, fin);

            // Fusionner les deux moitiés triées
            fusionner(liste, debut, milieu, fin);

            // Notifier la fusion
            notifierEtape("Fusion [" + debut + "..." + milieu + "] et [" + (milieu + 1) + "..."
                    + fin + "]", indices);
        }
    }

    /**
     * Fusionne deux sous-listes triées en une seule liste triée.
     *
     * @param liste La liste contenant les deux sous-listes
     * @param debut L'indice de début de la première sous-liste
     * @param milieu L'indice de fin de la première sous-liste
     * @param fin L'indice de fin de la deuxième sous-liste
     */
    private void fusionner(List<Integer> liste, int debut, int milieu, int fin) {
        // Créer des copies des deux sous-listes
        List<Integer> gauche = new ArrayList<>();
        List<Integer> droite = new ArrayList<>();

        for (int i = debut; i <= milieu; i++) {
            gauche.add(liste.get(i));
        }

        for (int i = milieu + 1; i <= fin; i++) {
            droite.add(liste.get(i));
        }

        // Fusionner les deux sous-listes
        int i = 0; // Indice pour gauche
        int j = 0; // Indice pour droite
        int k = debut; // Indice pour la liste principale

        // Comparer et fusionner élément par élément
        while (i < gauche.size() && j < droite.size()) {
            if (gauche.get(i) <= droite.get(j)) {
                liste.set(k, gauche.get(i));
                i++;
            } else {
                liste.set(k, droite.get(j));
                j++;
            }
            k++;
        }

        // Copier les éléments restants de gauche (s'il y en a)
        while (i < gauche.size()) {
            liste.set(k, gauche.get(i));
            i++;
            k++;
        }

        // Copier les éléments restants de droite (s'il y en a)
        while (j < droite.size()) {
            liste.set(k, droite.get(j));
            j++;
            k++;
        }
    }
}
