package dto;

public class OrderLineDTO {

    private final int id;
    private final int orderId;
    private final int amount;
    private final double price;
    private final int productId;

    public OrderLineDTO(int id, int orderId, int amount, double price, int productId) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.price = price;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public int getProductId() {
        return productId;
    }

}
