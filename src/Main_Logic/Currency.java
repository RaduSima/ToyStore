package Main_Logic;

import java.io.Serializable;

/**
 * Represents a currency.
 * Implements Serializable interface.
 * Overrides toString method.
 */
public class Currency implements Serializable {

    /**
     * The name of the currency, represented as a string.
     */
    private String name;

    /**
     * The symbol of the currency, represented as a string.
     */
    private String symbol;

    /**
     * The parity to the Euro currency, represented as a double.
     */
    private double parityToEur;

    /**
     * No parameter constructor.
     * Initialises all parameters as for the Euro currency (it is the default
     * one).
     */
    public Currency() {
        name = "EUR";
        symbol = "€";
        parityToEur = 1;
    }

    /**
     * Three parameter constructor.
     * Initialises all parameters.
     *
     * @param name        The name of the currency to be initialised.
     * @param symbol      The symbol of the currency to be initialised.
     * @param parityToEur The parityToEur of the currency to be initialised.
     */
    public Currency(String name, String symbol, double parityToEur) {
        this.name = name;
        this.symbol = symbol;
        this.parityToEur = parityToEur;
    }

    /**
     * Getter for the currency name.
     *
     * @return The name of the currency.
     */
    public String getName() {
        return name;
    }

    /**
     * Currency name setter.
     *
     * @param name The name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the currency symbol.
     *
     * @return The symbol of the currency.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Currency symbol setter.
     *
     * @param symbol The symbol to be set.
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Getter for the currency parity to Euro.
     *
     * @return The parity to Euro of the currency.
     */
    public double getParityToEur() {
        return parityToEur;
    }

    /**
     * Currency parity to Euro setter.
     *
     * @param parityToEUR The parity to Euro to be set.
     */
    public void updateParity(double parityToEUR) {
        this.parityToEur = parityToEUR;
    }

    /**
     * toString method override, for displaying the currency type objects
     * in a more human-readable way
     *
     * @return The string representing the currency type object.
     */
    @Override
    public String toString() {
        return "Currency{" + "name='" + name + '\'' + ", symbol='" + symbol +
                '\'' + ", parityToEur=" + parityToEur + '}';
    }
}
