package dto;

import java.util.List;

public class CategoryDTO extends ComponentDTO {

    private final List<Integer> productIdList;

    public CategoryDTO(int id, String name, String description, String image, List<Integer> productIdList) {
        super(id, name, description, image);
        this.productIdList = productIdList;
    }

    public List<Integer> getProductIdList() {
        return productIdList;
    }

}
