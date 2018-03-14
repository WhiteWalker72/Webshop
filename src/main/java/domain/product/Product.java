package domain.product;

public class Product extends ShopComponent {

    private final double price;

    public Product(String id, String name, String[] description, double price) {
        super(id, name, description);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

}
