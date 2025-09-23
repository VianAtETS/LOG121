package patternes.composant;

import patternes.strategie.StrategieAffichage;

interface ComposantDessin {
    void afficher(StrategieAffichage strategie, String chemin, int niveau);

    String getNom();
}
