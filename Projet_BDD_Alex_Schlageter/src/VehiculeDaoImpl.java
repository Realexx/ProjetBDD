import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class VehiculeDaoImpl extends JdbcDao {
    private MarqueDaoImpl marqueDao;
    private ModeleDaoImpl modeleDao;
    private CategorieDaoImpl categorieDao;
    private TypeDaoImpl typeDao;
    private AgenceDaoImpl agenceDao;

    public VehiculeDaoImpl(Connection connection) {
        super(connection);
        marqueDao = new MarqueDaoImpl(connection);
        modeleDao = new ModeleDaoImpl(connection);
        categorieDao = new CategorieDaoImpl(connection);
        typeDao = new TypeDaoImpl(connection);
        agenceDao = new AgenceDaoImpl(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> vehicules = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM vehicule");
            while (resultSet.next()) {
                Vehicule vehicule = new Vehicule();
                vehicule.setImmatriculation(resultSet.getInt("immatriculation"));
                vehicule.setDateMiseEnCirculation(resultSet.getString("dateMiseEnCirculation"));
                vehicule.setEtat(resultSet.getString("etat"));
                vehicule.setNbKilometres(resultSet.getInt("NbKilometres"));
                vehicule.setPrixParJourDeLocation(resultSet.getInt("PrixParJourDeLocation"));

                Marque marque = (Marque) marqueDao.findById(resultSet.getInt("idMarque"));
                vehicule.setMarque(marque);

                Modele modele = (Modele) modeleDao.findById(resultSet.getInt("idModele"));
                vehicule.setModele(modele);

                Categorie categorie = (Categorie) categorieDao.findById(resultSet.getInt("idCategorie"));
                vehicule.setCategorie(categorie);

                Type type = (Type) typeDao.findById(resultSet.getInt("idType"));
                vehicule.setType(type);

                Agence agence = (Agence) agenceDao.findById(resultSet.getInt("idAgence"));
                vehicule.setAgence(agence);

                vehicules.add(vehicule);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return vehicules;
    }

    @Override
    public Entity findById(int immatriculation) throws DaoException {
        Vehicule vehicule = null;
        String sqlReq = "SELECT * FROM vehicule WHERE immatriculation = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, immatriculation);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vehicule = new Vehicule();
                vehicule.setImmatriculation(resultSet.getInt("immatriculation"));
                vehicule.setDateMiseEnCirculation(resultSet.getString("dateMiseEnCirculation"));
                vehicule.setEtat(resultSet.getString("etat"));
                vehicule.setNbKilometres(resultSet.getInt("NbKilometres"));
                vehicule.setPrixParJourDeLocation(resultSet.getInt("PrixParJourDeLocation"));

                Marque marque = (Marque) marqueDao.findById(resultSet.getInt("idMarque"));
                vehicule.setMarque(marque);

                Modele modele = (Modele) modeleDao.findById(resultSet.getInt("idModele"));
                vehicule.setModele(modele);

                Categorie categorie = (Categorie) categorieDao.findById(resultSet.getInt("idCategorie"));
                vehicule.setCategorie(categorie);

                Type type = (Type) typeDao.findById(resultSet.getInt("idType"));
                vehicule.setType(type);

                Agence agence = (Agence) agenceDao.findById(resultSet.getInt("idAgence"));
                vehicule.setAgence(agence);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return vehicule;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Vehicule vehicule = (Vehicule) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into vehicule(immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation) values (?, ?, ?, ?, ?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, vehicule.getImmatriculation());
            statement.setString(2, vehicule.getDateMiseEnCirculation());
            statement.setString(3, vehicule.getEtat());
            statement.setInt(4, vehicule.getNbKilometres());
            statement.setInt(5, vehicule.getPrixParJourDeLocation());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Vehicule vehicule = (Vehicule) entity;
        PreparedStatement statement = null;
        String sqlReq = "update vehicule set dateMiseEnCirculation = ?, etat = ?, nbKilometres = ?, prixParJourDeLocation = ?, idMarque = ?, idModele = ?, idCategorie = ?, idType = ?, idAgence = ? where immatriculation = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setString(1, vehicule.getDateMiseEnCirculation());
            statement.setString(2, vehicule.getEtat());
            statement.setInt(3, vehicule.getNbKilometres());
            statement.setInt(4, vehicule.getPrixParJourDeLocation());
            statement.setInt(5, vehicule.getMarque().getIdMarque());
            statement.setInt(6, vehicule.getModele().getIdModele());
            statement.setInt(7, vehicule.getCategorie().getIdCategorie());
            statement.setInt(8, vehicule.getType().getIdType());
            statement.setInt(9, vehicule.getAgence().getIdAgence());
            statement.setInt(10, vehicule.getImmatriculation());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Vehicule vehicule = (Vehicule) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from vehicule where immatriculation = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, vehicule.getImmatriculation());
            int res = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}