package Main_Logic;

import Exceptions.NegativePriceException;

import java.io.Serializable;

/**
 * Represents a product in the store.
 * Implements Cloneable and  Serializable interfaces.
 * Overrides clone and toString methods.
 */
public class Product implements Cloneable, Serializable {

    /**
     * The unique ID of the product, represented as a string.
     */
    private String uniqueId;

    /**
     * The name of the product, represented as a string.
     */
    private String name;

    /**
     * The manufacturer of the product, represented as a manufacturer type
     * object.
     */
    private Manufacturer manufacturer;

    /**
     * The price of the product, represented as a double.
     */
    private double price;

    /**
     * The quantity of the product, represented as an integer.
     */
    private int quantity;

    /**
     * The last applied discount on the product, represented as a Discount
     * type object.
     */
    private Discount discount;

    /**
     * No parameter constructor.
     * Leaves all fields on their default 0 values.
     */
    public Product() {

    }

    /**
     * Six parameter constructor.
     * Initialises all fields.
     *
     * @param uniqueId     The unique ID to be initialised.
     * @param name         The name to be initialised.
     * @param manufacturer The manufacturer to be initialised.
     * @param price        The price to be initialised.
     * @param quantity     The quantity to be initialised.
     * @param discount     The discount to be initialised.
     */
    public Product(String uniqueId, String name, Manufacturer manufacturer,
                   double price, int quantity, Discount discount) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.manufacturer = (Manufacturer) manufacturer.clone();
        this.price = price;
        this.quantity = quantity;
        this.discount = (Discount) discount.clone();
    }

    /**
     * Getter for unique ID.
     *
     * @return The unique ID of the product.
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * Setter for unique ID.
     *
     * @param uniqueId The unique ID to be set.
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * Getter for name.
     *
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name.
     *
     * @param name The name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for manufacturer.
     *
     * @return The manufacturer of the product.
     */
    public Manufacturer getManufacturer() {
        return (Manufacturer) manufacturer.clone();
    }

    /**
     * Setter for manufacturer.
     *
     * @param manufacturer The manufacturer to be set.
     */
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Getter for price.
     *
     * @return The price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for price.
     *
     * @param price The price to be set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter for quantity.
     *
     * @return The quantity of the product.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter for quantity.
     *
     * @param quantity The quantity to be set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Getter for last applied discount.
     *
     * @return The last applied discount to the product.
     */
    public Discount getDiscount() {
        return (Discount) discount.clone();
    }

    /**
     * Setter for last applied discount.
     *
     * @param discount The last applied discount to be set.
     */
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    /**
     * Applies a discount to the price of the product.
     * If an exception is thrown, the price stays the same as before.
     *
     * @param discount The discount to be applied.
     * @throws NegativePriceException Is thrown if the price is to be
     *                                negative after the discount applied
     *                                discount. In this case, the price
     *                                remains unchanged.
     */
    public void applyDiscount(Discount discount) throws NegativePriceException {
        double newPrice = this.price;
        if (discount.getDiscountType() ==
                Discount.DiscountType.PERCENTAGE_DISCOUNT) {
            newPrice -= discount.getValue() / 100 * this.price;
        } else {
            newPrice -= discount.getValue();
        }
        if (newPrice <= 0) {
            throw new NegativePriceException(
                    "Negative price after discount " + "for product " +
                            this.toString());
        }
        this.price = newPrice;
        this.discount = discount;
    }

    /**
     * Displays information about the product, including the currency in
     * which the price is currently saved.
     *
     * @param currencySymbol The currency symbol of the price of the product,
     *                       represented as a string.
     */
    public void printProductInfo(String currencySymbol) {
        System.out.println(this.uniqueId + "," + this.name + "," +
                this.manufacturer.getName() + "," + currencySymbol +
                String.format("%.2f", this.price) + "," + this.quantity);
    }

    /**
     * toString method override, for displaying the Product type objects
     * in a more human-readable way
     *
     * @return The string representing the Product type object.
     */
    @Override
    public String toString() {
        return "Product{" + "uniqueId='" + uniqueId + '\'' + ", name='" + name +
                '\'' + ", manufacturer=" + manufacturer + ", price=" + price +
                ", quantity=" + quantity + ", discount=" + discount + '}';
    }

    /**
     * clone method override, in order to return a deep copy of the Product
     * type object.
     *
     * @return A deep copy of the Product type object.
     */
    @Override
    public Object clone() {
        Product productDeepCopy;
        try {
            productDeepCopy = (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            productDeepCopy =
                    new Product(this.uniqueId, this.name, this.manufacturer,
                            this.price, this.quantity, this.discount);
        }
        productDeepCopy.manufacturer = (Manufacturer) manufacturer.clone();
        if (discount != null) {
            productDeepCopy.discount = (Discount) discount.clone();
        }
        return productDeepCopy;
    }
}
