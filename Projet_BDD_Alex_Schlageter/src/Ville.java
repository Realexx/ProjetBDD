public class Ville extends Entity {
    private int idVille;
    private String nomVille;
    private int nombreHabitants;

    public Ville() {
        this(0);
    }
    public Ville(int id) {
        this(id, null);
    }
    public Ville(int id, String nomVille) {
        this(id, nomVille, 0);
    }
    public Ville(int id, String nomVille, int nombreHabitants) {
        super();
        this.idVille = id;
        this.nomVille = nomVille;
        this.nombreHabitants = nombreHabitants;
    }
    public int getIdVille() {
        return idVille;
    }
    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }
    public String getNom() {
        return nomVille;
    }
    public void setNom(String nomVille) {
        this.nomVille = nomVille;
    }
    public int getNombreHabitants() {
        return nombreHabitants;
    }
    public void setNombreHabitants(int nombreHabitants) {
        this.nombreHabitants = nombreHabitants;
    }

    @Override
    public String toString() {
        return "Ville{" +
                "idVille=" + idVille +
                ", nomVille='" + nomVille + '\'' +
                ", nombreHabitants=" + nombreHabitants +
                "}\n";
    }
}