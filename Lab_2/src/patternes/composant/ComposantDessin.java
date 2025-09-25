package patternes.composant;

import patternes.strategie.StrategieAffichage;

public interface ComposantDessin {
    void afficher(StrategieAffichage strategie, String chemin, int niveau);

    String getNom();
}
