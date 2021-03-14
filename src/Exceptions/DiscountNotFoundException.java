package Exceptions;

/**
 * Checked exception for when a discount does not exist in the store.
 */
public class DiscountNotFoundException extends Exception {

    /**
     * One parameter constructor.
     * Calls the superclass constructor.
     *
     * @param errorMessage The error message of the exception, represented as
     *                     a string.
     */
    public DiscountNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
