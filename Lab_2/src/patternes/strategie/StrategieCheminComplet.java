package patternes.strategie;

import patternes.composant.Dessin;
import patternes.composant.Forme;

class StrategieCheminComplet implements StrategieAffichage {
    public void afficherDessin(Dessin dessin, String chemin, int niveau) {
        String cheminComplet = chemin.isEmpty() ? dessin.getNom() : chemin + "." + dessin.getNom();
        System.out.println(cheminComplet);
    }

    public void afficherForme(Forme forme, String chemin, int niveau) {
        String cheminComplet = chemin + "." + forme.getNom();
        System.out.println(cheminComplet);
    }
}
