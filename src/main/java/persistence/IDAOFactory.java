package persistence;

import domain.account.Account;
import dto.*;

public interface IDAOFactory {

    DAO<ProductDTO> getProductDAO();

    DAO<CategoryDTO> getCategoryDAO();

    DAO<OfferDTO> getOfferDAO();

    DAO<AddressDTO> getAddressDAO();

    DAO<OrderDTO> getOrderDAO();

    DAO<Account> getAccountDAO();

}
