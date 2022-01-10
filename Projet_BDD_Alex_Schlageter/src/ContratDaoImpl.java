import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ContratDaoImpl extends JdbcDao {
    private ClientDaoImpl clientDao;
    private VehiculeDaoImpl vehiculeDao;
    private AgenceDaoImpl agenceDao;

    public ContratDaoImpl(Connection connection) {
        super(connection);
        clientDao = new ClientDaoImpl(connection);
        vehiculeDao = new VehiculeDaoImpl(connection);
        agenceDao = new AgenceDaoImpl(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> contrats = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM contrat");
            while (resultSet.next()) {
                Contrat contrat = new Contrat();
                contrat.setIdContrat(resultSet.getInt("idContrat"));
                contrat.setDateDeRetrait(resultSet.getString("dateDeRetrait"));
                contrat.setDateDeRetour(resultSet.getString("dateDeRetour"));
                contrat.setKmRetrait(resultSet.getInt("kmRetrait"));

                Client client = (Client) clientDao.findById(resultSet.getInt("idClient"));
                contrat.setClient(client);

                Vehicule vehicule = (Vehicule) vehiculeDao.findById(resultSet.getInt("idVehicule"));
                contrat.setVehicule(vehicule);

                Agence agence = (Agence) agenceDao.findById(resultSet.getInt("idAgence"));
                contrat.setAgence(agence);

                contrats.add(contrat);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return contrats;
    }

    @Override
    public Entity findById(int idContrat) throws DaoException {
        Contrat contrat = null;
        String sqlReq = "SELECT * FROM contrat WHERE idContrat = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, idContrat);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                contrat = new Contrat();
                contrat.setIdContrat(resultSet.getInt("idcontrat"));
                contrat.setDateDeRetrait(resultSet.getString("datederetrait"));
                contrat.setDateDeRetour(resultSet.getString("datederetour"));
                contrat.setKmRetrait(resultSet.getInt("kmRetrait"));

                Client client = (Client) clientDao.findById(resultSet.getInt("idclient"));
                contrat.setClient(client);

                Vehicule vehicule = (Vehicule) vehiculeDao.findById(resultSet.getInt("immatriculation"));
                contrat.setVehicule(vehicule);

                Agence agence = (Agence) agenceDao.findById(resultSet.getInt("idagencederetour"));
                contrat.setAgence(agence);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contrat;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Contrat contrat = (Contrat) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into contrat(idContrat, dateDeRetrait, dateDeRetour, kmRetrait) values (?, ?, ?, ?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, contrat.getIdContrat());
            statement.setString(2, contrat.getDateDeRetrait());
            statement.setString(3, contrat.getDateDeRetour());
            statement.setInt(4, contrat.getKmRetrait());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Contrat contrat = (Contrat) entity;
        PreparedStatement statement = null;
        String sqlReq = "update contrat set dateDeRetrait = ?, dateDeRetour = ?, kmRetrait = ?, idClient = ?, immatriculation = ?, idagencederetour = ? where idContrat = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setString(1, contrat.getDateDeRetrait());
            statement.setString(2, contrat.getDateDeRetour());
            statement.setInt(3, contrat.getKmRetrait());
            statement.setInt(4, contrat.getClient().getIdClient());
            statement.setInt(5, contrat.getVehicule().getImmatriculation());
            statement.setInt(6, contrat.getAgence().getIdAgence());
            statement.setInt(7, contrat.getIdContrat());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Contrat contrat = (Contrat) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from contrat where idContrat = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, contrat.getIdContrat());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Entity locationVoiture(int idclient, String dateRetrait, String dateRetour) throws DaoException {
        Contrat contrat = null;
        Client client = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Client.nomClient, contrat.datederetrait, Contrat.datederetour\n" +
                    "FROM Contrat\n" +
                    "INNER JOIN Client ON Contrat.idClient = Client.idClient\n" +
                    "INNER JOIN Vehicule ON Contrat.immatriculation = Vehicule.immatriculation\n" +
                    "WHERE Client.idclient = " + idclient + "\n" +
                    "ORDER BY Contrat.idContrat");
            while (resultSet.next()) {
                contrat = new Contrat();
                client = new Client(idclient);
                contrat.setClient(client);
                contrat.setDateDeRetrait(resultSet.getString("dateDeRetrait"));
                contrat.setDateDeRetour(resultSet.getString("dateDeRetour"));

            }
        } catch (SQLException e){
            throw new DaoException(e);
        }

        System.out.println(contrat);
        return contrat;
    }

}