package domain.component;

import java.util.List;

public class Category extends ShopComponent {

    private final List<ShopComponent> productList;

    Category(int id, String name, String description, String imageName, List<ShopComponent> productList) {
        super(id, name, description, imageName);
        this.productList = productList;
    }

    List<ShopComponent> getProductList() {
        return productList;
    }

}
