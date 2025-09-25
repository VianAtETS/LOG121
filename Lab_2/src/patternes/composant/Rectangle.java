package patternes.composant;

public class Rectangle extends Forme {
    private Double longueur = null;
    private Double largeur = null;

    public Rectangle(String nom) {
        super(nom);
    }

    public Rectangle(String nom, Double longueur, Double largeur) {
        super(nom);
        this.longueur = longueur;
        this.largeur = largeur;
    }

    public Double getLongueur() {
        return longueur;
    }

    public Double getLargeur() {
        return largeur;
    }
}
