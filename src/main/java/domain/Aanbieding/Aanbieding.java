package domain.Aanbieding;

import java.util.Date;

public class Aanbieding {
    private Date vanDatum;
    private Date totDatum;
    private String reclameTekst;
    private double aanbiedingsPrijs;

    public Aanbieding(Date vanDatum, Date totDatum, String reclameTekst, double aanbiedingsPrijs) {
        this.vanDatum = vanDatum;
        this.totDatum = totDatum;
        this.reclameTekst = reclameTekst;
        this.aanbiedingsPrijs = aanbiedingsPrijs;
    }

    public Date getVanDatum() {
        return vanDatum;
    }

    public void setVanDatum(Date vanDatum) {
        this.vanDatum = vanDatum;
    }

    public Date getTotDatum() {
        return totDatum;
    }

    public void setTotDatum(Date totDatum) {
        this.totDatum = totDatum;
    }

    public String getReclameTekst() {
        return reclameTekst;
    }

    public void setReclameTekst(String reclameTekst) {
        this.reclameTekst = reclameTekst;
    }

    public double getAanbiedingsPrijs() {
        return aanbiedingsPrijs;
    }

    public void setAanbiedingsPrijs(double aanbiedingsPrijs) {
        this.aanbiedingsPrijs = aanbiedingsPrijs;
    }
}
