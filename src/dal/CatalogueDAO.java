package dal;

import metier.I_Catalogue;

import java.util.List;

public interface CatalogueDAO {
    boolean create(I_Catalogue catalogue);
    I_Catalogue read(String nom);
    List<I_Catalogue> readAll();
    boolean update(I_Catalogue catalogue);
    boolean delete(String nom);
}