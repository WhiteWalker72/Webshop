package domain.component;

import domain.DTOMapper;
import dto.CategoryDTO;
import dto.ComponentDTO;
import dto.ProductDTO;

public class ComponentMapper implements DTOMapper<ShopComponent, ComponentDTO> {

    private final ComponentManager compManager;

    ComponentMapper(ComponentManager compManager) {
        this.compManager = compManager;
    }

    @Override
    public ComponentDTO toDTO(ShopComponent domainObject) {
        int id = compManager.getId(domainObject);

        if (domainObject instanceof Product) {
            return new ProductDTO(id, domainObject.getName(), domainObject.getDescription(), domainObject.getImageName())
                    .withPrice(((Product) domainObject).getPrice());
        } else if (domainObject instanceof Category) {
            return new CategoryDTO(id, domainObject.getName(), domainObject.getDescription(), domainObject.getImageName()
                    , ((Category) domainObject).getProductIdList());
        }
        return new ComponentDTO(id, domainObject.getName(), domainObject.getDescription(), domainObject.getImageName());
    }

    @Override
    public ShopComponent toDomainObject(ComponentDTO dto) {
        if (dto instanceof ProductDTO) {
            return new Product(dto.getName(), dto.getDescription(), ((ProductDTO) dto).getPrice(), dto.getImage());
        } else if (dto instanceof CategoryDTO) {
            return new Category(dto.getName(), dto.getDescription(), dto.getImage(), ((CategoryDTO) dto).getProductIdList());
        }
        return new ShopComponent(dto.getName(), dto.getDescription(), dto.getImage());
    }

}

