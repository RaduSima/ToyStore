package Exceptions;

/**
 * Checked exception for when a product already exists.
 */
public class DuplicateProductException extends Exception {

    /**
     * One parameter constructor.
     * Calls the superclass constructor.
     *
     * @param errorMessage The error message of the exception, represented as
     *                     a string.
     */
    public DuplicateProductException(String errorMessage) {
        super(errorMessage);
    }
}
