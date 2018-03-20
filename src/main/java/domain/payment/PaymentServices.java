package domain.payment;

import dto.AddressDTO;
import dto.OrderDTO;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;

import java.math.BigInteger;

public class PaymentServices {

    private static PaymentServices instance;

    private final GiroServiceClient serviceClient;
    private final OrderManager orderManager;

    private PaymentServices() {
        this.serviceClient = new GiroServiceClient();
        this.orderManager = new OrderManager();
    }

    public BigInteger getGiroNumber(String customerName, AddressDTO addressDTO, double cost) {
        return serviceClient.getRandomNumber(customerName, addressDTO, cost);
    }

    public void saveOrder(OrderDTO orderDTO) throws ObjectAlreadyExistsException {
        orderManager.saveOrder(orderDTO);
    }

    public OrderDTO getOrder(int orderId) throws ObjectNotFoundException {
        return orderManager.getOrder(orderId);
    }

    public static PaymentServices getInstance() {
        if (instance == null) {
            instance = new PaymentServices();
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(PaymentServices.getInstance().getGiroNumber("customer", new AddressDTO(1, "street", "12"
        , "1234ZP", "city", "country"), 12.00));
    }

}
