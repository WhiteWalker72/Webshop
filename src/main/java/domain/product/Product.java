package domain.product;

public class Product extends ShopComponent {

    private final double price;

    public Product(String ID, String name, String description, double price) {
        super(ID, name, description);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

}
