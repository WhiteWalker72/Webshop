package persistence;

import dto.CategoryDTO;
import dto.ProductDTO;

public interface IDAOFactory {

    DAO<ProductDTO> getProductDAO();

    DAO<CategoryDTO> getCategoryDAO();

}
