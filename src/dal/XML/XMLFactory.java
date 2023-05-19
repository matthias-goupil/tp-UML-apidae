package dal.XML;

import dal.DAOFactory;
import dal.ProductDAO;

public class XMLFactory extends DAOFactory {
    @Override
    public ProductDAO getProductDAO() {
        return new ProductDAOXML();
    }
}
