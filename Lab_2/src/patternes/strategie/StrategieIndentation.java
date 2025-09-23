package patternes.strategie;

import patternes.composant.Dessin;
import patternes.composant.Forme;

class StrategieIndentation implements StrategieAffichage {
    private int niveauMax;

    public StrategieIndentation(int niveauMax) {
        this.niveauMax = niveauMax;
    }

    public void afficherDessin(Dessin dessin, String chemin, int niveau) {
        if (niveauMax == -1 || niveau <= niveauMax) {
            String indentation = genererIndentation(niveau);
            System.out.println(indentation + dessin.getNom());
        }
    }

    public void afficherForme(Forme forme, String chemin, int niveau) {
        if (niveauMax == -1 || niveau <= niveauMax) {
            String indentation = genererIndentation(niveau);
            System.out.println(indentation + forme.getNom());
        }
    }

    private String genererIndentation(int niveau) {
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < niveau; i++) {
            indentation.append("    ");
        }
        return indentation.toString();
    }
}
