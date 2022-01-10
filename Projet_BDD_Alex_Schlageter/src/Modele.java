public class Modele extends Entity {
    private int idModele;
    private String denomination;
    private int puissanceFiscale;

    public Modele() {
        this(0);
    }

    public Modele(int idModele) {
        this.idModele = idModele;
    }

    public Modele(int idModele, String denomination, int puissanceFiscale) {
        this.idModele = idModele;
        this.denomination = denomination;
        this.puissanceFiscale = puissanceFiscale;
    }

    public int getIdModele() {
        return idModele;
    }

    public void setIdModele(int idModele) {
        this.idModele = idModele;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public int getPuissanceFiscale() {
        return puissanceFiscale;
    }

    public void setPuissanceFiscale(int puissanceFiscale) {
        this.puissanceFiscale = puissanceFiscale;
    }

    @Override
    public String toString() {
        return "Modele{" +
                "idModele=" + idModele +
                ", denomination='" + denomination + '\'' +
                ", puissanceFiscale=" + puissanceFiscale +
                '}';
    }
}
