package persistence;

import dto.CategoryDTO;
import dto.OfferDTO;
import dto.ProductDTO;

public interface IDAOFactory {

    DAO<ProductDTO> getProductDAO();

    DAO<CategoryDTO> getCategoryDAO();

    DAO<OfferDTO> getOfferDAO();

}
