package domain.component;

import dto.ProductDTO;
import exceptions.InvalidAmountException;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistence.PersistenceServices;

import static org.junit.jupiter.api.Assertions.*;

public class ComponentServicesProductTest {

    private static ComponentServices compServices = new ComponentServices();

    @BeforeAll
    static void init() {
        PersistenceServices.getInstance().switchToTestDatabase();
    }

    @Test
    void productDoesNotExistTest() {
        assertEquals(null, compServices.getProduct(1));
    }

    @Test
    void addAndDeleteProductTest() {
        try {
            addTestProduct();
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }
        assertNotEquals(null, compServices.getProduct(1));

        try {
            deleteTestProduct();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(null, compServices.getProduct(1));
    }

    @Test
    void productAmountLoweringTest() {
        try {
            addTestProduct();
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }
        try {
            compServices.lowerProductAmount(compServices.getProduct(1));
        } catch (InvalidAmountException | ObjectNotFoundException e) {
            e.printStackTrace();
        }

        assertThrows(InvalidAmountException.class, () -> compServices.lowerProductAmount(compServices.getProduct(1)));

        try {
            deleteTestProduct();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deletingProductFailsTest() {
        assertThrows(ObjectNotFoundException.class, () ->  compServices.deleteProduct(compServices.getProduct(1)));
    }

    @Test
    void addingProductTwiceFailsTest() {
        try {
            addTestProduct();
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }
        assertThrows(ObjectAlreadyExistsException.class, this::addTestProduct);

        try {
            deleteTestProduct();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addTestProduct() throws ObjectAlreadyExistsException {
        compServices.addNewProduct(new ProductDTO(1, "test product", "JUnit test", "image"
                , 1)
                .withPrice(5.0));
    }

    private void deleteTestProduct() throws ObjectNotFoundException {
        compServices.deleteProduct(compServices.getProduct(1));
    }

    @AfterAll
    static void tearDown() {
        compServices = null;
    }

}
