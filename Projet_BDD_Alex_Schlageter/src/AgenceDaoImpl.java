import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class AgenceDaoImpl extends JdbcDao {
    private final VilleDaoImpl villeDao;

    public AgenceDaoImpl(Connection connection) {
        super(connection);
        villeDao = new VilleDaoImpl(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> agences = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM agence");
            while (resultSet.next()) {
                Agence agence = new Agence();
                agence.setIdAgence(resultSet.getInt("idAgence"));
                agence.setNbEmployes(resultSet.getInt("nbEmployes"));
                Ville ville = (Ville) villeDao.findById(resultSet.getInt("idVille"));
                agence.setVille(ville);
                agences.add(ville);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return agences;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Agence agence = null;
        String sqlReq = "SELECT * FROM agence WHERE idAgence = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                agence = new Agence();
                agence.setIdAgence(resultSet.getInt("idAgence"));
                agence.setNbEmployes(resultSet.getInt("nbEmployes"));
                Ville ville = (Ville)
                        villeDao.findById(resultSet.getInt("idVille"));
                agence.setVille(ville);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return agence;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Agence agence = (Agence) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into agence(idAgence, nbEmployes) values (?, ?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, agence.getIdAgence());
            statement.setInt(2, agence.getNbEmploye());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Agence agence = (Agence) entity;
        PreparedStatement statement = null;
        String sqlReq = "update agence set nbEmployes = ?, idVille = ? where idAgence = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, agence.getNbEmploye());
            statement.setInt(2, agence.getVille().getIdVille());
            statement.setInt(3, agence.getIdAgence());

            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Agence agence = (Agence) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from agence where idAgence = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, agence.getIdAgence());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}