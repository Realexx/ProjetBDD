public class Type extends Entity {
    private int idType;
    private String libelleType;

    public Type() {
        this(0);
    }
    public Type(int id) {
        this(id, null);
    }
    public Type(int id, String nom) {
        super();
        this.idType = id;
        this.libelleType = nom;
    }
    public int getIdType() {
        return idType;
    }
    public void setIdType(int id) {
        this.idType = id;
    }

    public String getLibelleType() {
        return libelleType;
    }
    public void setLibelleType(String libelleType) {
        this.libelleType = libelleType;
    }

    @Override
    public String toString() {
        return "Type{" +
                "idType=" + idType +
                ", libelleType='" + libelleType + '\'' +
                '}';
    }
}
