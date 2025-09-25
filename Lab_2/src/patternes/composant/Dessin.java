package patternes.composant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import patternes.iterateur.IterateurDessin;
import patternes.strategie.StrategieAffichage;

public class Dessin implements ComposantDessin {
    private String nom;
    private List<ComposantDessin> enfants;

    public Dessin(String nom) {
        this.nom = nom;
        this.enfants = new ArrayList<>();
    }

    public void ajouter(ComposantDessin composant) {
        enfants.add(composant);
    }

    public void supprimer(ComposantDessin composant) {
        enfants.remove(composant);
    }

    public void afficher(StrategieAffichage strategie, String chemin, int niveau) {
        strategie.afficherDessin(this, chemin, niveau);

        Iterator<ComposantDessin> it = getIterateur();
        while (it.hasNext()) {
            ComposantDessin enfant = it.next();
            String nouveauChemin = chemin.isEmpty() ? nom : chemin + "." + nom;
            enfant.afficher(strategie, nouveauChemin, niveau + 1);
        }
    }

    public String getNom() {
        return nom;
    }

    public Iterator<ComposantDessin> getIterateur() {
        return new IterateurDessin(enfants);
    }

    public List<ComposantDessin> getEnfants() {
        return enfants;
    }
}
