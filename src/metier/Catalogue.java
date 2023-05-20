package metier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Catalogue implements I_Catalogue{
    private List<I_Produit> productList;

    private int id;

    private String nom;

    public Catalogue(String nom) {
        this.nom = nom;
        productList = new ArrayList<>();
    }

    public Catalogue(String nom, ArrayList<I_Produit> produits) {
        this.nom = nom;
        productList = produits;
    }

    @Override
    public boolean addProduit(I_Produit produit) {
        if(produit == null || produit.getQuantite() < 0 || produit.getPrixUnitaireHT() <= 0) return false;
        if(!productList.contains(produit)){
            return productList.add(produit);
        }
        return false;
    }

    @Override
    public boolean addProduit(String nom, double prix, int qte) {
        I_Produit produit = new Produit(nom,prix,qte);
        return addProduit(produit);

    }

    @Override
    public int addProduits(List<I_Produit> l) {
        if(l == null) return 0;
        int nbProduitAjoutes = 0;
        for(I_Produit produit : l) {
            if(addProduit(produit)){
                nbProduitAjoutes++;
            }
        }
        return nbProduitAjoutes;
    }

    @Override
    public boolean removeProduit(String nom) {
        return productList.removeIf(produit -> produit.getNom() == nom);
    }

    @Override
    public boolean acheterStock(String nomProduit, int qteAchetee) {
        boolean b = false;
        for (I_Produit p : productList) {
            if(p.getNom() == nomProduit) {
                b = p.ajouter(qteAchetee);
            }
        }
        return b;
    }

    @Override
    public boolean vendreStock(String nomProduit, int qteVendue) {
        boolean b = false;
        for (I_Produit p : productList) {
            if(p.getNom() == nomProduit) {
                b = p.enlever(qteVendue);
            }
        }
        return b;
    }

    @Override
    public String[] getNomProduits() {
        String[] nomProduits = new String[productList.size()];
        int i = 0;
        for(I_Produit product : productList) {
            nomProduits[i] = product.getNom();
            i++;
        }
        Arrays.sort(nomProduits);
        return nomProduits;
    }

    @Override
    public double getMontantTotalTTC() {
        double montantTotal = 0;
        for(I_Produit product : productList) {
            montantTotal += product.getPrixStockTTC();
        }
        return (double) Math.round(montantTotal * 100) / 100;
    }

    @Override
    public void clear() {
        productList = new ArrayList<>();
    }

    @Override
    public String toString() {
        String value = "";

        for(I_Produit produit : productList) {
            value += produit.getNom() + " - prix HT : "+
                    String.format(Locale.FRANCE, "%.2f", produit.getPrixUnitaireHT())+
                    " € - prix TTC : "+
                    String.format(Locale.FRANCE, "%.2f", produit.getPrixUnitaireTTC())+
                    " € - quantité en stock : "+
                    produit.getQuantite() + "\n";
        }
        value += "\nMontant total TTC du stock : "+String.format(Locale.FRANCE, "%.2f", getMontantTotalTTC())+" €";
        return value;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public I_Produit getProduit(String nomProduit) {
        for(I_Produit p: productList){
            if(p.getNom().equals(nomProduit))
                return p;
        }
        return null;
    }

}
