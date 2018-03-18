package domain.payment;

import dto.AddressDTO;

import java.math.BigInteger;

public class PaymentServices {

    private static PaymentServices instance;
    private final GiroServiceClient serviceClient;

    private PaymentServices() {
        this.serviceClient = new GiroServiceClient();
    }

    public BigInteger getGiroNumber(String customerName, AddressDTO addressDTO, double cost) {
        return serviceClient.getRandomNumber(customerName, addressDTO, cost);
    }

    public static PaymentServices getInstance() {
        if (instance == null) {
            instance = new PaymentServices();
        }
        return instance;
    }

    //To test:
/*    public static void main(String[] args) {
        System.out.println(PaymentServices.getInstance().getGiroNumber("Henk", new AddressDTO(1, "street", 2, "postal", "city", "country"), 20));
    }*/

}
