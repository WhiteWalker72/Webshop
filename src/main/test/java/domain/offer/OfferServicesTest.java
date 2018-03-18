package domain.offer;

import domain.component.ComponentServices;
import dto.OfferDTO;
import dto.ProductDTO;
import exceptions.InvalidDateException;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistence.PersistenceServices;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class OfferServicesTest {

    private static OfferServices offerServices;

    @BeforeAll
    static void init() {
        PersistenceServices.getInstance().switchToTestDatabase();
        offerServices = OfferServices.getInstance();

        // An offer needs a product
        try {
            ComponentServices.getInstance().addNewProduct(new ProductDTO(1, "test product", "JUnit test", "image"
                    , 5.0, 1));
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    @Test
    void offersDoNotExistTest() {
        assertTrue(offerServices.getActiveOffers().isEmpty());
    }

    @Test
    void addAndDeleteOfferTest() {
        assertTrue(offerServices.getAllOffers().isEmpty());

        try {
            addTestOffer();
        } catch (ObjectAlreadyExistsException | InvalidDateException e) {
            e.printStackTrace();
        }
        assertFalse(offerServices.getAllOffers().isEmpty());

        try {
            deleteTestOffer();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }

        assertTrue(offerServices.getAllOffers().isEmpty());
    }

    @Test
    void addingOfferTwiceTest() {
        try {
            addTestOffer();
        } catch (ObjectAlreadyExistsException | InvalidDateException e) {
            e.printStackTrace();
        }
        assertThrows(ObjectAlreadyExistsException.class, this::addTestOffer);

        try {
            deleteTestOffer();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void offerIsActiveTest() {
        assertTrue(offerServices.getActiveOffers().isEmpty());
        try {
            addTestOffer();
        } catch (ObjectAlreadyExistsException | InvalidDateException e) {
            e.printStackTrace();
        }
        assertFalse(offerServices.getActiveOffers().isEmpty());

        try {
            deleteTestOffer();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        assertTrue(offerServices.getActiveOffers().isEmpty());
    }

    @Test
    void offerEndDateInPastTest() {
        Date startDate = new Date(System.currentTimeMillis() - 50);
        Date endDate = new Date(System.currentTimeMillis() - 1);
        assertThrows(InvalidDateException.class, () -> offerServices.addNewOffer(new OfferDTO(1, startDate, endDate, "Hele goedkope mand!", 8.99, 1)));

        // Make sure it's not added
        assertTrue(offerServices.getActiveOffers().isEmpty());
    }

    @Test
    void offerStartDateBiggerThanEndTest() {
        Date startDate = new Date(System.currentTimeMillis() + 101);
        Date endDate = new Date(System.currentTimeMillis() + 100);
        assertThrows(InvalidDateException.class, () -> offerServices.addNewOffer(new OfferDTO(1, startDate, endDate, "Hele goedkope mand!", 8.99, 1)));

        // Make sure it's not added
        assertTrue(offerServices.getActiveOffers().isEmpty());
    }

    private void addTestOffer() throws ObjectAlreadyExistsException, InvalidDateException {
        Date startDate = new Date(System.currentTimeMillis() - 1000000000);
        Date endDate = new Date(System.currentTimeMillis() + 1000000000);
        offerServices.addNewOffer(new OfferDTO(1,  startDate, endDate, "Hele goedkope mand!", 8.99, 1));
    }

    private void deleteTestOffer() throws ObjectNotFoundException {
        offerServices.deleteOffer(1);
    }

    @AfterAll
    static void tearDown() {
        offerServices = null;

        try {
            ComponentServices.getInstance().deleteProduct(ComponentServices.getInstance().getProduct(1));
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

}
