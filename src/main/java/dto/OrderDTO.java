package dto;

import java.util.Date;
import java.util.List;

public class OrderDTO {

    private Integer id;
    private final int customerId;
    private final Date orderDate;
    private final AddressDTO addressDTO;
    private final List<OrderLineDTO> orderLines;
    private final Integer giro;

    public OrderDTO(Integer id, int customerId, Date orderDate, AddressDTO addressDTO, List<OrderLineDTO> orderLines, Integer giro) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.addressDTO = addressDTO;
        this.orderLines = orderLines;
        this.giro = giro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getGiro() { return giro; }

}
