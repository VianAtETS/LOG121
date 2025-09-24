package patternes.composant;

import patternes.strategie.StrategieAffichage;

public class Forme implements ComposantDessin {
    private String nom;

    public Forme(String nom) {
        this.nom = nom;
    }

    public void afficher(StrategieAffichage strategie, String chemin, int niveau) {
        strategie.afficherForme(this, chemin, niveau);
    }

    public String getNom() {
        return nom;
    }
}
