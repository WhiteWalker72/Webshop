package persistence;

import dto.CategoryDTO;
import dto.ProductDTO;

public class DAOSQLFactory implements IDAOFactory {

    private final DAO<ProductDTO> productDAO;
    private final DAO<CategoryDTO> categoryDAO;

    public DAOSQLFactory(SQLDatabase database) {
        productDAO = new ProductDAOSQLImpl(database);
        categoryDAO = new CategoryDAOSQLImpl(database);
    }

    @Override
    public DAO<ProductDTO> getProductDAO() {
        return productDAO;
    }

    @Override
    public DAO<CategoryDTO> getCategoryDAO() {
        return categoryDAO;
    }

}
