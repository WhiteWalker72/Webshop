package domain.account;

public class Address {
    private final int addressId;
    private final String streetName;
    private final String streetNumber;
    private final String postalCode;
    private final String city;
    private final String country;

    public Address(int addressId, String streetName, String streetNumber, String postalCode, String city, String country) {
        this.addressId = addressId;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public int getAddressId() {
        return addressId;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() { return country; }
}
