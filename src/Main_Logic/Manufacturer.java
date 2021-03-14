package Main_Logic;

import java.io.Serializable;

/**
 * Represents a manufacturer that sells products.
 * Implements Cloneable and Serializable interfaces.
 * Overrides clone and toString methods.
 */
public class Manufacturer implements Cloneable, Serializable {

    /**
     * The name of the manufacturer, represented as a string.
     */
    private String name;

    /**
     * The number of different products the manufacturer sells, represented
     * as an integer.
     */
    private int countProducts;

    /**
     * No argument constructor.
     * Initialises the name as "Not Available"
     */
    public Manufacturer() {
        name = "Not Available";
    }

    /**
     * One argument constructor.
     * Initialises the name of the manufacturer.
     *
     * @param name The name to be initialised, represented as a string.
     */
    public Manufacturer(String name) {
        this.name = name;
    }

    /**
     * Two argument constructor.
     * Initialises the name and the product count of the manufacturer.
     *
     * @param name          The name to be initialised, represented as a string.
     * @param countProducts The product count to be initialised, represented
     *                      as an integer.
     */
    public Manufacturer(String name, int countProducts) {
        this.name = name;
        this.countProducts = countProducts;
    }

    /**
     * Getter for name field.
     *
     * @return Name of manufacturer.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name field.
     *
     * @param name Name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for products count field.
     *
     * @return Products count of manufacturer.
     */
    public int getCountProducts() {
        return countProducts;
    }

    /**
     * Setter for products count.
     *
     * @param countProducts Products count to be set.
     */
    public void setCountProducts(int countProducts) {
        this.countProducts = countProducts;
    }

    /**
     * Increases the products count by 1.
     */
    public void increaseProductsCount() {
        countProducts++;
    }

    /**
     * clone method override, in order to return a deep copy
     * of
     * the manufacturer type object.
     *
     * @return A deep copy of the manufacturer type object.
     */
    @Override
    public Object clone() {
        Manufacturer manufacturerDeepCopy;
        try {
            manufacturerDeepCopy = (Manufacturer) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            manufacturerDeepCopy =
                    new Manufacturer(this.name, this.countProducts);
        }
        return manufacturerDeepCopy;
    }

    /**
     * toString method override, for displaying the manufacturer type objects
     * in a more human-readable way
     *
     * @return The string representing the manufacturer type object.
     */
    @Override
    public String toString() {
        return "Manufacturer{" + "name='" + name + '\'' + ", countProducts=" +
                countProducts + '}';
    }
}
