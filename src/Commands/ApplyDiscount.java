package Commands;

import Exceptions.DiscountNotFoundException;
import Exceptions.NegativePriceException;
import Main_Logic.Discount;
import Main_Logic.Store;

/**
 * Represents a command for applying a discount to the products of the store.
 * Extends the Command class.
 */
public class ApplyDiscount extends Command  {

    /**
     * Calls the applyDiscount method on the store.
     *
     * @param store      The store that the action will be executed on,
     *                   represented as an object of type Store.
     * @param parameters The parameters of the action to be executed,
     */
    @Override
    public void execute(Store store, String[] parameters) {
        try {
            if(parameters[0].equals("PERCENTAGE")) {
                store.applyDiscount(new Discount("",
                        Discount.DiscountType.PERCENTAGE_DISCOUNT,
                        Double.parseDouble(parameters[1])));
            } else if(parameters[0].equals("FIXED")) {
                store.applyDiscount(new Discount("",
                        Discount.DiscountType.FIXED_DISCOUNT,
                        Double.parseDouble(parameters[1])));
            }
        } catch (DiscountNotFoundException | NegativePriceException e) {
            e.printStackTrace();
        }
    }
}
