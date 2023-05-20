package dal.SQL;

import dal.ProductDAO;
import metier.I_Produit;
import metier.Produit;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAOSQL implements ProductDAO {
    private String tableName = "Produits";

    private Connection cn;

    public ProductDAOSQL(Connection connection) {
        cn = connection;
    }

    @Override
    public boolean create(I_Produit p) {
        String sql = "INSERT INTO " + tableName + "(QuantiteStock, Nom, prixUnitaireHT) VALUES(?,?,?)";
        PreparedStatement pst = null;
        try {
            pst = cn.prepareStatement(sql);
            pst.setInt(1,p.getQuantite());
            pst.setString(2,p.getNom());
            pst.setDouble(3,p.getPrixUnitaireHT());
            return (pst.executeUpdate() != 0);
        } catch (SQLException e){
            System.out.println("Erreur Création de produit");
            return false;
        }
    }
    @Override
    public I_Produit read(String Nom) {
        String sql = "SELECT * FROM "+tableName+" WHERE Nom = ?";
        PreparedStatement pst = null;
        try {
            pst = cn.prepareStatement(sql);
            pst.setString(1, Nom);

            ResultSet rs = pst.executeQuery();
            if(rs.next())
                return new Produit(rs.getString("Nom"),rs.getDouble("prixUnitaireHT"),rs.getInt("QuantiteStock"));
            return null;
        } catch (SQLException e){
            System.out.println("Erreur récupération produit");
            return null;
        }
    }
    @Override
    public ArrayList<I_Produit> readAll() {
        String sql = "SELECT * FROM "+tableName;
        ArrayList<I_Produit> produits = new ArrayList<>();
        PreparedStatement pst = null;
        try {
            pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
                produits.add(new Produit(rs.getString("Nom"),rs.getDouble("prixUnitaireHT"),rs.getInt("QuantiteStock")));
            return produits;
        } catch (SQLException e){
            System.out.println("Erreur récupération liste product");
            return null;
        }
    }
    @Override
    public boolean update(I_Produit p) {
        String sql = "UPDATE "+tableName+" SET QuantiteStock = ?, prixUnitaireHT = ? WHERE NomProduit = ?";

        PreparedStatement pst = null;
        try {
            pst = cn.prepareStatement(sql);
            pst.setInt(1,p.getQuantite());
            pst.setDouble(2,p.getPrixUnitaireHT());
            pst.setString(3,p.getNom());
            return (pst.executeUpdate() > 0);
        } catch (SQLException e){
            System.out.println("Erreur modification de produit");
            return false;
        }
    }

    @Override
    public boolean delete(String Nom) {
        String sql = "DELETE FROM "+tableName+" WHERE Nom = ?";

        PreparedStatement pst = null;
        try {
            pst = cn.prepareStatement(sql);
            pst.setString(1,Nom);

            return (pst.executeUpdate() > 0);
        } catch (SQLException e){
            System.out.println("Erreur suppression de produit");
            return false;
        }
    }
}
