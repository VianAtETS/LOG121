import java.util.List;
import java.util.ArrayList;

public abstract class Sujet {
    private List<Observateur> observateurs;

    public Sujet() {
        this.observateurs = new ArrayList<>();
    }

    public void ajouterObservateur(Observateur observateur) {
        observateurs.add(observateur);
    }

    public void supprimerObservateur(Observateur observateur) {
        observateurs.remove(observateur);
    }

    public void notifierObservateurs() {
        for (Observateur observateur : observateurs) {
            observateur.mettreAJour();
        }
    }
}
