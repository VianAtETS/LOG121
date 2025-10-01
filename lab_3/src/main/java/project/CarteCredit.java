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
        return super.valider() && validerDateExpiration() && validerCodeSecurite();
    }

    private boolean validerDateExpiration() {
        return dateExpiration != null && dateExpiration.after(new Date());
    }

    private boolean validerCodeSecurite() {
        return codeSecurite != null && codeSecurite.matches("\\d{3}");
    }
}
