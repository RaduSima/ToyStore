package Commands;

import Main_Logic.Store;

/**
 * Represents a command for listing all products in the store.
 * Extends the Command class.
 */
public class ListProducts extends Command {

    /**
     * Calls the listProducts method on the store.
     *
     * @param store      The store that the action will be executed on,
     *                   represented as an object of type Store.
     * @param parameters The parameters of the action to be executed,
     */
    @Override
    public void execute(Store store, String[] parameters) {
        store.listProducts();
    }
}
