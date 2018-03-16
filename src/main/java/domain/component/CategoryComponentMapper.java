package domain.component;

import dto.CategoryDTO;

import java.util.Objects;
import java.util.stream.Collectors;

class CategoryComponentMapper extends ComponentMapper<Category, CategoryDTO> {

    private final ProductManager productManager;

    CategoryComponentMapper(IComponentManager<Category, CategoryDTO> compManager, ProductManager productManager) {
        super(compManager);
        this.productManager = productManager;
    }

    @Override
    public CategoryDTO toDTO(Category domainObject) {
        return new CategoryDTO(compManager.getId(domainObject), domainObject.getName(), domainObject.getDescription(), domainObject.getImageName(),
                domainObject.getProductList().stream().map(productManager::getId).filter(Objects::nonNull).collect(Collectors.toList()));
    }

    @Override
    public Category toDomainObject(CategoryDTO dto) {
        return new Category(dto.getName(), dto.getDescription(), dto.getImage(),
                dto.getProductIdList().stream().map(productManager::getComponent).filter(Objects::nonNull).collect(Collectors.toList()));
    }

}
