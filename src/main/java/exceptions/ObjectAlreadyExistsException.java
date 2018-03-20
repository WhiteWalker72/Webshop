package exceptions;

public class ObjectAlreadyExistsException extends Exception {

    public ObjectAlreadyExistsException() {

    }

    public ObjectAlreadyExistsException(String objectName, String inStr) {
        super(objectName + " already exists in " + inStr + ".");
    }

    public ObjectAlreadyExistsException(String description) {
        super(description);
    }


}
