public class CapteurCO2 extends Capteur {
    private double concentration_ppm;

    public CapteurCO2() {
        super();
    }

    public double getValeur() {
        return concentration_ppm;
    }

    public void setValeur(double valeur) {
        this.concentration_ppm = valeur;
        super.setValeur(concentration_ppm);
    }

    public String toString() {
        return String.format("mesure de CO2 : %.1f ppm", concentration_ppm);
    }
}
