package dto;

public class ProductDTO extends ComponentDTO {

    private double price;
    private int amountStored;

    public ProductDTO(int id, String name, String description, String image, int amountStored) {
        super(id, name, description, image);
        this.amountStored = amountStored;
    }

    public ProductDTO withPrice(double price) {
        this.price = price;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public int getAmountStored() {
        return amountStored;
    }

}
