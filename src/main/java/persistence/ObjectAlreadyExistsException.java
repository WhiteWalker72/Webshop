package persistence;

public class ObjectAlreadyExistsException extends Exception {

    ObjectAlreadyExistsException() {

    }

    ObjectAlreadyExistsException(String objectName) {
        super(objectName + " already exists in the database.");
    }

}
