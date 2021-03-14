package Exceptions;

/**
 * Checked exception for when a price would become negative.
 */
public class NegativePriceException extends Exception {

    /**
     * One parameter constructor.
     * Calls the superclass constructor.
     *
     * @param errorMessage The error message of the exception, represented as
     *                     a string.
     */
    public NegativePriceException(String errorMessage) {
        super(errorMessage);
    }
}
