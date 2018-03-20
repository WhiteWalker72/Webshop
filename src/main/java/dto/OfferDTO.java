package dto;

import java.util.Date;

public class OfferDTO {

    private final int id;
    private final Date startDate;
    private final Date endDate;
    private final String text;
    private final double offerPrice;
    private final int productId;

    public OfferDTO(int id, Date startDate, Date endDate, String text, double offerPrice, int productId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.text = text;
        this.offerPrice = offerPrice;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
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

}
