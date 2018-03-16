package domain.component;

import dto.CategoryDTO;
import dto.ProductDTO;
import exceptions.InvalidAmountException;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;

public class ComponentServices {

    private final ProductManager productManager;
    private final CategoryManager categoryManager;

    public ComponentServices() {
        productManager = new ProductManager();
        categoryManager = new CategoryManager(productManager);
    }

    public Category getCategory(int id) {
        return categoryManager.getComponent(id);
    }

    public void addNewCategory(CategoryDTO categoryDTO) throws ObjectAlreadyExistsException {
        categoryManager.addNewComponent(categoryDTO);
    }

    public void deleteCategory(Category category) throws ObjectNotFoundException {
        categoryManager.deleteComponent(category);
    }

    public Product getProduct(int id) {
        return productManager.getComponent(id);
    }

    public void addNewProduct(ProductDTO productDTO) throws ObjectAlreadyExistsException {
        productManager.addNewComponent(productDTO);
    }

    public void deleteProduct(Product product) throws ObjectNotFoundException {
        productManager.deleteComponent(product);
    }

    public void lowerProductAmount(Product product) throws InvalidAmountException, ObjectNotFoundException {
        productManager.lowerProductAmount(product);
    }

    public void removeProductFromCategory(Product product, Category category) throws ObjectNotFoundException {
        categoryManager.removeProductFromCategory(product, category);
    }

    public void addProductToCategory(Product product, Category category) throws ObjectNotFoundException {
        categoryManager.addProductToCategory(product, category);
    }

}
