import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class TypeDaoImpl extends JdbcDao {

    public TypeDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> types = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM type");
            while (resultSet.next()) {
                Type type = new Type();
                type.setIdType(resultSet.getInt("idType"));
                type.setLibelleType(resultSet.getString("libelleType"));
                types.add(type);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return types;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Type type = null;
        String sqlReq = "SELECT * FROM type WHERE idType = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                type= new Type();
                type.setIdType(resultSet.getInt("idType"));
                type.setLibelleType(resultSet.getString("libelleType"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return type;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Type type = (Type) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into type(idType, libelleType) values (?, ?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, type.getIdType());
            statement.setString(2, type.getLibelleType());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Type type = (Type) entity;
        PreparedStatement statement = null;
        String sqlReq = "update type set libelleType = ? where idType = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setString(1, type.getLibelleType());
            statement.setInt(2, type.getIdType());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Type type = (Type) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from type where idType = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, type.getIdType());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}