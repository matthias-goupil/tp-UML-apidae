package dal;

public class ConcreteDAOFactory extends DAOFactory {
    @Override
    public ProductDAO getProductDAO() {
        return new ProductDAOImpl(createConnection());
    }

    @Override
    public CatalogueDAO getCatalogueDAO() {
        return new CatalogueDAOImpl(createConnection());
    }
}
