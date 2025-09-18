public class Capteur extends Sujet {
    private double valeur;

    public Capteur() {
        super();
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
        notifierObservateurs();
    }
}
