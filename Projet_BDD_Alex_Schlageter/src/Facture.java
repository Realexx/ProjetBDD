public class Facture extends Entity{
    private int idFacture;
    private int montant;
    private Contrat contrat;

    public Facture() {
        this(0);
    }
    public Facture(int id) {
        this(id, 0);
    }
    public Facture(int id, int montant) {
        this(id, montant, null);
    }
    public Facture(int id, int montant, Contrat contrat) {
        super();
        this.idFacture = id;
        this.montant = montant;
        this.contrat = contrat;
    }
    public int getIdFacture() {
        return idFacture;
    }
    public void setIdFacture(int id) {
        this.idFacture = id;
    }
    public int getMontant() {
        return montant;
    }
    public void setMontant(int montant) {
        this.montant = montant;
    }
    public Contrat getContrat() {
        return contrat;
    }
    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "idFacture=" + idFacture +
                ", montant=" + montant +
                ", contrat=" + contrat +
                '}';
    }
}
