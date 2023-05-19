package controleur;

import metier.Produit;

public class ControleurNouveauSuppression extends ControleurCatalogue {

    public static boolean nouveauProduit(String nom, double prix, int qte) {
        Produit produit = new Produit(nom, prix, qte);
        if(productDAO.create(produit))
            return getCatalogueSelectionne().addProduit(nom, prix, qte);
        return false;
    }


    public static String[] demandeSuppression() {
        return cat.getNomProduits();
    }

    public static boolean supprimerProduit(String nomProduit) {
        if(productDAO.delete(nomProduit))
            return getCatalogueSelectionne().removeProduit(nomProduit);
        return false;
    }
}