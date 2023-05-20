package dal.SQL;

import dal.CatalogueDAO;
import dal.DAOFactory;
import dal.ProductDAO;
import dal.SQL.ProductDAOSQL;

public class SQLFactory extends DAOFactory {

    public ProductDAO getProductDAO() {
        return new ProductDAOSQL(createConnection());
    }

    @Override
    public CatalogueDAO getCatalogueDAO() {
        return new CatalogueDAOSQL(createConnection());
    }
}
