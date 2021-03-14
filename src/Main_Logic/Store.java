package Main_Logic;

import Exceptions.*;
import com.opencsv.CSVWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a store.
 * All commands will be acted on objects of this class.
 * Implements Serializable interface.
 * Implements the Singleton design pattern.
 */
public class Store implements Serializable {

    /**
     * The static instance of the Store singleton class.
     */
    private static Store INSTANCE;

    /**
     * A list of discounts, represented as an ArrayList of Discount type
     * objects.
     */
    private ArrayList<Discount> discounts;

    /**
     * The name of the store, represented as a string.
     */
    private String name;

    /**
     * The saved currency of the store at a moment in time, represented as an
     * object of type Currency.
     */
    private Currency currency;

    /**
     * A list of all products loaded into the store, represented as an
     * ArrayList of Product type objects.
     */
    private ArrayList<Product> products;

    /**
     * A list of all manufacturers of the products in the store, represented
     * as an ArrayList of Manufacturer type objects.
     */
    private ArrayList<Manufacturer> manufacturers;

    /**
     * A list of all the currencies loaded into the store, represented as an
     * ArrayList of Currency type objects.
     */
    private ArrayList<Currency> currencies;

    /**
     * No argument constructor.
     * Initialises the store's name to its default value and allocates memory
     * for lists.
     * Also adds the 2 default currencies of the store: EUR and GBP.
     */
    private Store() {
        name = "POO Store";
        currency = new Currency();
        products = new ArrayList<>();
        manufacturers = new ArrayList<>();
        currencies = new ArrayList<>();
        discounts = new ArrayList<>();

        createCurrency("EUR", "€", 1);
        createCurrency("GBP", "£", 1.1);
    }

