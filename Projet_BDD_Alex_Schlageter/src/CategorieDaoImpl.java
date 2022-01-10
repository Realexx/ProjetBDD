import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CategorieDaoImpl extends JdbcDao {

    public CategorieDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> categories = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM categorie");
            while (resultSet.next()) {
                Categorie categorie = new Categorie();
                categorie.setIdCategorie(resultSet.getInt("idCategorie"));
                categorie.setLibelleCategorie(resultSet.getString("libelleCategorie"));

                categories.add(categorie);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return categories;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Categorie categorie = null;
        String sqlReq = "SELECT * FROM categorie WHERE idCategorie = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                categorie= new Categorie();
                categorie.setIdCategorie(resultSet.getInt("idCategorie"));
                categorie.setLibelleCategorie(resultSet.getString("libelleCategorie"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categorie;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Categorie categorie = (Categorie) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into categorie(idCategorie, libelleCategorie) values (?, ?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, categorie.getIdCategorie());
            statement.setString(2, categorie.getLibelleCategorie());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Categorie categorie = (Categorie) entity;
        PreparedStatement statement = null;
        String sqlReq = "update categorie set libelleCategorie = ? where idCategorie = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setString(1, categorie.getLibelleCategorie());
            statement.setInt(2, categorie.getIdCategorie());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Categorie categorie = (Categorie) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from categorie where idCategorie = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, categorie.getIdCategorie());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}