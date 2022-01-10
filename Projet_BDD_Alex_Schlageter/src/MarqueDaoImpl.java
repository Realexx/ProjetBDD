import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class MarqueDaoImpl extends JdbcDao {

    public MarqueDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> marques = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM marque");
            while (resultSet.next()) {
                Marque marque = new Marque();
                marque.setIdMarque(resultSet.getInt("idMarque"));
                marque.setNom(resultSet.getString("nomMarque"));

                marques.add(marque);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return marques;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Marque marque = null;
        String sqlReq = "SELECT * FROM marque WHERE idMarque = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                marque= new Marque();
                marque.setIdMarque(resultSet.getInt("idMarque"));
                marque.setNom(resultSet.getString("nomMarque"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return marque;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Marque marque = (Marque) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into marque(idMarque, nomMarque) values (?, ?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, marque.getIdMarque());
            statement.setString(2, marque.getNom());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Marque marque = (Marque) entity;
        PreparedStatement statement = null;
        String sqlReq = "update marque set nomMarque = ? where idMarque = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setString(1, marque.getNom());
            statement.setInt(2, marque.getIdMarque());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Marque marque = (Marque) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from marque where idMarque = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, marque.getIdMarque());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}