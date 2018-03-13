package persistence;

import dto.ProductDTO;

import java.util.List;

public class PersistenceServices {

    private static PersistenceServices instance;
    private IDAOFactory daoFactory;

    private PersistenceServices() {

        //TODO: set daoFactory
        //setDaoFactory(new DAOSQLFactory(new MySQLDatabase()));
    }

    public List<ProductDTO> getAllProducts() {
        return daoFactory.getProductDAO().findAll();
    }

    public ProductDTO getProductById(String identifier) throws ObjectNotFoundException {
        return daoFactory.getProductDAO().findById(identifier);
    }

    public void insertProduct(ProductDTO productDTO) throws ObjectAlreadyExistsException {
        daoFactory.getProductDAO().insert(productDTO);
    }

    public void updateProduct(ProductDTO productDTO) throws ObjectNotFoundException {
        daoFactory.getProductDAO().update(productDTO);
    }

    public void deleteProduct(String identifier) throws ObjectNotFoundException {
        daoFactory.getProductDAO().delete(identifier);
    }

    public void setDaoFactory(IDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public static PersistenceServices getInstance() {
        if (instance == null) {
            instance = new PersistenceServices();
        }
        return instance;
    }
}
