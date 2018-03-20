package persistence;

import domain.account.Account;
import domain.account.Customer;
import dto.*;

public interface IDAOFactory {

    DAO<ProductDTO> getProductDAO();

    DAO<CategoryDTO> getCategoryDAO();

    DAO<OfferDTO> getOfferDAO();

    DAO<AddressDTO> getAddressDAO();

    DAO<OrderDTO> getOrderDAO();

    DAO<Account> getAccountDAO();

    DAO<OrderLineDTO> getOrderLineDAO();

    DAO<Customer> getCustomerDAO();

}
