package Commands;

import Main_Logic.Store;

/**
 * Represents a command for saving all product information into a CSV file.
 * Extends the Command class.
 */
public class SaveCSV extends Command {

    /**
     * Calls the saveCSV method on the store.
     *
     * @param store      The store that the action will be executed on,
     *                   represented as an object of type Store.
     * @param parameters The parameters of the action to be executed,
     */
    @Override
    public void execute(Store store, String[] parameters) {
        store.saveCSV(parameters[0]);
    }
}
