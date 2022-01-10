import java.sql.*;
import java.util.Properties;

public class Test {
    // Connexion à la bdd
    static Connection connexion() throws SQLException {
        String url = "jdbc:postgresql://localhost/postgres";
        Properties props = new Properties();
        props.setProperty("user","aschlag2");
        props.setProperty("password","2204");
        Connection conn = DriverManager.getConnection(url, props);
        return conn;
    }

    // Requête 1
    public static void locationVoiture() throws SQLException {
        connexion();
        Statement statement = connexion().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT Client.nomClient, contrat.datederetrait, Contrat.dateDeRetour\n" +
                "FROM Contrat\n" +
                "INNER JOIN Client ON Contrat.idClient = Client.idClient\n" +
                "WHERE Client.nomClient = 'Schlageter'\n" +
                "ORDER BY Contrat.idContrat;");
        while (resultSet.next()) {
            String nomClient= resultSet.getString("nomClient");
            String datederetrait= resultSet.getString("datederetrait");
            String dateDeRetour= resultSet.getString("dateDeRetour");

            System.out.println("------------------------------\nLa location d’une voiture par un client donné : \n\tnom du client : "+nomClient+"\n\tdate de retrait : "+datederetrait+"\n\tdate de retour : "+dateDeRetour);
        }
        System.out.println("______________________________");
        connexion().close();
    }

    // R2
    public static void retourVehicule() throws SQLException {
        connexion();
        Statement statement = connexion().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT Vehicule.etat, Vehicule.nbKilometres, Contrat.idAgenceDeRetour, Contrat.dateDeRetour\n" +
                "FROM Contrat\n" +
                "INNER JOIN Vehicule ON Contrat.immatriculation = Vehicule.immatriculation\n" +
                "WHERE Contrat.idContrat = 0\n" +
                "ORDER BY Contrat.idContrat;");
        while (resultSet.next()) {
            String etat = resultSet.getString("etat");
            int nbKilometres = resultSet.getInt("nbKilometres");
            int idAgenceDeRetour = resultSet.getInt("idAgenceDeRetour");
            String dateDeRetour = resultSet.getString("dateDeRetour");
            System.out.println("------------------------------\nLe retour du véhicule loué précédemment : \n\tEtat : "+ etat +" \n\tNombre de Kilomètres : "+nbKilometres+"\n\tid de l'Agence : "+idAgenceDeRetour+"\n\tDate de retour : "+dateDeRetour);
        }
        System.out.println("______________________________");
        connexion().close();
    }

    // R3
    public static void facture() throws SQLException {
        connexion();
        Statement statement = connexion().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT Facture.montant, Contrat.idContrat\n" +
                "FROM Facture\n" +
                "INNER JOIN Contrat ON Facture.idContrat = Contrat.idContrat\n" +
                "WHERE Contrat.idContrat = 0\n" +
                "ORDER BY Facture.idFacture;");
        while (resultSet.next()) {
            int montant = resultSet.getInt("montant");
            int idContrat = resultSet.getInt("idContrat");
            System.out.println("------------------------------\nL’établissement de la facture pour la location précédente : \n\tMontant : " +montant + "€ \n\tid du Contrat : "+idContrat);
        }
        System.out.println("______________________________");
        connexion().close();
    }

    // R4
    public static void findChiffreAffaire(int idagence, int moisDateDeRetour) throws SQLException {
        connexion();
        Statement statement = connexion().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT SUM(Facture.montant) AS chiffreAffaire\n" +
                "FROM Facture\n" +
                "INNER JOIN Contrat ON Contrat.idContrat = Facture.idContrat\n" +
                "INNER JOIN Agence ON Agence.idAgence = Contrat.idagencederetour\n" +
                "WHERE contrat.idagencederetour = 0 AND EXTRACT(MONTH FROM dateDeRetour)=4;");
        while (resultSet.next()) {
            int chiffreAffaire = resultSet.getInt("chiffreAffaire");
            System.out.println("------------------------------\nLe chiffre d’affaire d’une agence donnée pour un mois donné : " +chiffreAffaire + " €");
        }
        System.out.println("______________________________");
        connexion().close();
    }

    // R5
    public static void nbVehiculesPourChaqueMarque() throws SQLException {
        connexion();
        Statement statement = connexion().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT Marque.nomMarque, COUNT(Vehicule.immatriculation) as nbVehicules " +
                                                    "FROM Vehicule " +
                                                    "INNER JOIN Marque ON Marque.idMarque = Vehicule.idMarque " +
                                                    "GROUP BY Marque.nomMarque;");
        System.out.println("Le nombre de véhicules pour chaque marque :");
        while (resultSet.next()) {
            String nomMarque = resultSet.getString("nomMarque");
            int nbVehicules = resultSet.getInt("nbVehicules");
            System.out.println("------------------------------\n\tNom de la marque : "+ nomMarque + "\n\tNombre de véhicules : " + nbVehicules);
        }
        System.out.println("______________________________");
        connexion().close();
    }

    // R6
    public static void clientPlusDeLocations(int idAgence, String mois) throws SQLException {
        connexion();
        Statement statement = connexion().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT Client.nomClient, COUNT(Contrat.idContrat) AS nbLocations\n" +
                "FROM Contrat\n" +
                "INNER JOIN Client ON Client.idClient = Contrat.idClient\n" +
                "WHERE Contrat.idagencederetour = 0 AND EXTRACT(MONTH FROM Contrat.dateDeRetour)=04\n" +
                "GROUP BY CLient.nomCLient\n" +
                "ORDER BY COUNT(Contrat.idContrat) DESC;");
        while (resultSet.next()) {
            String nomClient = resultSet.getString("nomClient");
            int nbLocations = resultSet.getInt("nbLocations");
            System.out.println("------------------------------\nLe(s) client(s) ayant réalisé(s) le plus de locations pour une agence donnée et pour une année donnée. : \n\tNom du client : "+nomClient+" \n\tNombre de locations : "+nbLocations);
        }
        System.out.println("______________________________");
        connexion().close();
    }

    // R7
    public static void chiffreDaffaireParCategorie() throws SQLException {
        connexion();
        Statement statement = connexion().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT Categorie.libelleCategorie, SUM(Facture.montant) AS ChiffreAffaire\n" +
                "FROM Facture\n" +
                "INNER JOIN Contrat ON Contrat.idContrat = Facture.idContrat\n" +
                "INNER JOIN Vehicule ON Vehicule.immatriculation = Contrat.immatriculation\n" +
                "INNER JOIN Categorie ON Categorie.idCategorie = Vehicule.idCategorie\n" +
                "GROUP BY Categorie.idCategorie;");
        while (resultSet.next()) {
            String libelleCategorie = resultSet.getString("libelleCategorie");
            int ChiffreAffaire = resultSet.getInt("ChiffreAffaire");
            System.out.println("------------------------------\nLe chiffre d’affaire par catégorie : \n\tLibelle catégorie : "+libelleCategorie+"\n\tChiffre d'affaire : "+ChiffreAffaire);
        }
        System.out.println("______________________________");
        connexion().close();
    }

    // R8
    public static void chiffreDaffaireParType() throws SQLException {
        connexion();
        Statement statement = connexion().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT Type.libelleType, SUM(Facture.montant) AS ChiffreAffaire\n" +
                "FROM Facture\n" +
                "INNER JOIN Contrat ON Contrat.idContrat = Facture.idContrat\n" +
                "INNER JOIN Vehicule ON Vehicule.immatriculation = Contrat.immatriculation\n" +
                "INNER JOIN Type ON Type.idType = Vehicule.idType\n" +
                "GROUP BY Type.idType;");
        while (resultSet.next()) {
            String libelleType = resultSet.getString("libelleType");
            int ChiffreAffaire = resultSet.getInt("ChiffreAffaire");
            System.out.println("------------------------------\nLe chiffre d’affaire par type : \n\tLibelle type : "+libelleType+"\n\tChiffre d'affaire : "+ChiffreAffaire);
        }
        System.out.println("______________________________");
        connexion().close();
    }

    // R9
    public static void nbVehiculesPlus2AnsEt150000Km() throws SQLException {
        connexion();
        Statement statement = connexion().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(Vehicule.immatriculation) as nbrVehicule FROM Vehicule WHERE Vehicule.dateMiseEnCirculation > '2009-01-15' AND Vehicule.nbKilometres > 150000;");
        while (resultSet.next()) {
            int nbrVehicule = resultSet.getInt("nbrVehicule");
            System.out.println("------------------------------\nLe nombre de véhicule(s) de plus de 2 ans et de plus de 150 000 km pour chaque agences : " + nbrVehicule);
        }
        System.out.println("______________________________");
        connexion().close();
    }

    // R10
    public static void chiffreAffaireAnneePourChaqueAgence() throws SQLException {
        connexion();
        Statement statement = connexion().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT Agence.idAgence, SUM(Facture.montant) AS ChiffreAffaire\n" +
                "FROM Facture\n" +
                "INNER JOIN Contrat ON Contrat.idContrat = Facture.idContrat\n" +
                "INNER JOIN Agence ON Agence.idAgence = Contrat.idAgenceDeRetour\n" +
                "GROUP BY Agence.idAgence;");
        while (resultSet.next()) {
            int idAgence = resultSet.getInt("idAgence");
            int ChiffreAffaire = resultSet.getInt("ChiffreAffaire");
            System.out.println("------------------------------\nLe chiffre d’affaire par agences : \n\tId de l'agence : "+idAgence+"\n\tChiffre d'affaire : "+ChiffreAffaire);
        }
        System.out.println("______________________________");
        connexion().close();
    }

    public static void main(String[] args) throws DaoException, SQLException {
        // TEST CRUD
        VilleDaoImpl villeDao = new VilleDaoImpl(connexion());
        Ville v1 = new Ville(5, "Paris", 2100000);
        Ville v2 = new Ville(5, "Marseille", 900000);

        System.out.println("Création de Paris : ");
        villeDao.create(v1);
        System.out.println(villeDao.findAll());
        System.out.println(villeDao.findById(5));

        System.out.println("Modification de Paris en Marseille : ");
        villeDao.update(v2);
        System.out.println(villeDao.findById(5));

        System.out.println("Suppression de Marseille : ");
        villeDao.delete(v2);
        System.out.println(villeDao.findAll());

        // Test R1
        // locationVoiture();

        // Test R2
        // retourVehicule();

        // Test R3
        // facture();

        // Test R4
        // findChiffreAffaire(0, 4);

        //Test R5
        nbVehiculesPourChaqueMarque();

        //Test R6
        // clientPlusDeLocations(0, "04");

        //Test R7
        // chiffreDaffaireParCategorie();

        //Test R8
        // chiffreDaffaireParType();

        //Test R9
        // nbVehiculesPlus2AnsEt150000Km();

        //Test R10
        // chiffreAffaireAnneePourChaqueAgence();

    }

}
