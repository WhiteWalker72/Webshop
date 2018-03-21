package domain.component;

import dto.CategoryDTO;
import dto.ProductDTO;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistence.PersistenceServices;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ComponentServiceIntegrationTest {

    private static ComponentServices compServices;

    @BeforeAll
    public static void init() {
        PersistenceServices.getInstance().switchToTestDatabase();
        compServices = ComponentServices.getInstance();
    }

    @Test
    public void componentsShouldNotExistTest() {
        assertEquals(null, compServices.getCategory(1));
        assertEquals(null, compServices.getProduct(1));
    }

    @Test
    public void addRemoveProductToCategorySuccessTest() {
        addTestCategory();
        addTestProduct();

        Category category = compServices.getCategory(1);
        Product product = compServices.getProduct(1);

        // Add the product
        try {
            compServices.addProductToCategory(product, category);
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }

        // Check if the product is added
        assertFalse(category.getProductList().isEmpty());

        // Remove the product
        try {
            compServices.removeProductFromCategory(product, category);
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }

        //Check if product got removed
        assertTrue(category.getProductList().isEmpty());

        deleteTestCategory();
        deleteTestProduct();
    }

    @Test
    public void addNullProductToCategoryFailsTest() {
        addTestCategory();
        assertThrows(ObjectNotFoundException.class, () -> compServices.addProductToCategory(null, compServices.getCategory(1)));
        deleteTestCategory();
    }

    @Test
    public void addProductToNullCategoryFailsTest() {
        addTestProduct();
        assertThrows(ObjectNotFoundException.class, () -> compServices.addProductToCategory(compServices.getProduct(1), null));
        deleteTestProduct();
    }

    @Test
    public void removeNullProductFromCategoryFailsTest() {
        addTestCategory();
        assertThrows(ObjectNotFoundException.class, () -> compServices.removeProductFromCategory(null, compServices.getCategory(1)));
        deleteTestCategory();
    }

    @Test
    public void removeProductFromNullCategoryFailsTest() {
        addTestProduct();
        assertThrows(ObjectNotFoundException.class, () -> compServices.removeProductFromCategory(compServices.getProduct(1), null));
        deleteTestProduct();
    }

    @Test
    public void componentsShouldBeDeletedTest() {
        assertEquals(null, compServices.getCategory(1));
        assertEquals(null, compServices.getProduct(1));
    }

    private void addTestCategory() {
        try {
            compServices.addNewCategory(new CategoryDTO(1, "test category", "JUnit test"
                    , "image", new ArrayList<>()));
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    private void deleteTestCategory() {
        try {
            compServices.deleteCategory(compServices.getCategory(1));
        } catch (ObjectNotFoundException e) {

        }
    }

    private void addTestProduct() {
        try {
            compServices.addNewProduct(new ProductDTO(1, "test product", "JUnit test", "image"
                    , 5.0, 1));
        } catch (ObjectAlreadyExistsException e) {

        }
    }

    private void deleteTestProduct(){
        try {
            compServices.deleteProduct(compServices.getProduct(1));
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void tearDown() {
        compServices = null;
    }

}
