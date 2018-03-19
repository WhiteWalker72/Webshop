package domain.account;

public class Customer {

    private final int customerID;
    private final String name;
    private final int adresID;

    public Customer(int customerID, String name, int adresID) {
        this.customerID = customerID;
        this.name = name;
        this.adresID = adresID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public int getAdresID() {
        return adresID;
    }


}
