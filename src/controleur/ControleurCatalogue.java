package controleur;

import dal.CatalogueDAO;
import dal.DAOFactory;
import dal.ProductDAO;
import dal.SQL.SQLFactory;
import metier.Catalogue;
import metier.I_Catalogue;

import java.util.ArrayList;

public abstract class ControleurCatalogue {

    private static DAOFactory sqlFactory = new SQLFactory();
    protected static ProductDAO productDAO = sqlFactory.getProductDAO();
    protected static CatalogueDAO catalogueDAO = sqlFactory.getCatalogueDAO();

    private static ArrayList<I_Catalogue> catalogues = catalogueDAO.readAll();
    protected static Catalogue catalogueSelectionne;

    public static Catalogue getCatalogueSelectionne() {
        return catalogueSelectionne;
    }

    public static boolean ajouterCatalogue(String nomCatalogue) {
        I_Catalogue catalogue = new Catalogue(nomCatalogue);
        if(catalogueDAO.create(catalogue)) {
            I_Catalogue newCatalogue = catalogueDAO.read(nomCatalogue);
            return catalogues.add(newCatalogue);
        }
        return false;
    }

    public static boolean supprimerCatalogue(String nomCatalogue) {
        int index = -1;
        for(int i = 0; i < catalogues.size(); i++) {
            Catalogue catalogue = (Catalogue) catalogues.get(i);
            if(catalogue.getNom().equals(nomCatalogue)) {
                index = i;
            }
        }
        if(catalogueDAO.delete(nomCatalogue)) {
            return catalogues.remove(index) != null;
        }
        return false;
    }

    public static void selectectionnerCatalogue(String nomCatalogue) {
        for(int i = 0; i < catalogues.size(); i++) {
            Catalogue catalogue = (Catalogue) catalogues.get(i);
            if(catalogue.getNom().equals(nomCatalogue)) {
                catalogueSelectionne = catalogue;
            }
        }
        catalogueDAO.getProduitsByCatalogue(catalogueSelectionne);
    }

    public static String[] demandeListeCatalogue() {
        String[] nomCatalogues = new String[catalogues.size()];
        for(int i = 0; i < catalogues.size(); i++)
            nomCatalogues[i] = ((Catalogue) catalogues.get(i)).getNom();
        return nomCatalogues;
    }
    public static String[] demandeListeCatalogueAvecNbProduits() {
        String[] nomCatalogues = new String[catalogues.size()];
        for(int i = 0; i < catalogues.size(); i++)
            nomCatalogues[i] = ((Catalogue) catalogues.get(i)).getNom() + " : " + catalogueDAO.getNombreProduitsByCatalogue(catalogues.get(i)) + " produits";
        return nomCatalogues;
    }

}
