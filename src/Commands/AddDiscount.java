package Commands;

import Main_Logic.Discount;
import Main_Logic.Store;

/**
 * Represents a command for adding a new discount to the store.
 * Extends the Command class.
 */
public class AddDiscount extends Command {

    /**
     * Calls the createDiscount method on the store.
     *
     * @param store      The store that the action will be executed on,
     *                   represented as an object of type Store.
     * @param parameters The parameters of the action to be executed,
     */
    @Override
    public void execute(Store store, String[] parameters) {
        if (parameters[0].equals("PERCENTAGE")) {
            store.createDiscount(Discount.DiscountType.PERCENTAGE_DISCOUNT,
                    parameters[2], Double.parseDouble(parameters[1]));
        } else if (parameters[0].equals("FIXED")) {
            store.createDiscount(Discount.DiscountType.FIXED_DISCOUNT,
                    parameters[2], Double.parseDouble(parameters[1]));
        }
    }
}
