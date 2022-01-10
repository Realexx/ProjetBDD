public class Client extends Entity {
    private int idClient;
    private String nomClient;
    private String adresseClient;
    private String codePostalClient;
    private Ville ville;

    public Client() {
        this(0);
    }
    public Client(int id) {
        this(id, null, null, null, null);
    }
    public Client(int id, String nomClient) {
        this(id, nomClient, null, null, null);
    }
    public Client(int id, String nomClient, String adresseClient) {
        super();
        this.idClient = id;
        this.nomClient = nomClient;
        this.adresseClient = adresseClient;
    }
    public Client(int id, String nomClient, String adresseClient, String codePostalClient) {
        super();
        this.idClient = id;
        this.nomClient = nomClient;
        this.adresseClient = adresseClient;
        this.codePostalClient = codePostalClient;
    }
    public Client(int id, String nomClient, String adresseClient, String codePostalClient, Ville ville) {
        super();
        this.idClient = id;
        this.nomClient = nomClient;
        this.adresseClient = adresseClient;
        this.codePostalClient = codePostalClient;
        this.ville = ville;
    }

    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int id) {
        this.idClient = id;
    }

    public String getNomClient() {
        return nomClient;
    }
    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getAdresseClient() {
        return adresseClient;
    }
    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    public String getCodePostalClient() {
        return codePostalClient;
    }
    public void setCodePostalClient(String codePostalClient) {
        this.codePostalClient = codePostalClient;
    }

    public Ville getVille() {
        return ville;
    }
    public void setVille(Ville entreprise) {
        this.ville = entreprise;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", nomClient='" + nomClient + '\'' +
                ", adresseClient='" + adresseClient + '\'' +
                ", codePostalClient='" + codePostalClient + '\'' +
                ", ville=" + ville +
                '}';
    }
}