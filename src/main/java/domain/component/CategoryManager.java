package domain.component;

import dto.CategoryDTO;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;
import persistence.PersistenceServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryManager implements IComponentManager<Category, CategoryDTO> {

    private final Map<Integer, Category> categoryIdMap = new HashMap<>();
    private final ComponentMapper<Category, CategoryDTO> componentMapper;

    CategoryManager(ProductManager productManager) {
        componentMapper = new CategoryComponentMapper(this, productManager);

        // Load all categories from the db
        PersistenceServices.getInstance().findAllCategories().forEach(dto -> categoryIdMap.put(dto.getId(), componentMapper.toDomainObject(dto)));
    }

    @Override
    public void addNewComponent(CategoryDTO compDTO) throws ObjectAlreadyExistsException {
        if (categoryIdMap.containsKey(compDTO.getId())) {
            throw new ObjectAlreadyExistsException("category", "CategoryManager categoryIdMap");
        }
        Category category = componentMapper.toDomainObject(compDTO);
        categoryIdMap.put(compDTO.getId(), category);
        PersistenceServices.getInstance().insertCategory(compDTO);
    }

    @Override
    public void deleteComponent(Category shopComponent) throws ObjectNotFoundException {
        Integer id = getId(shopComponent);
        if (id == null) {
            throw new ObjectNotFoundException("category", "when deleting in CategoryManager");
        }
        categoryIdMap.remove(id);
        PersistenceServices.getInstance().deleteCategory(id + "");
    }

    @Override
    public Integer getId(ShopComponent shopComponent) {
        for (Map.Entry<Integer, Category> entry : categoryIdMap.entrySet()) {
            if (entry.getValue().equals(shopComponent)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public Category getComponent(int id) {
        return categoryIdMap.get(id);
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categoryIdMap.values());
    }

    public void removeProductFromCategory(Product product, Category category) throws ObjectNotFoundException {
        String objectStr = product == null ? "product" : category == null ? "category" : null;
        if (objectStr != null) {
            throw new ObjectNotFoundException(objectStr, "removing product from category");
        }
        if (category.getProductList().contains(product)) {
            category.getProductList().remove(product);
            PersistenceServices.getInstance().updateCategory(componentMapper.toDTO(category));
        }
    }

    public void addProductToCategory(Product product, Category category) throws ObjectNotFoundException {
        String objectStr = product == null ? "product" : category == null ? "category" : null;
        if (objectStr != null) {
            throw new ObjectNotFoundException(objectStr, "adding product to category");
        }
        if (!category.getProductList().contains(product)) {
            category.getProductList().add(product);
            PersistenceServices.getInstance().updateCategory(componentMapper.toDTO(category));
        }
    }

}
