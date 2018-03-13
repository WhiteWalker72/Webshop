package persistence;

public class ObjectNotFoundException extends Exception {

    ObjectNotFoundException() {

    }

    // Param example: product, updating
    ObjectNotFoundException(String objectName, String action) {
        super("Couldn't find " + objectName + " when " + action + ".");
    }

}
