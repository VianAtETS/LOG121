package patternes.composant;

public class Carre extends Rectangle {
    private Double cote = null;

    public Carre(String nom) {
        super(nom);
    }

    public Carre(String nom, Double cote) {
        super(nom, cote, cote);
        this.cote = cote;
    }

    public Double getCote() {
        return cote;
    }
}
