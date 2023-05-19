package dal;

import metier.Catalogue;
import metier.I_Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogueDAOImpl implements CatalogueDAO {
    private Connection connection;

    public CatalogueDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(Catalogue catalogue) {
        String query = "INSERT INTO catalogue (nom) VALUES (?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, catalogue.getNom());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Catalogue read(String nom) {
        String query = "SELECT * FROM catalogue WHERE nom = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nom);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                Catalogue catalogue = new Catalogue(id, nom);
                return catalogue;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Catalogue> readAll() {
        String query = "SELECT * FROM catalogue";
        List<Catalogue> catalogues = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                Catalogue catalogue = new Catalogue(id, nom);
                catalogues.add(catalogue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return catalogues;
    }

    @Override
    public boolean update(Catalogue catalogue) {
        String query = "UPDATE catalogue SET nom = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, catalogue.getNom());
            statement.setInt(2, catalogue.getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM catalogue WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addProduit(int catalogueId, I_Produit produit) {
        String query = "INSERT INTO catalogue_produit (catalogue_id, produit_id) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, catalogueId);
            statement.setInt(2, produit.getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeProduit(int catalogueId, I_Produit produit) {
        String query = "DELETE FROM catalogue_produit WHERE catalogue_id = ? AND produit_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, catalogueId);
            statement.setInt(2, produit.getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
