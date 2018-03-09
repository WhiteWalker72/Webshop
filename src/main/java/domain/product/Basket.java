package domain.product;

public class Basket extends Product {

    private final double length;
    private final double height;
    private final double depth;

    public Basket(String ID, String name, String[] description, double price, double length, double height, double depth) {
        super(ID, name, description, price);
        this.length = length;
        this.height = height;
        this.depth = depth;
    }

    public double getLength() {
        return length;
    }

    public double getHeight() {
        return height;
    }

    public double getDepth() {
        return depth;
    }

}
