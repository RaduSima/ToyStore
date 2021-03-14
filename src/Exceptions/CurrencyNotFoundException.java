package Exceptions;

/**
 * Checked exception for when a currency does not exist in the store.
 */
public class CurrencyNotFoundException extends Exception {

    /**
     * One parameter constructor.
     * Calls the superclass constructor.
     *
     * @param errorMessage The error message of the exception, represented as
     *                     a string.
     */
    public CurrencyNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
