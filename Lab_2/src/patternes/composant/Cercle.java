package patternes.composant;

public class Cercle extends Forme {
    private Double rayon = null;

    public Cercle(String nom) {
        super(nom);
    }

    public Cercle(String nom, Double rayon) {
        super(nom);
        this.rayon = rayon;
    }

    public Double getRayon() {
        return rayon;
    }
}
