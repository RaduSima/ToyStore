package Commands;

import Exceptions.CurrencyNotFoundException;
import Main_Logic.Store;

/**
 * Represents a command for setting a new store currency.
 * Extends the Command class.
 */
public class SetStoreCurrency extends Command {

    /**
     * Calls the changeCurrency method on the store.
     *
     * @param store      The store that the action will be executed on,
     *                   represented as an object of type Store.
     * @param parameters The parameters of the action to be executed,
     */
    @Override
    public void execute(Store store, String[] parameters) {
        try {
            store.changeCurrency(parameters[0]);
        } catch (CurrencyNotFoundException e) {
            e.printStackTrace();
        }
    }
}
