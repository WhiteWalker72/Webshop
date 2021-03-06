package domain.component;

import domain.offer.Offer;
import dto.CategoryDTO;
import dto.ProductDTO;
import exceptions.InvalidAmountException;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;

import java.io.InvalidObjectException;
import java.util.List;

public class ComponentServices {

    private static ComponentServices instance;

    private final ProductManager productManager;
    private final CategoryManager categoryManager;

    private ComponentServices() {
        productManager = new ProductManager();
        categoryManager = new CategoryManager(productManager);
    }

    public List<Category> getAllCategories() {
        return categoryManager.getAllCategories();
    }

    public Category getCategory(int id) {
        return categoryManager.getComponent(id);
    }

    public void updateCategory(CategoryDTO categoryDTO) throws ObjectNotFoundException {
        categoryManager.updateCategory(categoryDTO);
    }

    public void addNewCategory(CategoryDTO categoryDTO) throws ObjectAlreadyExistsException {
        categoryManager.addNewComponent(categoryDTO);
    }

    public void deleteCategory(Category category) throws ObjectNotFoundException {
        categoryManager.deleteComponent(category);
    }

    public List<Category> getProductCategories(int productId) {
        return categoryManager.getProductCategories(productManager.getComponent(productId));
    }

    public List<Product> getAllProducts() {
        return productManager.getAllProducts();
    }

    public List<Product> getAllProductsByCategory(int categoryId) {
        return categoryManager.getProductsByCategory(categoryId);
    }

    public Product getProduct(int id) {
        return productManager.getComponent(id);
    }

    public void updateProduct(ProductDTO productDTO) throws ObjectNotFoundException {
        productManager.updateProduct(productDTO);
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

    public void setProductOffer(Product product, Offer offer) throws InvalidObjectException, ObjectNotFoundException {
        productManager.setProductOffer(product, offer);
    }

    public static ComponentServices getInstance() {
        if (instance == null)
            instance = new ComponentServices();
        return instance;
    }

}
