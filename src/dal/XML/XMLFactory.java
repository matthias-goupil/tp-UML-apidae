package dal.XML;

import dal.CatalogueDAO;
import dal.DAOFactory;
import dal.ProductDAO;

public class XMLFactory extends DAOFactory {
    @Override
    public ProductDAO getProductDAO() {
        return new ProductDAOXML();
    }

    @Override
    public CatalogueDAO getCatalogueDAO() {
        return null;
    }
}