    /**
     * Gets the store singleton instance.
     *
     * @return The store singleton instance.
     */
    public static Store getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Store();
        }
        return INSTANCE;
    }

    /**
     * Getter for name.
     *
     * @return The name of the store.
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
     * Getter for currency.
     *
     * @return The currency of the store.
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Setter for currency.
     * Sets the current currency of the store to a different one from the
     * currencies list. If the new currency is set successfully,
     * the prices of all products are converted to their respective new prices.
     *
     * @param currencyName The name of the currency to be set.
     * @throws CurrencyNotFoundException If the currency to be added to the
     *                                   list does not exist, the method
     *                                   throws a CurrencyNotFoundException.
     */
    public void changeCurrency(String currencyName)
            throws CurrencyNotFoundException {
        for (Currency c : currencies) {
            if (c.getName().equals(currencyName)) {
                Currency oldCurrency = currency;
                currency = c;
                convertAllPrices(oldCurrency);
                return;
            }
        }
        throw new CurrencyNotFoundException(
                "Currency name " + currencyName + " does " + "not exist.");
    }

    /**
     * Getter for products.
     *
     * @return The products of the store.
     */
    public ArrayList<Product> getProducts() {
        return products;
    }

    /**
     * Setter for products.
     *
     * @param products The products to be set.
     */
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    /**
     * Getter for manufacturers.
     *
     * @return The manufacturers of the store.
     */
    public ArrayList<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    /**
     * Setter for manufacturers.
     *
     * @param manufacturers The manufacturers to be set.
     */
    public void setManufacturers(ArrayList<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }

    /**
     * Getter for currencies.
     *
     * @return The currencies of the store.
     */
    public ArrayList<Currency> getCurrencies() {
        return currencies;
    }

    /**
     * Setter for name.
     *
     * @param currencies The currencies to be set.
     */
    public void setCurrencies(ArrayList<Currency> currencies) {
        this.currencies = currencies;
    }

    /**
     * Reads a CSV file and loads all products it contains into the store.
     * The products prices get converted to the store's current currency.
     * The method uses a CSVParser type object from the org.apache.commons
     * library for the actual file reading.
     *
     * @param filename The name of the file from which the products will be
     *                 read, represented as a string.
     * @return The products list of the store.
     */
    public ArrayList<Product> readCSV(String filename) {

        try {

            //Initialises the CSVParser
            Reader reader = Files.newBufferedReader(Path.of(filename));
            CSVParser csvParser = new CSVParser(reader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader());

            //Reads the whole file as records.
            List<CSVRecord> csvRecords = csvParser.getRecords();

            //Parses the records and builds the products that get added to
            // the products list
            for (CSVRecord csvRecord : csvRecords) {
                String idString = csvRecord.get("uniq_id");
                String nameString = csvRecord.get("product_name");
                String manufacturerString = csvRecord.get("manufacturer");
                String priceString = csvRecord.get("price");
                String quantityString =
                        csvRecord.get("number_available_in_stock");

                if (!priceString.equals("")) {
                    Product newProduct =
                            new ProductBuilder().withUniqueId(idString)
                                    .withName(nameString).withManufacturer(
                                    parseManufacturer(manufacturerString))
                                    .withPrice(
                                            convertStringToPrice(priceString))
                                    .withQuantity(parseQuantity(quantityString))
                                    .build();
                    addProduct(newProduct);
                }
            }

            //Closes the reader
            reader.close();
            csvParser.close();

        } catch (IOException | DuplicateProductException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Writes all the products information in the store to a CSV file.
     * The method uses a CSVWriter type object from the com.opencsv
     * library for the actual file writing.
     *
     * @param filename The name of the file that the products information
     *                 will be written to.
     */
    public void saveCSV(String filename) {
        try {

            //Opens the writer
            FileWriter outputFile = new FileWriter(filename);
            CSVWriter writer = new CSVWriter(outputFile);

            //Writes the header
            writer.writeNext(
                    new String[]{"uniq_id", "product_name", "manufacturer",
                            "price", "number_available_in_stock"});

            //Writes all the products information
            for (Product prod : products) {
                String[] line = {prod.getUniqueId(), prod.getName(),
                        prod.getManufacturer().getName(), currency.getSymbol() +
                        String.format("%.2f", prod.getPrice()),
                        prod.getQuantity() + " NEW"};
                writer.writeNext(line);
            }

            //Closes the writer
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the current state of the store in a binary file, using the
     * writeObject method from the Serializable interface
     *
     * @param filename The path to the file that the current state of the
     *                 store will be written to, represented as a string.
     */
    public void saveStore(String filename) {
        try {
            FileOutputStream outputFile = new FileOutputStream(filename);
            ObjectOutputStream objectOut = new ObjectOutputStream(outputFile);
            objectOut.writeObject(this);

            objectOut.close();
            outputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a prior store state from a binary file.
     *
     * @param filename The path to the file that the prior store state will
     *                 be read from, represented as a string.
     */
    public void loadStore(String filename) {
        try {
            FileInputStream inputFile = new FileInputStream(filename);
            ObjectInputStream objectIn = new ObjectInputStream(inputFile);

            copyStore((Store) objectIn.readObject());

            objectIn.close();
            inputFile.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a product to the products list.
     *
     * @param product The product to be added to the list.
     * @throws DuplicateProductException If the product to be added already
     *                                   exists in the products list, it does
     *                                   not get added and a
     *                                   DuplicateProductException gets thrown.
     */
    public void addProduct(Product product) throws DuplicateProductException {
        for (Product prod : products) {
            if (prod.getUniqueId().equals(product.getUniqueId())) {
                throw new DuplicateProductException(
                        product.toString() + " " + "already exists.");
            }
        }
        products.add(product);
    }

    /**
     * Prints all products information of a certain manufacturer to the output
     * terminal.
     *
     * @param manufacturer The manufacturer of which the products information
     *                     will be displayed.
     */
    public void listProdByMan(Manufacturer manufacturer) {

        //Gets the products of the manufacturer
        ArrayList<Product> wantedProducts =
                getProductsByManufacturer(manufacturer);

        //Displays their information
        for (Product prod : wantedProducts) {
            prod.printProductInfo(currency.getSymbol());
        }
    }

    /**
     * Gets all products that have certain unique IDs.
     *
     * @param IDs The IDs of the desired products, represented as an array of
     *            strings.
     * @return The list of desired products.
     */
    public ArrayList<Product> getProductsByIDs(String[] IDs) {
        ArrayList<Product> wantedProducts = new ArrayList<>();

        for (String id : IDs) {
            for (Product prod : products) {
                if (prod.getUniqueId().equals(id)) {
                    wantedProducts.add(prod);
                }
            }
        }
        return wantedProducts;
    }

    /**
     * Prints the information of a certain product, based on its unique ID,
     * to the output terminal.
     *
     * @param id The ID of the product to be shown.
     */
    public void showProduct(String id) {
        for (Product prod : products) {
            if (prod.getUniqueId().equals(id)) {
                prod.printProductInfo(currency.getSymbol());
            }
        }
    }

    /**
     * Prints the information of all products in the store to the output
     * terminal.
     */
    public void listProducts() {
        for (Product prod : products) {
            prod.printProductInfo(currency.getSymbol());
        }
    }

    /**
     * Prints the total price of products with certain IDs to the output
     * terminal.
     *
     * @param IDs The list of IDs of the products to be printed, represented
     *            as an array of strings.
     */
    public void showTotal(String[] IDs) {
        System.out.println(currency.getSymbol() +
                String.format("%.2f", calculateTotal(getProductsByIDs(IDs))));
        System.out.flush();
    }

    /**
     * Adds a manufacturer to the manufacturers list.
     *
     * @param manufacturer The manufacturer to be added to the list.
     * @throws DuplicateManufacturerException If the manufacturer to be added
     *                                        already
     *                                        exists in the manufacturers list,
     *                                        it does not get added and a
     *                                        DuplicateManufacturerException
     *                                        gets thrown.
     */
    public void addManufacturer(Manufacturer manufacturer)
            throws DuplicateManufacturerException {
        for (Manufacturer man : manufacturers) {
            if (man.getName().equals(manufacturer.getName())) {
                throw new DuplicateManufacturerException(
                        manufacturer.toString() + " already exists.");
            }
        }
        manufacturers.add(manufacturer);
    }

    /**
     * Prints the information of all manufacturers in the store to the output
     * terminal.
     */
    public void listManufacturers() {
        for (Manufacturer man : manufacturers) {
            System.out.println(man.getName());
        }
    }

    /**
     * Adds a new currency to the currencies list.
     * If the currency already exists in the list, it does not get added and
     * is simply returned.
     *
     * @param name        The name of the currency to be added.
     * @param symbol      The symbol of the currency to be added.
     * @param parityToEur The parity to Euro of the currency to be added.
     * @return The newly added currency.
     */
    public Currency createCurrency(String name, String symbol,
                                   double parityToEur) {

        //Checks if the currency already exists in the list
        for (Currency c : currencies) {
            if (c.getSymbol().equals(symbol)) {
                return currencies.get(0);
            }
        }

        //Adds a new currency to the list
        Currency newCurrency = new Currency(name, symbol, parityToEur);
        currencies.add(newCurrency);

        return newCurrency;
    }

    /**
     * Prints all currencies to the output terminal.
     */
    public void listCurrencies() {
        for (Currency c : currencies) {
            System.out.println(c.getName() + " " + c.getParityToEur());
        }
    }

    /**
     * Prints the current currency of the store to the output terminal.
     */
    public void showCurrency() {
        System.out.println(currency.getName());
    }

    /**
     * Updates the parity to Euro of a currency in the list of currencies
     * (and the currently loaded one, if it is the same as the desired one in
     * the list)
     *
     * @param currencyName The name of the currency to be updated,
     *                     represented as a string.
     * @param newParity    The new parity to Euro of the currency, represented
     *                     as a double.
     */
    public void updateParity(String currencyName, double newParity) {

        //Updates the parity of the current currency of the store, if it is
        // the one given as a parameter
        if (currency.getName().equals(currencyName)) {
            currency.updateParity(newParity);
        }

        //Updates the the parity of the currency in the list as well
        for (Currency c : currencies) {
            if (c.getName().equals(currencyName)) {
                c.updateParity(newParity);
                return;
            }
        }
    }

    /**
     * Adds a new discount to the discounts list.
     * If the discount already exists in the list, it does not get added.
     *
     * @param discountType The discount type (fixed or percentage).
     * @param name         The discount name.
     * @param value        The discount value.
     */
    public void createDiscount(Discount.DiscountType discountType, String name,
                               double value) {

        //Checks if the currency already exists in the list
        Discount newDiscount = new Discount(name, discountType, value);
        for (Discount dis : discounts) {
            if (dis.equalsWithoutDate(newDiscount)) {
                return;
            }
        }

        //Adds a new discount to the list
        this.discounts.add(newDiscount);
    }

    /**
     * Applies a certain discount to all products in the store.
     *
     * @param discount The discount to be applied.
     * @throws DiscountNotFoundException If the discount to be applied does
     *                                   not exist in the discounts list, a
     *                                   DiscountNotFoundException is thrown.
     * @throws NegativePriceException    If by applying the discount to the
     *                                   products, at least one's price would
     *                                   become negative, a
     *                                   NegativePriceException is thrown.
     */
    public void applyDiscount(Discount discount)
            throws DiscountNotFoundException, NegativePriceException {
        for (Discount dis : discounts) {

            //Finds the discount in the discount list
            if (dis.equalsWithoutDateAndName(discount)) {
                dis.setAsAppliedNow();

                //Creates a copy of the products list and tries to apply the
                // discounts on it
                ArrayList<Product> temp = new ArrayList<>();
                for (Product prod : products) {
                    temp.add((Product) prod.clone());
                }
                for (Product prod : temp) {
                    prod.applyDiscount(dis);
                }

                //If the previous action did not throw a
                // NegativePriceException, the products list of the store
                // becomes the copied list
                products = temp;
                return;
            }
        }
        throw new DiscountNotFoundException(
                discount.toString() + " not found" + ".");
    }

    /**
     * Prints all discounts information to the output terminal.
     */
    public void listDiscounts() {
        for (Discount disc : discounts) {
            System.out.println(
                    disc.getDiscountType() + " " + disc.getValue() + " " +
                            disc.getName() + " " + disc.getLastDateApplied());
        }
    }

    /**
     * Private helper method.
     * Processes a manufacturer name string from a products CSV file.
     * Helps in the creation of Product type objects when loading a CSV file
     * into the store.
     *
     * @param manufacturerString The name of the manufacturer, represented as
     *                           a string
     * @return The new manufacturer type object.
     */
    private Manufacturer parseManufacturer(String manufacturerString) {

        //If the manufacturer field in the CSV file was empty
        if (manufacturerString.equals("")) {
            return new Manufacturer();
        }

        try {

            //Creates a new manufacturer and adds it to the list
            Manufacturer newManufacturer =
                    new Manufacturer(manufacturerString, 1);
            addManufacturer(newManufacturer);
            return newManufacturer;

            //If it already exists, his product count is increased by 1
        } catch (DuplicateManufacturerException e) {
            for (Manufacturer man : this.manufacturers) {
                if (man.getName().equals(manufacturerString)) {
                    man.increaseProductsCount();
                    return man;
                }
            }
        }
        return new Manufacturer();
    }

    /**
     * Private helper method.
     * Processes the quantity string of a product from a CSV file.
     * Helps in the creation of Product type objects when loading a CSV file
     * into the store.
     *
     * @param quantityString The string containing the quantity of the product.
     * @return The quantity of the product.
     */
    private int parseQuantity(String quantityString) {

        //Empty quantity field in the CSV file case
        if (quantityString.equals("")) return 0;

        //Removes all non-digit characters from the string and parses the
        // rest into an integer
        return Integer.parseInt(quantityString.replaceAll("[^0-9]", ""));
    }

    /**
     * Private helper method.
     * Converts a price string from a CSV file into a double.
     *
     * @param priceString The string containing the price.
     * @return The price of the product.
     */
    private double convertStringToPrice(String priceString) {

        //Gets the symbol of the currency
        String priceCurrencySymbol = priceString.charAt(0) + "";

        //Gets the actual price value
        String priceValueString = priceString.substring(1);

        //Price contains '-' character case
        if (priceValueString.contains('-' + "")) {
            priceValueString = priceValueString
                    .substring(0, priceValueString.indexOf('-'));
        }

        //Removes ',' character from price
        double price = Double.parseDouble(priceValueString.replace(",", ""));

        //Converts the price to the current currency of the store
        try {
            price = convertPrice(price, priceCurrencySymbol);
        } catch (CurrencyNotFoundException e) {
            e.printStackTrace();
        }

        return price;
    }

    /**
     * Private helper function.
     * Converts a price in a certain currency to the current currency of the
     * store.
     *
     * @param price          The price to be converted.
     * @param currencySymbol The old currency symbol of the price.
     * @return The new converted price.
     * @throws CurrencyNotFoundException If the old currency of the price
     *                                   does not exist in the currencies
     *                                   list of the store, a
     *                                   CurrencyNotFoundException is thrown.
     */
    private double convertPrice(double price, String currencySymbol)
            throws CurrencyNotFoundException {
        for (Currency c : currencies) {
            if (c.getSymbol().equals(currencySymbol)) {
                return price * c.getParityToEur() / currency.getParityToEur();
            }
        }
        throw new CurrencyNotFoundException(
                "Currency with symbol " + currencySymbol + " not found.");
    }

    /**
     * Private helper function.
     * Converts all product prices from the store from a certain currency to
     * the current currency of the store.
     *
     * @param oldCurrency The old currency of the prices.
     */
    private void convertAllPrices(Currency oldCurrency) {
        for (Product prod : products) {
            prod.setPrice(prod.getPrice() * oldCurrency.getParityToEur() /
                    currency.getParityToEur());
        }
    }

    /**
     * Private helper function.
     * Gets all products made by a certain manufacturer.
     *
     * @param manufacturer The manufacturer that produces the desired products.
     * @return The products produced by the manufacturer, represented as an
     * ArrayList of Product type objects.
     */
    private ArrayList<Product> getProductsByManufacturer(
            Manufacturer manufacturer) {
        ArrayList<Product> wantedProducts = new ArrayList<>();
        for (Product prod : products) {
            if (prod.getManufacturer().getName()
                    .equals(manufacturer.getName())) {
                wantedProducts.add(prod);
            }
        }
        return wantedProducts;
    }

    /**
     * Calculates the total price of all products in a list.
     *
     * @param products The list of products, represented as an ArrayList of
     *                 Product type objects.
     * @return The total price of the products in the list, represented as a
     * double.
     */
    private double calculateTotal(ArrayList<Product> products) {
        double total = 0;
        for (Product prod : products) {
            total += prod.getPrice();
        }
        return total;
    }

    /**
     * Private helper function.
     * Copies all fields from the store.
     * Helps in the reading of a previous store state from a binary file.
     *
     * @param store The store to be copied.
     */
    private void copyStore(Store store) {
        name = store.name;
        currency = store.currency;
        products = store.products;
        manufacturers = store.manufacturers;
        currencies = store.currencies;
        discounts = store.discounts;
    }
}
