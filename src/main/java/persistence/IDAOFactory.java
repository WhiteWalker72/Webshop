package persistence;

import dto.ProductDTO;

public interface IDAOFactory {

    DAO<ProductDTO> getProductDAO();

}
