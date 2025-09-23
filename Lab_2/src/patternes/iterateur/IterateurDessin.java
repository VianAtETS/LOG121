package patternes.iterateur;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class IterateurDessin implements Iterator<ComposantDessin> {
    private List<ComposantDessin> elements;
    private int position;

    public IterateurDessin(List<ComposantDessin> elements) {
        this.elements = elements;
        this.position = 0;
    }

    public boolean hasNext() {
        return position < elements.size();
    }

    public ComposantDessin next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Aucun element suivant");
        }
        ComposantDessin element = elements.get(position);
        position++;
        return element;
    }
}
