package controleur;

import metier.Produit;

public class ControleurNouveauSuppression extends ControleurCatalogue {

    public static boolean nouveauProduit(String nom, double prix, int qte) {
        Produit produit = new Produit(nom, prix, qte);
        if(catalogueDAO.addProduit(catalogueSelectionne, produit))
            return getCatalogueSelectionne().addProduit(nom, prix, qte);
        return false;
    }


    public static String[] demandeSuppression() {
        return catalogueSelectionne.getNomProduits();
    }

    public static boolean supprimerProduit(String nomProduit) {
        if(catalogueDAO.removeProduit(catalogueSelectionne, nomProduit))
            return getCatalogueSelectionne().removeProduit(nomProduit);
        return false;
    }
}