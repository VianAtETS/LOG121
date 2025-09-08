package Cours_1.Exercice_2;

public class Employe extends Personne {
    private String poste;
    private double salaire;

    public Employe(String nom, int age, String poste, double salaire) {
        super(nom, age);
        this.poste = poste;
        this.salaire = salaire;
    }

    public String getPoste() {
        return poste;
    }

    public double getSalaire() {
        return salaire;
    }
}
