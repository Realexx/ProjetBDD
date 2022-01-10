public class Marque extends Entity {
    private int idMarque;
    private String nomMarque;
    public Marque() {
        this(0);
    }
    public Marque(int id) {
        this(id, null);
    }
    public Marque(int id, String nom) {
        super();
        this.idMarque = id;
        this.nomMarque = nom;
    }
    public int getIdMarque() {
        return idMarque;
    }
    public void setIdMarque(int id) {
        this.idMarque = id;
    }
    public String getNom() {
        return nomMarque;
    }
    public void setNom(String nom) {
        this.nomMarque = nom;
    }

    @Override
    public String toString() {
        return "Marque{" +
                "idMarque=" + idMarque +
                ", nomMarque='" + nomMarque + '\'' +
                '}';
    }
}
