package domain.component;

import dto.ProductDTO;
import exceptions.InvalidAmountException;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;
import persistence.PersistenceServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductManager implements IComponentManager<Product, ProductDTO> {

    private final Map<Integer, Product> productIdMap = new HashMap<>();
    private final ComponentMapper<Product, ProductDTO> componentMapper = new ProductComponentMapper(this);

    ProductManager() {
        PersistenceServices.getInstance().findAllProducts()
                .forEach(dto -> productIdMap.put(dto.getId(), componentMapper.toDomainObject(dto)));
    }

    @Override
    public void addNewComponent(ProductDTO compDTO) throws ObjectAlreadyExistsException {
        if (productIdMap.containsKey(compDTO.getId())) {
            throw new ObjectAlreadyExistsException("product", "ProductManager productIdMap");
        }
        Product product = componentMapper.toDomainObject(compDTO);
        productIdMap.put(compDTO.getId(), product);
        PersistenceServices.getInstance().insertProduct(compDTO);
    }

    @Override
    public void deleteComponent(Product shopComponent) throws ObjectNotFoundException {
        Integer id = getId(shopComponent);
        if (id == null) {
            throw new ObjectNotFoundException("product", "when deleting in ProductManager");
        }
        productIdMap.remove(id);
        PersistenceServices.getInstance().deleteProduct(id + "");
    }

    @Override
    public Integer getId(ShopComponent shopComponent) {
        for (Map.Entry<Integer, Product> entry : productIdMap.entrySet()) {
            if (entry.getValue().equals(shopComponent)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public Product getComponent(int id) {
        return productIdMap.get(id);
    }

    public void updateProduct(ProductDTO productDTO) throws ObjectNotFoundException {
        if (productDTO == null) {
            throw new ObjectNotFoundException("productDTO", "updating", "unknown");
        }

        productIdMap.put(productDTO.getId(), componentMapper.toDomainObject(productDTO));
        PersistenceServices.getInstance().updateProduct(productDTO);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productIdMap.values());
    }

    public void lowerProductAmount(Product product) throws ObjectNotFoundException, InvalidAmountException {
        if (product == null) {
            throw new ObjectNotFoundException("product", "when lowering amount stored");
        }
        product.lowerAmountStored();
    }

}
