package Commands;

import Main_Logic.Store;

/**
 * Represents a command for getting the current saved currency of the store.
 * Extends the Command class.
 */
public class GetStoreCurrency extends Command  {

    /**
     * Calls the showCurrency method on the store.
     *
     * @param store      The store that the action will be executed on,
     *                   represented as an object of type Store.
     * @param parameters The parameters of the action to be executed,
     */
    @Override
    public void execute(Store store, String[] parameters) {
        store.showCurrency();
    }
}
