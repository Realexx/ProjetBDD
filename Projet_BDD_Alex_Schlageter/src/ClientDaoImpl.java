import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ClientDaoImpl extends JdbcDao {
    private VilleDaoImpl villeDao;

    public ClientDaoImpl(Connection connection) {
        super(connection);
        villeDao = new VilleDaoImpl(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> clients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM client");
            while (resultSet.next()) {
                Client client = new Client();
                client.setIdClient(resultSet.getInt("idClient"));
                client.setNomClient(resultSet.getString("nomClient"));
                client.setAdresseClient(resultSet.getString("adresseClient"));
                client.setCodePostalClient(resultSet.getString("codePostalClient"));
                Ville ville = (Ville) villeDao.findById(resultSet.getInt("idVille"));
                client.setVille(ville);
                clients.add(client);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return clients;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Client client = null;
        String sqlReq = "SELECT * FROM client WHERE idClient = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                client = new Client();
                client.setIdClient(resultSet.getInt("idClient"));
                client.setNomClient(resultSet.getString("nomClient"));
                client.setAdresseClient(resultSet.getString("adresseClient"));
                client.setCodePostalClient(resultSet.getString("codePostalClient"));
                Ville ville = (Ville)
                        villeDao.findById(resultSet.getInt("idVille"));
                client.setVille(ville);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return client;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Client client = (Client) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into client(idClient, nomClient, adresseClient, codePostalClient) values (?, ?, ?, ?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, client.getIdClient());
            statement.setString(2, client.getNomClient());
            statement.setString(3, client.getAdresseClient());
            statement.setString(4, client.getCodePostalClient());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Client client = (Client) entity;
        PreparedStatement statement = null;
        String sqlReq = "update client set nomClient = ?, adresseClient = ?, codePostalClient = ?, idVille = ? where idClient = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setString(1, client.getNomClient());
            statement.setString(2, client.getAdresseClient());
            statement.setString(3, client.getCodePostalClient());
            statement.setInt(4, client.getVille().getIdVille());
            statement.setInt(5, client.getIdClient());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Client client = (Client) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from client where idClient = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, client.getIdClient());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}