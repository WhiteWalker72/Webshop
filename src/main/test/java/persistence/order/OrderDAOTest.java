package persistence.order;

import exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistence.PersistenceServices;

import static org.junit.jupiter.api.Assertions.*;

public class OrderDAOTest {

    private static PersistenceServices perServices;

    @BeforeAll
    public static void init() {
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

    @Test
    public void deletingOrderFailsTest() {
        assertThrows(ObjectNotFoundException.class, () -> perServices.deleteOrder(1 + ""));
    }

    @AfterAll
    public  static void tearDown() {
        perServices = null;
    }

}
