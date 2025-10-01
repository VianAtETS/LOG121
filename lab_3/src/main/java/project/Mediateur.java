package project;

public interface Mediateur {
    void ajouterComposant(Composant composant);

    void notifier(Composant emetteur, String evenement);
}
