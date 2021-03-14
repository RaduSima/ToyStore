package Commands;

import Main_Logic.Store;

/**
 * Represents a command for loading an object file into the store (the object
 * file represents an earlier save state of the store).
 * Extends the Command class.
 */
public class LoadStore extends Command {

    /**
     * Calls the loadStore method on the store.
     *
     * @param store      The store that the action will be executed on,
     *                   represented as an object of type Store.
     * @param parameters The parameters of the action to be executed,
     */
    @Override
    public void execute(Store store, String[] parameters) {
        store.loadStore(parameters[0]);
    }
}
