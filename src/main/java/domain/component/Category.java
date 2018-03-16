package domain.component;

import java.util.List;

public class Category extends ShopComponent {

    private final List<Integer> productIdList;

    public Category(String name, String description, String imageName, List<Integer> productIdList) {
        super(name, description, imageName);
        this.productIdList = productIdList;
    }

    public List<Integer> getProductIdList() {
        return productIdList;
    }

}
