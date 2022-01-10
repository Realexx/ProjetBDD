import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class VilleDaoImpl extends JdbcDao {

    public VilleDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> villes = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ville");
            while (resultSet.next()) {
                Ville ville = new Ville();
                ville.setIdVille(resultSet.getInt("idVille"));
                ville.setNom(resultSet.getString("nomVille"));
                ville.setNombreHabitants(resultSet.getInt("nombreHabitants"));
                villes.add(ville);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return villes;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Ville ville = null;
        String sqlReq = "SELECT * FROM ville WHERE idVille = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ville = new Ville();
                ville.setIdVille(resultSet.getInt("idVille"));
                ville.setNom(resultSet.getString("nomVille"));
                ville.setNombreHabitants(resultSet.getInt("nombreHabitants"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ville;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Ville ville = (Ville) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into ville(idVille, nomVille, nombreHabitants) values (?, ?, ?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, ville.getIdVille());
            statement.setString(2, ville.getNom());
            statement.setInt(3, ville.getNombreHabitants());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Ville ville = (Ville) entity;
        PreparedStatement statement = null;
        String sqlReq = "update ville set nomVille = ?, nombreHabitants = ? where idVille = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setString(1, ville.getNom());
            statement.setInt(2, ville.getNombreHabitants());
            statement.setInt(3, ville.getIdVille());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Ville ville = (Ville) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from ville where idVille = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, ville.getIdVille());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}