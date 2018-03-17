package domain.offer;

import domain.component.ComponentServices;
import domain.component.Product;
import dto.OfferDTO;

import java.util.Date;

public class Offer {

    private final int offerId;
    private final Date startDate;
    private final Date endDate;
    private final String text;
    private final double offerPrice;
    private final int productId;

    Offer(int offerId, Date startDate, Date endDate, String text, double offerPrice, int productId) {
        this.offerId = offerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.text = text;
        this.offerPrice = offerPrice;
        this.productId = productId;
    }

    public int getOfferId() {
        return offerId;
    }

    public boolean isActive() {
        if (getProduct() == null) {
            System.out.println("no product");
            return false;
        }
        Date currentDate = new Date();
        return currentDate.after(startDate) && currentDate.before(endDate);
    }

    public String getText() {
        return text;
    }

    public double getOfferPrice() {
        return offerPrice;
    }

    public Product getProduct() {
        return ComponentServices.getInstance().getProduct(productId);
    }

    int getProductId() {
        return productId;
    }

    OfferDTO toOfferDTO() {
        return new OfferDTO(offerId, startDate, endDate, text, offerPrice, productId);
    }

}
