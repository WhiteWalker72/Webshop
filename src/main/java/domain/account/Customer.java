package domain.account;

public class Customer {

    private final int customerID;
    private final String name;
    private final int addressID;

    public Customer(int customerID, String name, int adresID) {
        this.customerID = customerID;
        this.name = name;
        this.addressID = adresID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public int getAddressID() {
        return addressID;
    }
}
