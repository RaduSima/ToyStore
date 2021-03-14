package Commands;

import Main_Logic.Store;

/**
 * Represents a command.
 * Implements a Command Design Pattern.
 */
public abstract class Command {

    /**
     * Executes an action depending on implementation of child of Command
     * class.
     *
     * @param store      The store that the action will be executed on,
     *                   represented as an object of type Store.
     * @param parameters The parameters of the action to be executed,
     *                   represented as an array of strings.
     */
    public abstract void execute(Store store, String[] parameters);
}
