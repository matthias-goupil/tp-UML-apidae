package metier;

import java.util.Objects;

public class Produit implements I_Produit {

    /**
     * Quantite du produit en stock
     */
    private Integer quantiteStock;

    /**
     * Nom du produit
     */
    private String nom;

    /**
     * Prix unitaire du produit
     */
    private Double prixUnitaireHT ;

    /**
     * Taux de la TVA
     */
    private static final Double tauxTVA = 1.2;

    /**
     *  Constructor
     * @param nom String
     * @param prixInitaireHT Double
     * @param quantiteStock Integer
     */
    public Produit(String nom, Double prixInitaireHT, Integer quantiteStock) {
        this.nom = nom.replaceAll("(^ +)|( +$)|(\t+$)|(^\t+)", "");
        this.nom = this.nom.replaceAll("\\t"," ");
        this.prixUnitaireHT = prixInitaireHT;
        this.quantiteStock = quantiteStock;
    }

    /**
     *  Ajoute une quantite du produit en stock
     * @param qteAchetee Integer
     * @return boolean
     */
    @Override
    public boolean ajouter(int qteAchetee) {
        if (qteAchetee > 0) {
            this.quantiteStock += qteAchetee;
            return true;
        }
        return false;
    }

    /**
     *  Enleve une quantite du produit en stock
     * @param qteVendue Integer
     * @return boolean
     */
    @Override
    public boolean enlever(int qteVendue) {
        if (qteVendue > 0 && qteVendue <= quantiteStock) {
            this.quantiteStock -= qteVendue;
            return true;
        }
        return false;
    }

    /**
     * @return String nom
     */
    @Override
    public String getNom() {
        return this.nom;
    }

    /**
     * @return Integer quantiteStock
     */
    @Override
    public int getQuantite() {
        return this.quantiteStock;
    }

    /**
     * @return Double prixUnitaireHT
     */
    @Override
    public double getPrixUnitaireHT() {
        return this.prixUnitaireHT;
    }

    /**
     * @return Double prixUnitaireTTC
     */
    @Override
    public double getPrixUnitaireTTC() {
        return this.prixUnitaireHT*tauxTVA;
    }

    /**
     * @return Double prixStockTTC
     */
    @Override
    public double getPrixStockTTC() {
        return this.quantiteStock*prixUnitaireHT*tauxTVA;
    }

    /**
     * Verifie si deux produits sont les mêmes
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produit produit = (Produit) o;
        return nom.equals(produit.nom);
    }

    /**
     * Retourne le hashcode du produit
     * @return Integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}
