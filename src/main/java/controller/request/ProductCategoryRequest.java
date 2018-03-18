package controller.request;

public class ProductCategoryRequest {

    private int productId;
    private int categoryId;
    private String action;

    public ProductCategoryRequest() {

    }

    public ProductCategoryRequest(int productId, int categoryId, String action) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.action = action;
    }

    public int getProductId() {
        return productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getAction() {
        return action;
    }

}
