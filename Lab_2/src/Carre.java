import patternes.composant.Forme;

public class Carre extends Forme {
    private Double cote = null;

    public Carre(String nom, Double cote) {
        super(nom);
        this.cote = cote;
    }

    public Double getCote() {
        return cote;
    }
}
