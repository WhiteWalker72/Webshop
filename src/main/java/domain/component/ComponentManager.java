package domain.component;

import persistence.PersistenceServices;

import java.util.HashMap;
import java.util.Map;

public class ComponentManager {

    private final ComponentMapper compMapper;
    private final Map<ShopComponent, Integer> componentIdMap = new HashMap<>();

    public ComponentManager() {
        compMapper = new ComponentMapper(this);

        PersistenceServices.getInstance().findAllProducts().forEach(productDTO ->
            componentIdMap.put(compMapper.toDomainObject(productDTO), productDTO.getId())
        );
    }

    public int getId(ShopComponent shopComponent) {
        return componentIdMap.get(shopComponent);
    }

    public ShopComponent getCategory(int id) {
        for (Map.Entry<ShopComponent, Integer> entry : componentIdMap.entrySet()) {
            if (entry.getValue() == id && entry.getKey() instanceof Category) {
                return entry.getKey();
            }
        }
        return null;
    }

    public ShopComponent getProduct(int id) {
        for (Map.Entry<ShopComponent, Integer> entry : componentIdMap.entrySet()) {
            if (entry.getValue() == id && entry.getKey() instanceof Product) {
                return entry.getKey();
            }
        }
        return null;
    }

}
