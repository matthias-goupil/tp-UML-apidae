package dal.XML;

import dal.ProductDAO;
import metier.I_Produit;
import metier.Produit;

import java.util.ArrayList;

public class ProductDAOXML implements ProductDAO {
    @Override
    public boolean create(I_Produit p) {
        return false;
    }

    @Override
    public I_Produit read(String nom) {
        return null;
    }

    @Override
    public ArrayList<I_Produit> readAll() {
        return null;
    }

    @Override
    public boolean update(I_Produit p) {
        return false;
    }

    @Override
    public boolean delete(String nom) {
        return false;
    }
}
