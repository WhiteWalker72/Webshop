package persistence;

import dto.CategoryDTO;
import dto.OfferDTO;
import dto.ProductDTO;

public class DAOSQLFactory implements IDAOFactory {

    private final DAO<ProductDTO> productDAO;
    private final DAO<CategoryDTO> categoryDAO;
    private final DAO<OfferDTO> offerDAO;

    public DAOSQLFactory(SQLDatabase database) {
        productDAO = new ProductDAOSQLImpl(database);
        categoryDAO = new CategoryDAOSQLImpl(database);
        offerDAO = new OfferDAOSQLImpl(database);
    }

    @Override
    public DAO<ProductDTO> getProductDAO() {
        return productDAO;
    }

    @Override
    public DAO<CategoryDTO> getCategoryDAO() {
        return categoryDAO;
    }

    @Override
    public DAO<OfferDTO> getOfferDAO() {
        return offerDAO;
    }

}
