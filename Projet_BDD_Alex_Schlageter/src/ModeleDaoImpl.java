import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ModeleDaoImpl extends JdbcDao {

    public ModeleDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> modeles = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM modele");
            while (resultSet.next()) {
                Modele modele = new Modele();
                modele.setIdModele(resultSet.getInt("idModele"));
                modele.setDenomination(resultSet.getString("denomination"));
                modele.setPuissanceFiscale(resultSet.getInt("puissanceFiscale"));
                modeles.add(modele);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return modeles;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Modele modele = null;
        String sqlReq = "SELECT * FROM modele WHERE idModele = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                modele= new Modele();
                modele.setIdModele(resultSet.getInt("idModele"));
                modele.setDenomination(resultSet.getString("denomination"));
                modele.setPuissanceFiscale(resultSet.getInt("puissanceFiscale"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return modele;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Modele modele = (Modele) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into modele(idModele, denomination, puissanceFiscale) values (?, ?, ?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, modele.getIdModele());
            statement.setString(2, modele.getDenomination());
            statement.setInt(3, modele.getPuissanceFiscale());

            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Modele modele = (Modele) entity;
        PreparedStatement statement = null;
        String sqlReq = "update modele set denomination = ?, puissanceFiscale = ? where idModele = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setString(1, modele.getDenomination());
            statement.setInt(2, modele.getPuissanceFiscale());
            statement.setInt(3, modele.getIdModele());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Modele modele = (Modele) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from modele where idModele = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, modele.getIdModele());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}