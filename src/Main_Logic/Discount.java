package Main_Logic;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a price discount for product type objects.
 * Implements Cloneable and Serializable interfaces.
 * Overrides equals, clone and toString methods.
 */
public class Discount implements Cloneable, Serializable {

    /**
     * The name of the discount, represented as a string.
     */
    private String name;
    /**
     * The type of discount, represented as a DiscountType enum.
     */
    private DiscountType discountType;
    /**
     * The actual value of the discount, represented as a double.
     */
    private double value;
    /**
     * The exact last date the discount has been applied on a product,
     * represented as a LocalDateTime type object.
     */
    private LocalDateTime lastDateApplied;

    /**
     * No parameter constructor.
     * Leaves all fields as their default 0 values.
     */
    public Discount() {

    }

    /**
     * Three parameter constructor.
     * Initialises all fields but the last date.
     *
     * @param name         The name of the discount to be initialised.
     * @param discountType The discount type of the discount to be initialised.
     * @param value        The value of the discount to be initialised.
     */
    public Discount(String name, DiscountType discountType, double value) {
        this.name = name;
        this.discountType = discountType;
        this.value = value;
    }

    /**
     * Four parameter constructor (used only for the clone method).
     * Initialises all fields.
     *
     * @param name            The name of the discount to be initialised.
     * @param discountType    The discount type of the discount to be
     *                        initialised.
     * @param value           The value of the discount to be initialised.
     * @param lastDateApplied The last applied date to be initialised.
     */
    public Discount(String name, DiscountType discountType, double value,
                    LocalDateTime lastDateApplied) {
        this.name = name;
        this.discountType = discountType;
        this.value = value;
        this.lastDateApplied = lastDateApplied;
    }

    /**
     * Getter for name field.
     *
     * @return The name of the discount.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name field.
     *
     * @param name The name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for type field.
     *
     * @return The type of the discount.
     */
    public DiscountType getDiscountType() {
        return discountType;
    }

    /**
     * Setter for type field.
     *
     * @param discountType The type to be set.
     */
    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    /**
     * Getter for value field.
     *
     * @return The value of the discount.
     */
    public double getValue() {
        return value;
    }

    /**
     * Setter for value field.
     *
     * @param value The value to be set.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Getter for last date applied field.
     *
     * @return The last date applied of the discount.
     */
    public LocalDateTime getLastDateApplied() {
        return lastDateApplied;
    }

    /**
     * Sets the last applied date from the system time.
     */
    public void setAsAppliedNow() {
        this.lastDateApplied = LocalDateTime.now();
    }

    /**
     * Checks if an object's field values are the same as the ones of this
     * currency type, ignoring the name and last date applied.
     *
     * @param obj The object to be compared with.
     * @return The equality with the object, represented as a boolean values.
     */
    public boolean equalsWithoutDateAndName(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Discount)) {
            return false;
        }

        Discount discount = (Discount) obj;
        return this.discountType.equals(discount.discountType) &&
                (Double.compare(this.value, discount.value) == 0);
    }

    /**
     * Checks if an object's field values are the same as the ones of this
     * currency type, ignoring the last date applied.
     *
     * @param obj The object to be compared with.
     * @return The equality with the object, represented as a boolean values.
     */
    public boolean equalsWithoutDate(Object obj) {
        return equalsWithoutDateAndName(obj) &&
                this.name.equals(((Discount) obj).name);
    }

    /**
     * equals method override.
     * Checks if an object's field values are the same as the ones of this
     * currency type.
     *
     * @param obj The object to be compared with.
     * @return The equality with the object, represented as a boolean values.
     */
    @Override
    public boolean equals(Object obj) {
        return equalsWithoutDate(obj) &&
                this.lastDateApplied.equals(((Discount) obj).lastDateApplied);
    }

    /**
     * clone method override, in order to return a deep copy of the currency
     * type object.
     *
     * @return A deep copy of the currency type object.
     */
    @Override
    public Object clone() {
        Discount discountDeepCopy;
        try {
            discountDeepCopy = (Discount) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            discountDeepCopy =
                    new Discount(this.name, this.discountType, this.value,
                            this.lastDateApplied);
        }
        return discountDeepCopy;
    }

    /**
     * toString method override, for displaying the currency type objects
     * in a more human-readable way
     *
     * @return The string representing the currency type object.
     */
    @Override
    public String toString() {
        return "Discount{" + "name='" + name + '\'' + ", discountType=" +
                discountType + ", value=" + value + ", lastDateApplied=" +
                lastDateApplied + '}';
    }

    /**
     * Enum representing the 2 possible discount types: percentage and fixed.
     */
    public enum DiscountType {
        PERCENTAGE_DISCOUNT, FIXED_DISCOUNT
    }
}
