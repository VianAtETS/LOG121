import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import patternes.composant.*;
import patternes.strategie.*;

public class App {
    public static void main(String[] args) {
        List<ComposantDessin> dessins = new ArrayList<>();

        // Dessin avec trois niveaux
        Dessin dessin1 = new Dessin("Dessin1");
        Cercle cercle1 = new Cercle("Cercle1");
        Dessin dessin2 = new Dessin("Dessin2");
        Dessin dessin3 = new Dessin("Dessin3");
        Cercle cercle2 = new Cercle("Cercle2");
        Cercle cercle3 = new Cercle("Cercle3");
        Dessin dessin4 = new Dessin("Dessin4");
        Triangle triangle1 = new Triangle("Triangle1");
        Triangle triangle2 = new Triangle("Triangle2");
        Rectangle rectangle1 = new Rectangle("Rectangle1");

        dessin3.ajouter(cercle2);
        dessin3.ajouter(cercle3);
        dessin4.ajouter(triangle1);
        dessin4.ajouter(triangle2);
        dessin2.ajouter(dessin3);
        dessin2.ajouter(dessin4);
        dessin2.ajouter(rectangle1);
        dessin1.ajouter(cercle1);
        dessin1.ajouter(dessin2);

        // Dessin avec deux formes seulement
        Dessin dessinSimple = new Dessin("DessinSimple");
        Carre carre = new Carre("Carre");
        Forme losange = new Forme("Losange");
        dessinSimple.ajouter(carre);
        dessinSimple.ajouter(losange);

        // Dessin avec une seule forme
        Forme formeSeule = new Forme("etoile");

        dessins.add(dessin1);
        dessins.add(dessinSimple);
        dessins.add(formeSeule);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Entrez le dessin à tester :");
            System.out.println("1- Dessin avec trois niveaux");
            System.out.println("2- Dessin avec deux niveaux");
            System.out.println("3- Dessin avec un niveau (une forme seulement)");
            System.out.print("Choisissez un dessin (1-3) : ");
            int choixDessin = scanner.nextInt();

            System.out.println("Entrez la strategie d'affichage à utiliser :");
            System.out.println("1- Indentation");
            System.out.println("2- Chemin complet");
            System.out.print("Choisissez une strategie (1-2) : ");
            int choixStrategie = scanner.nextInt();

            int niveauMax = -1;
            if (choixStrategie == 1) {
                System.out.print("Nombre de niveaux à afficher (-1 pour tous) : ");
                niveauMax = scanner.nextInt();
            }

            StrategieAffichage strategie = null;
            if (choixStrategie == 1) {
                strategie = new StrategieIndentation(niveauMax);
            } else if (choixStrategie == 2) {
                strategie = new StrategieCheminComplet();
            }

            if (choixDessin >= 1 && choixDessin <= 3) {
                System.out.println("Affichage du dessin :");
                ComposantDessin composant = dessins.get(choixDessin - 1);
                composant.afficher(strategie, "", 0);
            }

            System.out.print("Continuer? (o/n) : ");
            String reponse = scanner.next();
            if (!reponse.equals("o")) {
                break;
            }
        }

        scanner.close();
    }
}
