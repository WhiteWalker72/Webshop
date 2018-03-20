package dto;

public class AddressDTO {

    private final int id;
    private final String street;
    private final String number;
    private final String postalCode;
    private final String city;
    private final String country;


    public AddressDTO(int id, String street, String number, String postalCode, String city, String country) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() { return country;}

}
