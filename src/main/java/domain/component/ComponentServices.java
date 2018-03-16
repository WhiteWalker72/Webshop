package domain.component;

import exceptions.ObjectNotFoundException;

public class ComponentServices {

    private final ComponentManager compManager;

    public ComponentServices() {
        this.compManager = new ComponentManager();
    }

    public Category getCategory(int id) {
        return compManager.getCategory(id);
    }

    public Product getProduct(int id) {
        return compManager.getProduct(id);
    }

    public void removeProductFromCategory(Product product, Category category) throws ObjectNotFoundException {
        compManager.removeProductFromCategory(product, category);
    }

    public void addProductToCategory(Product product, Category category) throws ObjectNotFoundException {
        compManager.addProductToCategory(product, category);
    }

}
