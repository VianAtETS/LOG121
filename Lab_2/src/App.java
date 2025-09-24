import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import patternes.composant.*;
import patternes.strategie.*;

public class App {
    public static void main(String[] args) {
        List<ComposantDessin> dessins = new ArrayList<>();

        // Dessin avec une seule forme
        Dessin dessinSimple = new Dessin("Dessin1");
        Forme formeSeule = new Forme("etoile");

        dessins.add(dessinSimple);
        dessinSimple.ajouter(formeSeule);

        // Dessin avec deux formes seulement
        Dessin dessinRelativementSimple = new Dessin("Dessin1");
        Carre carre = new Carre("Carre");
        Forme losange = new Forme("Losange");

        dessins.add(dessinRelativementSimple);
        dessinRelativementSimple.ajouter(carre);
        dessinRelativementSimple.ajouter(losange);

        // Dessin avec trois niveaux
        Dessin dessinComplexe = new Dessin("Dessin1");
        Dessin dessin2 = new Dessin("Dessin2");
        Dessin dessin3 = new Dessin("Dessin3");
        Dessin dessin4 = new Dessin("Dessin4");
        Cercle cercle1 = new Cercle("Cercle1");
        Cercle cercle2 = new Cercle("Cercle2");
        Cercle cercle3 = new Cercle("Cercle3");
        Rectangle rectangle1 = new Rectangle("Rectangle1");
        Triangle triangle1 = new Triangle("Triangle1");
        Triangle triangle2 = new Triangle("Triangle2");

        dessins.add(dessinComplexe);
        dessinComplexe.ajouter(cercle1);
        dessinComplexe.ajouter(dessin2);
        dessin2.ajouter(dessin3);
        dessin2.ajouter(dessin4);
        dessin2.ajouter(rectangle1);
        dessin3.ajouter(cercle2);
        dessin3.ajouter(cercle3);
        dessin4.ajouter(triangle1);
        dessin4.ajouter(triangle2);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Entrez le dessin à tester :");
            System.out.println("1- Dessin avec un niveau (une forme seulement)");
            System.out.println("2- Dessin avec deux niveaux");
            System.out.println("3- Dessin avec trois niveaux");
            System.out.print("Choisissez un dessin (1-3) : ");
            int choixDessin = scanner.nextInt();

            System.out.println("Entrez la strategie d'affichage à utiliser :");
            System.out.println("1- Indentation");
            System.out.println("2- Chemin complet");
            System.out.println("3- Affichage graphique (non implémenté)");
            System.out.print("Choisissez une strategie (1-3) : ");
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
            } else if (choixStrategie == 3) {
                System.out.println(
                        "Affichage graphique non implémenté, utilisation de la strategie par defaut (Indentation).");
                strategie = new StrategieIndentation(-1);
            } else {
                System.out.println("Choix de strategie invalide.");
                continue;
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
