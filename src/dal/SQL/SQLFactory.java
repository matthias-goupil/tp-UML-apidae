package dal.SQL;

import dal.DAOFactory;
import dal.ProductDAO;
import dal.SQL.ProductDAOSQL;

public class SQLFactory extends DAOFactory {

    public ProductDAO getProductDAO() {
        return new ProductDAOSQL();
    }
}
