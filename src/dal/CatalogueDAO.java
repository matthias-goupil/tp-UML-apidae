package dal;

import metier.I_Catalogue;
import metier.I_Produit;

import java.util.ArrayList;
import java.util.List;

public interface CatalogueDAO {
    boolean create(I_Catalogue catalogue);
    I_Catalogue read(String nom);
    ArrayList<I_Catalogue> readAll();
    boolean update(String newNom, String nomActuel);
    boolean delete(String nom);
    public boolean addProduit(I_Catalogue catalogue, I_Produit produit);

    public boolean removeProduit(I_Catalogue catalogue, String nomProduit);
    public List<I_Produit> getProduitsByCatalogue(I_Catalogue catalogue);

    public int getNombreProduitsByCatalogue(I_Catalogue catalogue);
}