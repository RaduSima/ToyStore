package Exceptions;

/**
 * Checked exception for when a manufacturer already exists.
 */
public class DuplicateManufacturerException extends Exception {

    /**
     * One parameter constructor.
     * Calls the superclass constructor.
     *
     * @param errorMessage The error message of the exception, represented as
     *                     a string.
     */
    public DuplicateManufacturerException(String errorMessage) {
        super(errorMessage);
    }
}
