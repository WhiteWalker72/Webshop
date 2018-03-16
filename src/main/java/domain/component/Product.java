package domain.component;

public class Product extends ShopComponent {

    private final double price;

    public Product(String name, String description, double price, String imageName) {
        super(name, description, imageName);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

}
