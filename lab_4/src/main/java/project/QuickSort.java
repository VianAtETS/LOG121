package project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implémentation de l'algorithme Quick Sort. Utilise le patron Méthode Template défini dans
 * AlgorithmeTri.
 *
 * Le Quick Sort divise la collection en choisissant un pivot et en partitionnant les éléments
 * autour de ce pivot (plus petits à gauche, plus grands à droite).
 */
public class QuickSort extends AlgorithmeTri {

    /**
     * Constructeur du Quick Sort.
     * 
     * @param collection La collection d'entiers à trier
     */
    public QuickSort(List<Integer> collection) {
        super(collection);
    }

    /**
     * Méthode récursive de tri Quick Sort. Divise la liste en deux parties autour d'un pivot et
     * trie récursivement chaque partie.
     *
     * @param liste La liste à trier
     * @param debut L'indice de début de la section à trier
     * @param fin L'indice de fin de la section à trier
     */
    @Override
    protected void trierRecursif(List<Integer> liste, int debut, int fin) {
        if (debut < fin) {
            // Partitionner et obtenir l'indice du pivot
            int pivotIndex = partitionner(liste, debut, fin);

            // Notifier l'étape de partitionnement
            Map<String, Integer> indices = new HashMap<>();
            indices.put("pivot", pivotIndex);
            notifierEtape("Partitionnement autour du pivot à l'indice " + pivotIndex, indices);

            // Trier récursivement les deux sous-parties
            trierRecursif(liste, debut, pivotIndex - 1);
            trierRecursif(liste, pivotIndex + 1, fin);
        }
    }

    /**
     * Partitionne la liste autour d'un pivot. Place le pivot à sa position finale et organise les
     * éléments: - À gauche: éléments plus petits que le pivot - À droite: éléments plus grands que
     * le pivot
     *
     * @param liste La liste à partitionner
     * @param debut L'indice de début
     * @param fin L'indice de fin
     * @return L'indice final du pivot
     */
    private int partitionner(List<Integer> liste, int debut, int fin) {
        // Choisir le pivot (dernier élément par défaut)
        int pivotIndex = choisirPivot(debut, fin);
        int pivot = liste.get(pivotIndex);

        // Placer le pivot à la fin temporairement
        echanger(liste, pivotIndex, fin);

        int i = debut;

        // Parcourir la liste et réorganiser autour du pivot
        for (int j = debut; j < fin; j++) {
            if (liste.get(j) < pivot) {
                echanger(liste, i, j);

                // Notifier l'échange
                Map<String, Integer> indices = new HashMap<>();
                indices.put("i", i);
                indices.put("j", j);
                notifierEtape("Échange des éléments aux indices " + i + " et " + j, indices);

                i++;
            }
        }

        // Placer le pivot à sa position finale
        echanger(liste, i, fin);
        return i;
    }

    /**
     * Choisit l'indice du pivot. Par défaut, choisit le dernier élément.
     *
     * @param debut L'indice de début
     * @param fin L'indice de fin
     * @return L'indice du pivot choisi
     */
    private int choisirPivot(int debut, int fin) {
        return fin; // Stratégie simple: dernier élément
    }

    /**
     * Échange deux éléments dans la liste.
     *
     * @param liste La liste contenant les éléments
     * @param i Le premier indice
     * @param j Le deuxième indice
     */
    private void echanger(List<Integer> liste, int i, int j) {
        int temp = liste.get(i);
        liste.set(i, liste.get(j));
        liste.set(j, temp);
    }
}
