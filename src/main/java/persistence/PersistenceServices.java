package persistence;

import domain.account.Account;
import domain.account.Customer;
import dto.*;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class PersistenceServices {

    private static PersistenceServices instance;
    private IDAOFactory daoFactory;

    private PersistenceServices() {
        setDaoFactory(new DAOSQLFactory(new MySQLDatabase("@airdev.nl", 33060
                , "webshop", "webshop", "eqwl2722yEFx")));
    }

    public static PersistenceServices getInstance() {
        if (instance == null) {
            instance = new PersistenceServices();
        }
        return instance;
    }

    public void switchToTestDatabase() {
        setDaoFactory(new DAOSQLFactory(new MySQLDatabase("@airdev.nl", 33060
                , "webshop-test", "webshop", "eqwl2722yEFx")));
    }

    public String getNextProductId() {
        return daoFactory.getProductDAO().getNextUniqueId();
    }

    public List<ProductDTO> findAllProducts() {
        return daoFactory.getProductDAO().findAll();
    }

    public ProductDTO findProductById(String identifier) {
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

    public String getNextCategoryId() {
        return daoFactory.getCategoryDAO().getNextUniqueId();
    }

    public List<CategoryDTO> findAllCategories() {
        return daoFactory.getCategoryDAO().findAll();
    }

    public CategoryDTO findCategoryById(String identifier) {
        return daoFactory.getCategoryDAO().findById(identifier);
    }

    public void insertCategory(CategoryDTO categoryDTO) throws ObjectAlreadyExistsException {
        daoFactory.getCategoryDAO().insert(categoryDTO);
    }

    public void updateCategory(CategoryDTO categoryDTO) throws ObjectNotFoundException {
        daoFactory.getCategoryDAO().update(categoryDTO);
    }

    public void deleteCategory(String identifier) throws ObjectNotFoundException {
        daoFactory.getCategoryDAO().delete(identifier);
    }

    public List<OfferDTO> findAllOffers() {
        return daoFactory.getOfferDAO().findAll();
    }

    public OfferDTO findOfferById(String identifier) {
        return daoFactory.getOfferDAO().findById(identifier);
    }

    public void insertOffer(OfferDTO offerDTO) throws ObjectAlreadyExistsException {
        daoFactory.getOfferDAO().insert(offerDTO);
    }

    public void updateOffer(OfferDTO offerDTO) throws ObjectNotFoundException {
        daoFactory.getOfferDAO().update(offerDTO);
    }

    public void deleteOffer(String identifier) throws ObjectNotFoundException {
        daoFactory.getOfferDAO().delete(identifier);
    }

    public AddressDTO findAddressById(String identifier) {
        return daoFactory.getAddressDAO().findById(identifier);
    }

    public void insertAddress(AddressDTO addressDTO) throws ObjectAlreadyExistsException {
        daoFactory.getAddressDAO().insert(addressDTO);
    }

    public void updateAddress(AddressDTO addressDTO) throws ObjectNotFoundException {
        daoFactory.getAddressDAO().update(addressDTO);
    }

    public void deleteAddress(String identifier) throws ObjectNotFoundException {
        daoFactory.getAddressDAO().delete(identifier);
    }

    public String getNextOrderId() {
        return daoFactory.getOrderDAO().getNextUniqueId();
    }

    public String getNextOrderLineId() {
        return daoFactory.getOrderLineDAO().getNextUniqueId();
    }

    void insertOrderLine(OrderLineDTO orderLineDTO) throws ObjectAlreadyExistsException {
        daoFactory.getOrderLineDAO().insert(orderLineDTO);
    }

    List<OrderLineDTO> getAllOrderLines() {
        return daoFactory.getOrderLineDAO().findAll();
    }

    public OrderDTO findOrderById(String identifier) {
        return daoFactory.getOrderDAO().findById(identifier);
    }

    public List<OrderDTO> getAllOrders() {
        return daoFactory.getOrderDAO().findAll();
    }

    public void insertOrder(OrderDTO orderDTO) throws ObjectAlreadyExistsException {
        daoFactory.getOrderDAO().insert(orderDTO);
    }

    public void updateOrder(OrderDTO orderDTO) throws ObjectNotFoundException {
        daoFactory.getOrderDAO().update(orderDTO);
    }

    public void deleteOrder(String identifier) throws ObjectNotFoundException {
        daoFactory.getOrderDAO().delete(identifier);
    }

    public void setDaoFactory(IDAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void insertAccount(Account account) throws ObjectAlreadyExistsException {
        daoFactory.getAccountDAO().insert(account);
    }

    public void insertCustomer(Customer customer) throws ObjectAlreadyExistsException {
        daoFactory.getCustomerDAO().insert(customer);
    }

    public String getNextAccountId() {
        return daoFactory.getAccountDAO().getNextUniqueId();
    }

    public Account getAccount(String username) {
        return daoFactory.getAccountDAO().findById(username);
    }

    public String getNextAddressId() {
        return daoFactory.getAddressDAO().getNextUniqueId();
    }

    public String getNextCustomerId() {
        return daoFactory.getCustomerDAO().getNextUniqueId();
    }

}