import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class FactureDaoImpl extends JdbcDao{
    private ContratDaoImpl contratDao;

    public FactureDaoImpl(Connection connection) {
        super(connection);
        contratDao = new ContratDaoImpl(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> factures = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM facture");
            while (resultSet.next()) {
                Facture facture = new Facture();
                facture.setIdFacture(resultSet.getInt("idFacture"));
                facture.setMontant(resultSet.getInt("montant"));
                Contrat contrat = (Contrat) contratDao.findById(resultSet.getInt("idContrat"));
                facture.setContrat(contrat);
                factures.add(contrat);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return factures;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Facture facture = null;
        String sqlReq = "SELECT * FROM facture WHERE idFacture = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                facture = new Facture();
                facture.setIdFacture(resultSet.getInt("idFacture"));
                facture.setMontant(resultSet.getInt("montant"));
                Contrat contrat = (Contrat) contratDao.findById(resultSet.getInt("idContrat"));
                facture.setContrat(contrat);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return facture;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Facture facture = (Facture) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into facture(idFacture, montant) values (?, ?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, facture.getIdFacture());
            statement.setInt(2, facture.getMontant());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Facture facture = (Facture) entity;
        PreparedStatement statement = null;
        String sqlReq = "update facture set montant = ?, idContrat = ? where idFacture = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, facture.getMontant());
            statement.setInt(2, facture.getContrat().getIdContrat());
            statement.setInt(3, facture.getIdFacture());

            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Facture facture = (Facture) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from facture where idFacture = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, facture.getIdFacture());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Entity findChiffreAffaire(int idagence, int moisDateDeRetour) throws DaoException {
        Facture facture = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT SUM(Facture.montant) AS ChiffreAffaire\n" +
                    "FROM Facture\n" +
                    "INNER JOIN Contrat ON Contrat.idContrat = Facture.idContrat\n" +
                    "INNER JOIN Agence ON Agence.idAgence = Contrat.idagencederetour\n" +
                    "WHERE contrat.idagencederetour = " + idagence + " AND EXTRACT(MONTH FROM dateDeRetour)= " + moisDateDeRetour);
            while (resultSet.next()) {
                facture = new Facture();
                facture.setMontant(resultSet.getInt("ChiffreAffaire"));

            }
        } catch (SQLException e){
            throw new DaoException(e);
        }

        System.out.println(facture);
        return facture;
    }

}
