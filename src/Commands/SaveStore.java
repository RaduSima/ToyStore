package Commands;

import Main_Logic.Store;

/**
 * Represents a command for saving the current state of the store into an
 * object file.
 * Extends the Command class.
 */
public class SaveStore extends Command {

    /**
     * Calls the saveStore method on the store.
     *
     * @param store      The store that the action will be executed on,
     *                   represented as an object of type Store.
     * @param parameters The parameters of the action to be executed,
     */
    @Override
    public void execute(Store store, String[] parameters) {
        store.saveStore(parameters[0]);
    }
}
