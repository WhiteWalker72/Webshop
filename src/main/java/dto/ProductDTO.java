package dto;

public class ProductDTO extends ComponentDTO {

    private double price;

    public ProductDTO(int id, String name, String description, String image) {
        super(id, name, description, image);
    }

    public ComponentDTO withPrice(double price) {
        this.price = price;
        return this;
    }

    public double getPrice() {
        return price;
    }

}
