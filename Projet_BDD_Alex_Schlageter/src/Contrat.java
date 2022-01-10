public class Contrat extends Entity {
    private int idContrat;
    private String dateDeRetrait;
    private String dateDeRetour;
    private int kmRetrait;
    private Client client;
    private Vehicule vehicule;
    private Agence agence;

    public Contrat() {
    }

    public Contrat(int idContrat, String dateDeRetrait, String dateDeRetour, int kmRetrait, Client client, Vehicule vehicule, Agence agence) {
        this.idContrat = idContrat;
        this.dateDeRetrait = dateDeRetrait;
        this.dateDeRetour = dateDeRetour;
        this.kmRetrait = kmRetrait;
        this.client = client;
        this.vehicule = vehicule;
        this.agence = agence;
    }

    public int getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(int idContrat) {
        this.idContrat = idContrat;
    }

    public String getDateDeRetrait() {
        return dateDeRetrait;
    }

    public void setDateDeRetrait(String dateDeRetrait) {
        this.dateDeRetrait = dateDeRetrait;
    }

    public String getDateDeRetour() {
        return dateDeRetour;
    }

    public void setDateDeRetour(String dateDeRetour) {
        this.dateDeRetour = dateDeRetour;
    }

    public int getKmRetrait() {
        return kmRetrait;
    }

    public void setKmRetrait(int kmRetrait) {
        this.kmRetrait = kmRetrait;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    @Override
    public String toString() {
        return "Contrat{" +
                "idContrat=" + idContrat +
                ", dateDeRetrait='" + dateDeRetrait + '\'' +
                ", dateDeRetour='" + dateDeRetour + '\'' +
                ", kmRetrait=" + kmRetrait +
                ", client=" + client +
                ", vehicule=" + vehicule +
                ", agence=" + agence +
                '}';
    }
}