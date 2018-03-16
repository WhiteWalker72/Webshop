package persistence;

import dto.ProductDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class PersistenceServices {

    private static PersistenceServices instance;
    private IDAOFactory daoFactory;

    private PersistenceServices() {
        try {
            InputStream input = new FileInputStream("config.properties");
            Properties prop = new Properties();
            prop.load(input);

            setDaoFactory(new DAOSQLFactory(new MySQLDatabase(prop.getProperty("address"), Integer.parseInt(prop.getProperty("port"))
                    , prop.getProperty("database"), prop.getProperty("username"), prop.getProperty("password"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ProductDTO> findAllProducts() {
        return daoFactory.getProductDAO().findAll();
    }

    public ProductDTO findProductById(String identifier) throws ObjectNotFoundException {
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
