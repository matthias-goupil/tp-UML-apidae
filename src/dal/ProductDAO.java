package dal;

import metier.I_Produit;
import metier.Produit;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDAO {
    public boolean create(I_Produit p);
    public I_Produit read(String nom);
    public ArrayList<I_Produit> readAll();
    public boolean update(I_Produit p);
    public boolean delete(String nom);
}
