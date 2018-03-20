package dto;

public class ProductDTO extends ComponentDTO {

    private double price;
    private int amountStored;

    // Only for REST
    @Deprecated
    public ProductDTO() {

    }

    public ProductDTO(Integer id, String name, String description, String image, double price, int amountStored) {
        super(id, name, description, image);
        this.price = price;
        this.amountStored = amountStored;
    }

    public double getPrice() {
        return price;
    }

    public int getAmountStored() {
        return amountStored;
    }

}
