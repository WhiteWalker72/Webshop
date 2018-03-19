package domain.component;

import java.util.List;
import java.util.stream.Collectors;

public class Category extends ShopComponent {

    private final List<ShopComponent> productList;

    Category(int id, String name, String description, String imageName, List<ShopComponent> productList) {
        super(id, name, description, imageName);
        this.productList = productList;

        List<Product> products = productList.stream().filter(comp -> comp instanceof Product).map(product -> (Product) product).collect(Collectors.toList());
    }

    List<ShopComponent> getProductList() {
        return productList;
    }

}
