package dal;

import metier.I_Produit;
import metier.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private Connection connection;

    public ProductDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create(I_Produit p) {
        try {
            String query = "INSERT INTO produits (nom, prix, quantite) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, p.getNom());
            statement.setDouble(2, p.getPrixUnitaireHT());
            statement.setInt(3, p.getQuantite());
            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public I_Produit read(String nom) {
        try {
            String query = "SELECT * FROM produits WHERE nom = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nom);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String produitNom = resultSet.getString("nom");
                double produitPrix = resultSet.getDouble("prix");
                int produitQuantite = resultSet.getInt("quantite");
                I_Produit produit = new Produit(produitNom, produitPrix, produitQuantite);

                return produit;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<I_Produit> readAll() {
        ArrayList<I_Produit> produits = new ArrayList<>();

        try {
            String query = "SELECT * FROM produits";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String produitNom = resultSet.getString("nom");
                double produitPrix = resultSet.getDouble("prix");
                int produitQuantite = resultSet.getInt("quantite");
                I_Produit produit = new Produit(produitNom, produitPrix, produitQuantite);

                produits.add(produit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produits;
    }

    @Override
    public boolean update(I_Produit p) {
        try {
            String query = "UPDATE produits SET prix = ?, quantite = ? WHERE nom = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1, p.getPrixUnitaireHT());
            statement.setInt(2, p.getQuantite());
            statement.setString(3, p.getNom());
            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(String nom) {
        try {
            String query = "DELETE FROM produits WHERE nom = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nom);
            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
