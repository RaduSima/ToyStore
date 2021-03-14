package Exceptions;

/**
 * Checked exception for when a given command does not exist.
 */
public class CommandNotFoundException extends Exception {

    /**
     * One parameter constructor.
     * Calls the superclass constructor.
     *
     * @param errorMessage The error message of the exception, represented as
     *                     a string.
     */
    public CommandNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
