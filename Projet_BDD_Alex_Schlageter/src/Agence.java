public class Agence extends Entity {
    private int idAgence;
    private int nbEmployes;
    private Ville ville;

    public Agence() {
        this(0);
    }
    public Agence(int id) {
        this(id, 0);
    }
    public Agence(int id, int nbEmployes) {
        this(id, nbEmployes, null);
    }
    public Agence(int id, int nbEmployes, Ville ville) {
        super();
        this.idAgence = id;
        this.nbEmployes = nbEmployes;
        this.ville = ville;
    }
    public int getIdAgence() {
        return idAgence;
    }
    public void setIdAgence(int id) {
        this.idAgence = id;
    }
    public int getNbEmploye() {
        return nbEmployes;
    }
    public void setNbEmployes(int nbEmployes) {
        this.nbEmployes = nbEmployes;
    }
    public Ville getVille() {
        return ville;
    }
    public void setVille(Ville ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Agence{" +
                "idAgence=" + idAgence +
                ", nbEmployes=" + nbEmployes +
                ", ville=" + ville +
                '}';
    }
}

