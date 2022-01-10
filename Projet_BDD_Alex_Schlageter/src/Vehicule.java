public class Vehicule extends Entity {
    private int immatriculation;
    private String dateMiseEnCirculation;
    private String etat;
    private int nbKilometres;
    private int prixParJourDeLocation;
    private Marque marque;
    private Modele modele;
    private Categorie categorie;
    private Type type;
    private Agence agence;

    public Vehicule(){
        this(0);
    }
    public Vehicule(int immatriculation) {
        this.immatriculation = immatriculation;
    }

    public Vehicule(int immatriculation, String dateMiseEnCirculation, String etat, int nbKilometres, int prixParJourDeLocation, Marque marque, Modele modele, Categorie categorie, Type type, Agence agence) {
        this.immatriculation = immatriculation;
        this.dateMiseEnCirculation = dateMiseEnCirculation;
        this.etat = etat;
        this.nbKilometres = nbKilometres;
        this.prixParJourDeLocation = prixParJourDeLocation;
        this.marque = marque;
        this.modele = modele;
        this.categorie = categorie;
        this.type = type;
        this.agence = agence;
    }

    public int getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(int immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getDateMiseEnCirculation() {
        return dateMiseEnCirculation;
    }

    public void setDateMiseEnCirculation(String dateMiseEnCirculation) {
        this.dateMiseEnCirculation = dateMiseEnCirculation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getNbKilometres() {
        return nbKilometres;
    }

    public void setNbKilometres(int nbKilometres) {
        this.nbKilometres = nbKilometres;
    }

    public int getPrixParJourDeLocation() {
        return prixParJourDeLocation;
    }

    public void setPrixParJourDeLocation(int prixParJourDeLocation) {
        this.prixParJourDeLocation = prixParJourDeLocation;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele modele) {
        this.modele = modele;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "immatriculation=" + immatriculation +
                ", dateMiseEnCirculation='" + dateMiseEnCirculation + '\'' +
                ", etat='" + etat + '\'' +
                ", nbKilometres=" + nbKilometres +
                ", prixParJourDeLocation=" + prixParJourDeLocation +
                ", marque=" + marque +
                ", modele=" + modele +
                ", categorie=" + categorie +
                ", type=" + type +
                ", agence=" + agence +
                '}';
    }
}