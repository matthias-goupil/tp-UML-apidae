package controleur;

import dal.DAOFactory;
import dal.ProductDAO;
import dal.SQL.SQLFactory;
import metier.Catalogue;
import metier.I_Catalogue;

import java.util.ArrayList;

public abstract class ControleurCatalogue {
    protected static ProductDAO productDAO = new SQLFactory().getProductDAO();

    protected static I_Catalogue cat = new Catalogue("NomCatalogue", productDAO.readAll());

    private static ArrayList<I_Catalogue> catalogues = new ArrayList<>();
    private static Catalogue catalogueSelectionne;

    public static Catalogue getCatalogueSelectionne() {
        return catalogueSelectionne;
    }

    public static boolean ajouterCatalogue(String nomCatalogue) {
        return catalogues.add(new Catalogue(nomCatalogue));
    }

    public static boolean supprimerCatalogue(String nomCatalogue) {
        int index = -1;
        for(int i = 0; i < catalogues.size(); i++) {
            Catalogue catalogue = (Catalogue) catalogues.get(i);
            if(catalogue.getNom().equals(nomCatalogue)) {
                index = i;
            }
        }
        return catalogues.remove(index) != null;
    }

    public static void selectectionnerCatalogue(String nomCatalogue) {
        for(int i = 0; i < catalogues.size(); i++) {
            Catalogue catalogue = (Catalogue) catalogues.get(i);
            if(catalogue.getNom().equals(nomCatalogue)) {
                catalogueSelectionne = catalogue;
            }
        }
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
            nomCatalogues[i] = ((Catalogue) catalogues.get(i)).getNom() + " : " + catalogues.get(i).getNomProduits().length + " produits";
        return nomCatalogues;
    }

}
