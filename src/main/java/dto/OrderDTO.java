package dto;

import java.util.Date;
import java.util.List;

public class OrderDTO {

    private final int id;
    private final int customerId;
    private final Date orderDate;
    private final AddressDTO addressDTO;
    private final List<OrderLineDTO> orderLines;

    public OrderDTO(int id, int customerId, Date orderDate, AddressDTO addressDTO, List<OrderLineDTO> orderLines) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.addressDTO = addressDTO;
        this.orderLines = orderLines;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public List<OrderLineDTO> getOrderLines() {
        return orderLines;
    }

}
