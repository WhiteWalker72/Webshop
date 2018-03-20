package domain.offer;

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
        Date currentDate = new Date();
        return currentDate.after(startDate) && currentDate.before(endDate);
    }

    public String getText() {
        return text;
    }

    public double getOfferPrice() {
        return offerPrice;
    }

    public int getProductId() {
        return productId;
    }

    OfferDTO toOfferDTO() {
        return new OfferDTO(offerId, startDate, endDate, text, offerPrice, productId);
    }

}
