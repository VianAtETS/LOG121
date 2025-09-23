package patternes.strategie;

import patternes.composant.Dessin;
import patternes.composant.Forme;

interface StrategieAffichage {
    void afficherDessin(Dessin dessin, String chemin, int niveau);

    void afficherForme(Forme forme, String chemin, int niveau);
}
