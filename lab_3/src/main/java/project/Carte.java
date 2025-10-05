package project;

public abstract class Carte {
    protected String numero;

    public Carte(String numero) {
        this.numero = numero;
    }

    public boolean valider() {
        return validerNumero();
    }

    protected boolean validerNumero() {
        return numero != null && numero.matches("\\d{16}");
    }
}
