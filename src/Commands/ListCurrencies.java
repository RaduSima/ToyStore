package Commands;

import Main_Logic.Store;

/**
 * Represents a command for listing all the currencies in the store.
 * Extends the Command class.
 */
public class ListCurrencies extends Command {

    /**
     * Calls the listCurrencies method on the store.
     *
     * @param store      The store that the action will be executed on,
     *                   represented as an object of type Store.
     * @param parameters The parameters of the action to be executed,
     */
    @Override
    public void execute(Store store, String[] parameters) {
        store.listCurrencies();
    }
}
