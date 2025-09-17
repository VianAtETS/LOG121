import java.util.Scanner;

public class Simulation {
    public static void main(String[] args) throws Exception {
        CapteurTemperature capteurTemp = new CapteurTemperature();
        CapteurCO2 capteurCO2 = new CapteurCO2();

        Controleur controleur = new Controleur();
        capteurTemp.ajouterObservateur(controleur);
        capteurCO2.ajouterObservateur(controleur);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Entrez la nouvelle température (°C) : ");
            double tempValue = scanner.nextDouble();
            capteurTemp.setValeur(tempValue);

            System.out.print("Entrez la nouvelle concentration de CO2 (ppm) : ");
            double co2Value = scanner.nextDouble();
            capteurCO2.setValeur(co2Value);

            System.out.println("Voulez-vous continuer ? (oui/non) : ");
            String reponse = scanner.next();
            if (!reponse.equalsIgnoreCase("oui")) {
                break;
            }
        }

        scanner.close();
    }
}
