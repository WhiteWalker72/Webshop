package domain.component;

import domain.DTOMapper;
import dto.CategoryDTO;
import dto.ComponentDTO;
import dto.ProductDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
            List<ShopComponent> categoryProducts = ((Category) domainObject).getProductList();

            return new CategoryDTO(id, domainObject.getName(), domainObject.getDescription(), domainObject.getImageName()
                    , categoryProducts.stream().map(compManager::getId).filter(Objects::nonNull).collect(Collectors.toList()));
        }
        return new ComponentDTO(id, domainObject.getName(), domainObject.getDescription(), domainObject.getImageName());
    }

    @Override
    public ShopComponent toDomainObject(ComponentDTO dto) {
        if (dto instanceof ProductDTO) {
            return new Product(dto.getName(), dto.getDescription(), ((ProductDTO) dto).getPrice(), dto.getImage());
        } else if (dto instanceof CategoryDTO) {
            List<Integer> productIdList = ((CategoryDTO) dto).getProductIdList();

            return new Category(dto.getName(), dto.getDescription(), dto.getImage(),
                    productIdList.stream().map(compManager::getProduct).filter(Objects::nonNull).collect(Collectors.toList()));
        }
        return new ShopComponent(dto.getName(), dto.getDescription(), dto.getImage());
    }

}

