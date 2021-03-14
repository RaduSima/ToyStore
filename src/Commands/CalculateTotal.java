package Commands;

import Main_Logic.Store;

/**
 * Represents a command for calculating the total price of certain products,
 * based on unique IDs.
 * Extends the Command class.
 */
public class CalculateTotal extends Command  {

    /**
     * Calls the showTotal method on the store.
     *
     * @param store      The store that the action will be executed on,
     *                   represented as an object of type Store.
     * @param parameters The parameters of the action to be executed,
     */
    @Override
    public void execute(Store store, String[] parameters) {
        store.showTotal(parameters);
    }
}
