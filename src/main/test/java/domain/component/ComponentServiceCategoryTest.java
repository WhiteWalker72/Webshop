package domain.component;

import dto.CategoryDTO;
import exceptions.ObjectAlreadyExistsException;
import exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistence.PersistenceServices;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentServiceCategoryTest {

    private static ComponentServices compServices;

    @BeforeAll
    static void init() {
        PersistenceServices.getInstance().switchToTestDatabase();
        compServices = ComponentServices.getInstance();
    }

    @Test
    void categoryDoesNotExistTest() {
        assertEquals(null, compServices.getCategory(1));
    }

    @Test
    void addAndDeleteCategoryTest() {
        try {
            addTestCategory();
        } catch (ObjectAlreadyExistsException e) {
            e.printStackTrace();
        }
        assertNotEquals(null, compServices.getCategory(1));

        try {
            deleteTestCategory();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(null, compServices.getCategory(1));
    }

    @Test
    void deletingCategoryFailsTest() {
        assertThrows(ObjectNotFoundException.class, () ->  compServices.deleteCategory(compServices.getCategory(1)));
    }

    @Test
    void addingCategoryTwiceFailsTest() {
        try {
            addTestCategory();
        } catch (ObjectAlreadyExistsException e) {

        }
        assertThrows(ObjectAlreadyExistsException.class, this::addTestCategory);

        try {
            deleteTestCategory();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void allCategoriesNotNullTest() {
        assertNotEquals(null, compServices.getAllCategories());
    }

    private void addTestCategory() throws ObjectAlreadyExistsException {
        compServices.addNewCategory(new CategoryDTO(1, "test category", "JUnit test"
                , "image", new ArrayList<>()));
    }

    private void deleteTestCategory() throws ObjectNotFoundException {
        compServices.deleteCategory(compServices.getCategory(1));
    }

    @AfterAll
    static void tearDown() {

        compServices = null;
    }

}
