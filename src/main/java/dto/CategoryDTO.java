package dto;

import java.util.ArrayList;
import java.util.List;

public class CategoryDTO extends ComponentDTO {

    private List<Integer> productIdList;

    // Only for REST
    @Deprecated
    public CategoryDTO() {

    }

    public CategoryDTO(Integer id, String name, String description, String image, List<Integer> productIdList) {
        super(id, name, description, image);
        this.productIdList = productIdList;
    }

    public List<Integer> getProductIdList() {
        if (productIdList == null) {
            productIdList = new ArrayList<>();
        }
        return productIdList;
    }

}
