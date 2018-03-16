package exceptions;

public class ObjectNotFoundException extends Exception {

    // Param example: product, updating
    public ObjectNotFoundException(String objectName, String action) {
        super("Couldn't find " + objectName + " when " + action + ".");
    }

    public ObjectNotFoundException(String objectName, String action, String id) {
        super("Couldn't find " + objectName + " with id: '" + id + "' " + " when " + action + ".");
    }

}
