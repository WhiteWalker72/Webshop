package dto;

public class ProductDTO {

    private final int id;
    private final String name;
    private final String description;
    private final String image;

    private Double price;

    public ProductDTO(int id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public ProductDTO withPrice(double price) {
        this.price = price;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public Double getPrice() {
        return price;
    }

}
