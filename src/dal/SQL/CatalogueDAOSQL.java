package dal.SQL;

import dal.CatalogueDAO;
import metier.Catalogue;
import metier.I_Catalogue;
import metier.I_Produit;
import metier.Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogueDAOSQL implements CatalogueDAO {
    private Connection connection;

    public CatalogueDAOSQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(I_Catalogue catalogue) {
        String query = "INSERT INTO Catalogues (nomCatalogue) VALUES (?)";

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
    public I_Catalogue read(String nom) {
        String query = "SELECT * FROM Catalogues WHERE NomCatalogue = ? ";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nom);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nomCatalogue = resultSet.getString("nomCatalogue");
                I_Catalogue catalogue = new Catalogue(nomCatalogue);
                catalogue.setId(resultSet.getInt("ID"));
                return catalogue;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<I_Catalogue> readAll() {
        String query = "SELECT * FROM Catalogues";
        ArrayList<I_Catalogue> catalogues = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nom = resultSet.getString("nomCatalogue");
                Catalogue catalogue = new Catalogue(nom);
                catalogue.setId(resultSet.getInt("ID"));
                catalogues.add(catalogue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return catalogues;
    }

    @Override
    public boolean update(String newNom, String nomActuel) {
        String query = "UPDATE Catalogues SET nomCatalogue = ? WHERE nomCatalogue = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newNom);
            statement.setString(2, nomActuel);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String nom) {
        String query = "DELETE FROM Catalogues WHERE nomCatalogue = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nom);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addProduit(I_Catalogue catalogue, I_Produit produit) {
        String query = "INSERT INTO Produits (IDCatalogue, NomProduit, QuantiteStock, prixUnitaireHT) VALUES (?, ?, ?, ?)";

        System.out.println(catalogue.getId());
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, catalogue.getId());
            statement.setString(2, produit.getNom());
            statement.setInt(3, produit.getQuantite());
            statement.setDouble(4, produit.getPrixUnitaireHT());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeProduit(I_Catalogue catalogue, String nomProduit) {
        String query = "DELETE FROM Produits WHERE IDCatalogue = ? AND NomProduit = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, catalogue.getId());
            statement.setString(2, nomProduit);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public List<I_Produit> getProduitsByCatalogue(I_Catalogue catalogue) {
        String query = "SELECT * FROM Produits WHERE IDCatalogue = ?";
        List<I_Produit> produits = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, catalogue.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nomProduit = resultSet.getString("NomProduit");
                int quantiteStock = resultSet.getInt("QuantiteStock");
                double prixUnitaireHT = resultSet.getDouble("prixUnitaireHT");
                I_Produit produit = new Produit(nomProduit, prixUnitaireHT, quantiteStock);
                produits.add(produit);
            }
            catalogue.addProduits(produits);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produits;
    }

    @Override
    public int getNombreProduitsByCatalogue(I_Catalogue catalogue) {
        String query = "SELECT COUNT(*) FROM Produits WHERE IDCatalogue = ?";
        int nombreProduits = 0;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, catalogue.getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                nombreProduits = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreProduits;
    }
}
