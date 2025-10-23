package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Représente une étape dans le processus de tri. Contient l'état de la collection, une description
 * et les indices importants.
 */
public class EtapeTri {
    private final String description;
    private final List<Integer> collectionState;
    private final Map<String, Integer> indices;
    private final long timestamp;

    /**
     * Constructeur d'une étape de tri.
     * 
     * @param description Description de l'étape
     * @param collection État actuel de la collection
     * @param indices Indices importants pour cette étape (pivot, i, j, etc.)
     */
    public EtapeTri(String description, List<Integer> collection, Map<String, Integer> indices) {
        this.description = description;
        this.collectionState = new ArrayList<>(collection);
        this.indices = indices != null ? new HashMap<>(indices) : new HashMap<>();
        this.timestamp = System.currentTimeMillis();
    }

    public String getDescription() {
        return description;
    }

    public List<Integer> getCollectionState() {
        return new ArrayList<>(collectionState);
    }

    public Map<String, Integer> getIndices() {
        return new HashMap<>(indices);
    }

    public long getTimestamp() {
        return timestamp;
    }
}
