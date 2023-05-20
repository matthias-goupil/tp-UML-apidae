package controleur;

import metier.I_Produit;
import metier.Produit;

import java.util.ArrayList;
import java.util.List;

public class ControleurVenteAchat extends ControleurCatalogue{

    public static String[] demandeVente() {
        return catalogueSelectionne.getNomProduits();
    }

    public static String[] demandeAchat() {
        return getCatalogueSelectionne().getNomProduits();
    }

    public static boolean achatProduit(String nomProduit, int quantite) {
        if(getCatalogueSelectionne().acheterStock(nomProduit, quantite)) {
            System.out.println(catalogueSelectionne.getProduit(nomProduit));
            return productDAO.update(catalogueSelectionne.getProduit(nomProduit));
        }
        return false;
    }

    public static boolean venteProduit(String nomProduit, int quantite) {
        if(getCatalogueSelectionne().vendreStock(nomProduit, quantite)) {
            return productDAO.update(catalogueSelectionne.getProduit(nomProduit));
        }
        return false;
    }
}
