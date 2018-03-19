package domain.component;

import domain.offer.Offer;
import exceptions.InvalidAmountException;

public class Product extends ShopComponent {

    private final double price;
    private int amountStored;
    private Offer activeOffer;

    public Product(int id, String name, String description, double price, String imageName, int amountStored) {
        super(id, name, description, imageName);
        this.price = price;
        this.amountStored = amountStored;
    }

    public double getPrice() {
        return price;
    }

    public int getAmountStored() {
        return amountStored;
    }

    public void lowerAmountStored() throws InvalidAmountException {
        if (amountStored <= 0) {
            throw new InvalidAmountException("Product amount can't be lower than 0.");
        }
        amountStored -= 1;
    }

    public Offer getActiveOffer() {
        if (activeOffer != null && !activeOffer.isActive()) {
            activeOffer = null;
        }
        return activeOffer;
    }

    public void setActiveOffer(Offer activeOffer) {
        this.activeOffer = activeOffer;
    }

}
