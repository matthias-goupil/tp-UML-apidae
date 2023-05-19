package controleur;

public class ControleurStock extends ControleurCatalogue{
    public static String getStringCatalogue() {
        return getCatalogueSelectionne().toString();
    }
}
