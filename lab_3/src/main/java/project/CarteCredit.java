package project;

import java.util.Date;

public class CarteCredit extends Carte {
    private Date dateExpiration;
    private String codeSecurite;

    public CarteCredit(String numero, Date dateExpiration, String codeSecurite) {
        super(numero);
        this.dateExpiration = dateExpiration;
        this.codeSecurite = codeSecurite;
    }

    @Override
    public boolean valider() {
        return validerNumero() && validerDateExpiration() && validerCodeSecurite();
    }

    /**
     * Vérifie le numéro de carte en utilisant l'algorithme de Luhn (mod 10).
     *
     * Principe (en bref) :
     *
     * On parcourt les chiffres de droite à gauche. Pour chaque deuxième chiffre (en partant de la
     * droite), on le double ; si le double est supérieur à 9 on soustrait 9 (équivalent à
     * additionner les deux chiffres du produit). On somme tous les chiffres transformés. Le numéro
     * est valide si la somme est un multiple de 10.
     *
     * Cette méthode conserve la validation de base de la super-classe (format attendu) et applique
     * ensuite la vérification Luhn.
     */
    protected boolean validerNumero() {
        int somme = 0;
        boolean appliquerDouble = false;

        for (int indice = numero.length() - 1; indice >= 0; indice--) {
            int chiffre = Character.getNumericValue(numero.charAt(indice));

            if (appliquerDouble) {
                chiffre *= 2;
                if (chiffre > 9)
                    chiffre -= 9;
            }

            somme += chiffre;
            appliquerDouble = !appliquerDouble;
        }

        return super.validerNumero() && somme % 10 == 0;
    }

    private boolean validerDateExpiration() {
        return dateExpiration != null && dateExpiration.after(new Date());
    }

    private boolean validerCodeSecurite() {
        return codeSecurite != null && codeSecurite.matches("\\d{3}");
    }
}
