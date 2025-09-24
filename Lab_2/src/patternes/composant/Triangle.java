package patternes.composant;

public class Triangle extends Forme {
    private Double base = null;
    private Double hauteur = null;

    public Triangle(String nom) {
        super(nom);
    }

    public Triangle(String nom, Double base, Double hauteur) {
        super(nom);
        this.base = base;
        this.hauteur = hauteur;
    }

    public Double getBase() {
        return base;
    }

    public Double getHauteur() {
        return hauteur;
    }
}
