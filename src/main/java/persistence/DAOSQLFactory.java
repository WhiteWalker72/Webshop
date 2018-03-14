package persistence;

import dto.ProductDTO;

public class DAOSQLFactory implements IDAOFactory {

    private final DAO<ProductDTO> productDAO;

    public DAOSQLFactory(SQLDatabase database) {
        productDAO = new ProductDAOSQLImpl(database);
    }

    @Override
    public DAO<ProductDTO> getProductDAO() {
        return productDAO;
    }

}
