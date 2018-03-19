package domain.payment;

import dto.OrderDTO;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;
import persistence.PersistenceServices;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class OrderManager {

    private final Set<Integer> addingSet = new HashSet<>();
    private final Set<OrderDTO> orderDTOSet = new HashSet<>();

    OrderManager() {

    }

    public void saveOrder(OrderDTO orderDTO) throws ObjectAlreadyExistsException {
        try {
            if (getOrder(orderDTO.getId()) != null) {
                throw new ObjectAlreadyExistsException("orderDTO", "OrderManager");
            }
        } catch (ObjectNotFoundException e) {
            // The order shouldn't exist.
        }

        if (addingSet.contains(orderDTO.getId())) {
            throw new ObjectAlreadyExistsException("orderDTO", "OrderManager addingSet");
        }
        addingSet.add(orderDTO.getId());

        new Timer().schedule(
            new TimerTask() {
                @Override
                public void run() {
                    //TODO: get giro number and save it with the offer

                    try {
                        PersistenceServices.getInstance().insertOrder(orderDTO);
                    } catch (ObjectAlreadyExistsException e) {
                        e.printStackTrace();
                    }
                }
            },
            30000
        );
    }

    public OrderDTO getOrder(int orderId) throws ObjectNotFoundException {
        for (OrderDTO orderDTO : orderDTOSet) {
            if (orderDTO.getId() == orderId) {
                return orderDTO;
            }
        }

        OrderDTO orderDTO = PersistenceServices.getInstance().findOrderById("" + orderId);
        if (orderDTO == null) {
            throw new ObjectNotFoundException("order", "getting order in OrderManager", orderId + "");
        }
        orderDTOSet.add(orderDTO);
        return orderDTO;
    }

}
