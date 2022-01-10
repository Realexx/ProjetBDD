public class Categorie extends Entity {
    private int idCategorie;
    private String libelleCategorie;

    public Categorie() {
        this(0);
    }
    public Categorie(int id) {
        this(id, null);
    }
    public Categorie(int id, String nom) {
        super();
        this.idCategorie = id;
        this.libelleCategorie = nom;
    }
    public int getIdCategorie() {
        return idCategorie;
    }
    public void setIdCategorie(int id) {
        this.idCategorie = id;
    }

    public String getLibelleCategorie() {
        return libelleCategorie;
    }
    public void setLibelleCategorie(String libelleCategorie) {
        this.libelleCategorie = libelleCategorie;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "idCategorie=" + idCategorie +
                ", libelleCategorie='" + libelleCategorie + '\'' +
                '}';
    }
}
