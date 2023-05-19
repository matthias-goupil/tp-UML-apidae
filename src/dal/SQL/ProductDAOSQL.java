package dal.SQL;

import dal.ProductDAO;
import metier.I_Produit;
import metier.Produit;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAOSQL implements ProductDAO {
    private final String url = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
    private String login = "goupilm";
    private String psw = "04102002";
    protected Connection cn;
    private String tableName = "Produits";

    private void connection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            cn = DriverManager.getConnection(url, login, psw);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean create(I_Produit p) {
        connection();
        String sql = "INSERT INTO " + tableName + "(quantiteStock, nom, prixUnitaireHT) VALUES(?,?,?)";
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
    public I_Produit read(String nom) {
        connection();
        String sql = "SELECT * FROM "+tableName+" WHERE nom = ?";
        PreparedStatement pst = null;
        try {
            pst = cn.prepareStatement(sql);
            pst.setString(1, nom);

            ResultSet rs = pst.executeQuery();
            if(rs.next())
                return new Produit(rs.getString("nom"),rs.getDouble("prixUnitaireHT"),rs.getInt("quantiteStock"));
            return null;
        } catch (SQLException e){
            System.out.println("Erreur récupération produit");
            return null;
        }
    }
    @Override
    public ArrayList<I_Produit> readAll() {
        connection();
        String sql = "SELECT * FROM "+tableName;
        ArrayList<I_Produit> produits = new ArrayList<>();
        PreparedStatement pst = null;
        try {
            pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
                produits.add(new Produit(rs.getString("nom"),rs.getDouble("prixUnitaireHT"),rs.getInt("quantiteStock")));
            return produits;
        } catch (SQLException e){
            System.out.println("Erreur récupération liste product");
            return null;
        }
    }
    @Override
    public boolean update(I_Produit p) {
        connection();
        String sql = "UPDATE "+tableName+" SET quantiteStock = ?, prixUnitaireHT = ? WHERE nom = ?";

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
    public boolean delete(String nom) {
        connection();
        String sql = "DELETE FROM "+tableName+" WHERE nom = ?";

        PreparedStatement pst = null;
        try {
            pst = cn.prepareStatement(sql);
            pst.setString(1,nom);

            return (pst.executeUpdate() > 0);
        } catch (SQLException e){
            System.out.println("Erreur suppression de produit");
            return false;
        }
    }
}
