package persistence.order;

import dto.AddressDTO;
import dto.OrderDTO;
import dto.OrderLineDTO;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistence.PersistenceServices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class OrderDAOTest {

    private static PersistenceServices perServices;

    @BeforeAll
    static void init() {
        PersistenceServices.getInstance().switchToTestDatabase();
        perServices = PersistenceServices.getInstance();
    }

    @Test
    void orderShouldNotExistTest() {
        assertEquals(null, perServices.findOrderById(1 + ""));
    }

    @Test
    void ordersShouldNotExistTest() {
        assertTrue(perServices.getAllOrders().isEmpty());
    }

/*
    @Test
    void addAndDeleteOrderTest() {
        assertEquals(null, perServices.findOrderById(1 + ""));

        try {
            addTestOrder();
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }

        // Order got added so it shouldn't be null
        assertNotEquals(null, perServices.findOrderById(1 + ""));

        try {
            deleteTestOrder();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }

        assertEquals(null, perServices.findAddressById(1 + ""));
    }
*/

/*    @Test
    void insertOrderTwiceFailsTest() {
        try {
            addTestOrder();
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }
        assertThrows(ObjectAlreadyExistsException.class, this::addTestOrder);

        try {
            deleteTestOrder();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    @Test
    void deletingOrderFailsTest() {
        assertThrows(ObjectNotFoundException.class, () -> perServices.deleteOrder(1 + ""));
    }

/*    @Test
    void getOrderLineTest() {
        try {
            addTestOrder();
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }
        OrderDTO orderDTO = perServices.findOrderById(1 + "");
        assertFalse(orderDTO.getOrderLines().isEmpty());
    }*/

    private void addTestOrder() throws ObjectAlreadyExistsException {
        perServices.insertOrder(new OrderDTO(1, 1, new Date()
                , new AddressDTO(1, "street", 1, "1234ZP", "city", "country")
                , new ArrayList<>(Collections.singletonList(new OrderLineDTO
                (1, 1, 5, 20, 1)))));
    }

    private void deleteTestOrder() throws ObjectNotFoundException {
        perServices.deleteOffer(1 + "");
    }

    @AfterAll
    static void tearDown() {
        perServices = null;
    }

}
