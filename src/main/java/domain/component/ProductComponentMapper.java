package domain.component;

import dto.ProductDTO;

class ProductComponentMapper extends ComponentMapper<Product, ProductDTO> {

    ProductComponentMapper(IComponentManager<Product, ProductDTO> compManager) {
        super(compManager);
    }

    @Override
    public ProductDTO toDTO(Product domainObject) {
        int id = compManager.getId(domainObject);
        return new ProductDTO(id, domainObject.getName(), domainObject.getDescription(), domainObject.getImageName()
                , domainObject.getAmountStored()).withPrice(domainObject.getPrice());
    }

    @Override
    public Product toDomainObject(ProductDTO dto) {
        return new Product(dto.getName(), dto.getDescription(), dto.getPrice(), dto.getImage(), dto.getAmountStored());
    }

}