public class CapteurTemperature extends Capteur {
    private double temperature_celsius;

    public CapteurTemperature() {
        super();
    }

    public double getValeur() {
        return temperature_celsius;
    }

    public void setValeur(double valeur) {
        this.temperature_celsius = valeur;
        super.setValeur(temperature_celsius);
    }

    public String toString() {
        return String.format("mesure de température : %.1f °C", temperature_celsius);
    }
}
