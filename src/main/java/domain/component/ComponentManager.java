package domain.component;

import exceptions.ObjectNotFoundException;
import persistence.PersistenceServices;

import java.util.HashMap;
import java.util.Map;

public class ComponentManager {

    private final ComponentMapper compMapper;
    private final Map<ShopComponent, Integer> componentIdMap = new HashMap<>();

    ComponentManager() {
        compMapper = new ComponentMapper(this);

        PersistenceServices.getInstance().findAllProducts().forEach(productDTO ->
            componentIdMap.put(compMapper.toDomainObject(productDTO), productDTO.getId())
        );
    }

    public Integer getId(ShopComponent shopComponent) {
        return componentIdMap.get(shopComponent);
    }

    public Category getCategory(int id) {
        for (Map.Entry<ShopComponent, Integer> entry : componentIdMap.entrySet()) {
            if (entry.getValue() == id && entry.getKey() instanceof Category) {
                return (Category) entry.getKey();
            }
        }
        return null;
    }

    public Product getProduct(int id) {
        for (Map.Entry<ShopComponent, Integer> entry : componentIdMap.entrySet()) {
            if (entry.getValue() == id && entry.getKey() instanceof Product) {
                return (Product) entry.getKey();
            }
        }
        return null;
    }

    public void removeProductFromCategory(Product product, Category category) throws ObjectNotFoundException {
        String objectStr = product == null ? "product" : category == null ? "category" : null;
        if (objectStr != null) {
            throw new ObjectNotFoundException(objectStr, "removing product from category");
        }
        category.getProductList().remove(product);
    }

    public void addProductToCategory(Product product, Category category) throws ObjectNotFoundException {
        String objectStr = product == null ? "product" : category == null ? "category" : null;
        if (objectStr != null) {
            throw new ObjectNotFoundException(objectStr, "adding product to category");
        }
        if (!category.getProductList().contains(product)) {
            category.getProductList().add(product);
        }
    }

}
