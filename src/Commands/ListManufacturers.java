package Commands;

import Main_Logic.Store;

/**
 * Represents a command for listing all the manufacturers of the products in
 * the store.
 * Extends the Command class.
 */
public class ListManufacturers extends Command {

    /**
     * Calls the listManufacturers method on the store.
     *
     * @param store      The store that the action will be executed on,
     *                   represented as an object of type Store.
     * @param parameters The parameters of the action to be executed,
     */
    @Override
    public void execute(Store store, String[] parameters) {
        store.listManufacturers();
    }
}
