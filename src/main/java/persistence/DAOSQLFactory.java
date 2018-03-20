package persistence;

import domain.account.Account;
import dto.*;

public class DAOSQLFactory implements IDAOFactory {

    private final DAO<ProductDTO> productDAO;
    private final DAO<CategoryDTO> categoryDAO;
    private final DAO<OfferDTO> offerDAO;
    private final DAO<AddressDTO> addressDAO;
    private final DAO<OrderDTO> orderDAO;
    private final DAO<Account> accountDAO;
    private final DAO<OrderLineDTO> orderLineDAO;

    DAOSQLFactory(SQLDatabase database) {
        productDAO = new ProductDAOSQLImpl(database);
        categoryDAO = new CategoryDAOSQLImpl(database);
        offerDAO = new OfferDAOSQLImpl(database);
        addressDAO = new AddressDAOSQLImpl(database);
        orderDAO = new OrderDAOSQLImpl(database);
        accountDAO = new AccountDAOSQLImpl(database);
        orderLineDAO = new OrderLineDAOSQLImpl(database);
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

    @Override
    public DAO<AddressDTO> getAddressDAO() {
        return addressDAO;
    }

    @Override
    public DAO<OrderDTO> getOrderDAO() {
        return orderDAO;
    }

    @Override
    public DAO<Account> getAccountDAO() {
        return accountDAO;
    }

    @Override
    public DAO<OrderLineDTO> getOrderLineDAO() {
        return orderLineDAO;
    }

}
