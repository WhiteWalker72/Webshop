package persistence.address;

import dto.AddressDTO;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistence.PersistenceServices;

import static org.junit.jupiter.api.Assertions.*;

public class AddressDAOTest {

    private static PersistenceServices perServices;

    @BeforeAll
    public static void init() {
        PersistenceServices.getInstance().switchToTestDatabase();
        perServices = PersistenceServices.getInstance();
    }

    @AfterAll
    public static void tearDown() {
        perServices = null;
    }

    @Test
    public void addressShouldNotExistTest() {
        assertEquals(null, perServices.findAddressById(1 + ""));
    }

    @Test
    public void addAndDeleteAddressTest() {
        // There should not be an address already
        assertEquals(null, perServices.findAddressById(1 + ""));

        try {
            addTestAddress();
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }

        // Address added so it can't be null
        assertNotEquals(null, perServices.findAddressById(1 + ""));

        try {
            deleteTestAddress();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }

        // Address removed so it doesn't exist
        assertEquals(null, perServices.findAddressById(1 + ""));
    }

    @Test
    public void insertAddressTwiceFailsTest() {
        try {
            addTestAddress();
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }
        assertThrows(ObjectAlreadyExistsException.class, this::addTestAddress);

        try {
            deleteTestAddress();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deletingAddressFailsTest() {
        assertThrows(ObjectNotFoundException.class, () -> perServices.deleteAddress(1 + ""));
    }

    @Test
    public void updatingAddressFailsTest() {
        assertThrows(ObjectNotFoundException.class, () -> perServices.deleteAddress(1 + ""));
    }

    @Test
    public void updateAddressSuccessTest() {
        try {
            addTestAddress();
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }

        // Get the address and make sure its not null
        AddressDTO addressDTO = perServices.findAddressById("1");
        assertNotEquals(null, addressDTO);

        String newStreet = "newStreet";
        String newNumber = "99A";
        String newPostal = "1930TE";
        String newCity = "newCity";
        String newCountry = "newCountry";

        // Update the object and check the if the values changed
        try {
            perServices.updateAddress(new AddressDTO(addressDTO.getId(), newStreet, newNumber, newPostal, newCity, newCountry));
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        addressDTO = perServices.findAddressById("1");
        assertEquals(newStreet, addressDTO.getStreet());
        assertEquals(newNumber, addressDTO.getNumber());
        assertEquals(newPostal, addressDTO.getPostalCode());
        assertEquals(newCity, addressDTO.getCity());
        assertEquals(newCountry, addressDTO.getCountry());

        try {
            deleteTestAddress();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addTestAddress() throws ObjectAlreadyExistsException {
        PersistenceServices.getInstance().insertAddress(new AddressDTO(1, "street", 1 + "", "5910ZP", "Utrecht", "country"));
    }

    private void deleteTestAddress() throws ObjectNotFoundException {
        PersistenceServices.getInstance().deleteAddress("" + 1);
    }

}
