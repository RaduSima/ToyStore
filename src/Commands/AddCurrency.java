package Commands;

import Main_Logic.Store;

/**
 * Represents a command for adding a new currency to the store.
 * Extends the Command class.
 */
public class AddCurrency extends Command {

    /**
     * Calls the createCurrency method on the store.
     *
     * @param store      The store that the action will be executed on,
     *                   represented as an object of type Store.
     * @param parameters The parameters of the action to be executed,
     */
    @Override
    public void execute(Store store, String[] parameters) {
        store.createCurrency(parameters[0], parameters[1],
                Double.parseDouble(parameters[2]));
    }
}
