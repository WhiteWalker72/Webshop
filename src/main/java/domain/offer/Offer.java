package domain.offer;

import java.util.Date;

public class Offer {

    private final Date startDate;
    private final Date endDate;
    private final String text;
    private final double offerPrice;

    public Offer(Date startDate, Date endDate, String text, double offerPrice) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.text = text;
        this.offerPrice = offerPrice;
    }

    public boolean isActive() {
        Date currentDate = new Date();
        return startDate.compareTo(currentDate) * endDate.compareTo(currentDate) >= 0;
    }

    public String getText() {
        return text;
    }

    public double getOfferPrice() {
        return offerPrice;
    }

}
