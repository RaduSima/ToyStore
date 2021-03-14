package Commands;

import Main_Logic.Store;

/**
 * Represents a command for changing the parity to EURO of a cartain currency
 * in the store.
 * Extends the Command class.
 */
public class UpdateParity extends Command {

    /**
     * Calls the updateParity method on the store.
     *
     * @param store      The store that the action will be executed on,
     *                   represented as an object of type Store.
     * @param parameters The parameters of the action to be executed,
     */
    @Override
    public void execute(Store store, String[] parameters) {
        store.updateParity(parameters[0], Double.parseDouble(parameters[1]));
    }
}
