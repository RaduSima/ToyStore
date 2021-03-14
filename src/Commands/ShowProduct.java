package Commands;

import Main_Logic.Store;

/**
 * Represents a command for displaying information about a product, given a
 * unique ID
 * Extends the Command class.
 */
public class ShowProduct extends Command {

    /**
     * Calls the showProduct method on the store.
     *
     * @param store      The store that the action will be executed on,
     *                   represented as an object of type Store.
     * @param parameters The parameters of the action to be executed,
     */
    @Override
    public void execute(Store store, String[] parameters) {
        store.showProduct(parameters[0]);
    }
}
