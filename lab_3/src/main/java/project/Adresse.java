package project;

public class Adresse {
    private String rue;
    private String ville;
    private String codePostal;
    private String pays;
    private String region;

    public Adresse(String rue, String ville, String codePostal, String pays) {
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.pays = pays;
        this.region = null;
    }

    public Adresse(String rue, String ville, String codePostal, String pays, String region) {
        this(rue, ville, codePostal, pays);
        this.region = region;
    }

    public boolean valider() {
        return validerRue() && validerVille() && validerCodePostal() && validerPays()
                && validerRegion();
    }

    private boolean validerRue() {
        return rue != null && !rue.trim().isEmpty();
    }

    private boolean validerVille() {
        return ville != null && !ville.trim().isEmpty();
    }

    private boolean validerCodePostal() {
        return codePostal != null && codePostal.matches("\\d{5}");
    }

    private boolean validerPays() {
        return pays != null && !pays.trim().isEmpty();
    }

    private boolean validerRegion() {
        return region == null || !region.trim().isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(rue).append(", ").append(ville).append(", ").append(codePostal).append(", ")
                .append(pays);
        if (region != null) {
            sb.append(", ").append(region);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Adresse adresse = (Adresse) obj;

        return rue.equals(adresse.rue) && ville.equals(adresse.ville)
                && codePostal.equals(adresse.codePostal) && pays.equals(adresse.pays)
                && (region != null ? region.equals(adresse.region) : adresse.region == null);
    }
}
