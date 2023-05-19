package controleur;

import metier.I_Produit;
import metier.Produit;

import java.util.ArrayList;
import java.util.List;

public class ControleurVenteAchat extends ControleurCatalogue{

    public static String[] demandeVente() {
        return cat.getNomProduits();
    }

    public static String[] demandeAchat() {
        return getCatalogueSelectionne().getNomProduits();
    }

    public static boolean achatProduit(String nomProduit, int quantite) {
        return getCatalogueSelectionne().acheterStock(nomProduit, quantite);
    }

    public static boolean venteProduit(String nomProduit, int quantite) {

        return getCatalogueSelectionne().vendreStock(nomProduit, quantite);
    }
}
