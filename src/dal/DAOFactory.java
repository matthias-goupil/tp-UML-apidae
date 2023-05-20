package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAOFactory {
    private static final String DB_URL = "jdbc:oracle:thin:@162.38.222.149:1521:iut";
    private static final String DB_USER = "goupilm";
    private static final String DB_PASSWORD = "04102002";

    public abstract ProductDAO getProductDAO();
    public abstract CatalogueDAO getCatalogueDAO();

    protected Connection createConnection() {
        // Code pour créer et retourner une instance de connexion à votre base de données
        // Utilisez les constantes DB_URL, DB_USER et DB_PASSWORD pour la configuration de la connexion

        // Exemple pour une connexion à une base de données MySQL :

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
