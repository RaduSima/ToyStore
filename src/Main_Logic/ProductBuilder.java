package Main_Logic;

/**
 * Builder for the Product type objects
 * Implements a Builder Design Pattern.
 */
public class ProductBuilder {

    /**
     * The product to be built.
     */
    private final Product product;


    /**
     * No parameter constructor.
     * Initialises the product field to a new object of type Product.
     */
    public ProductBuilder() {
        product = new Product();
    }

    /**
     * Sets a unique ID to the product.
     *
     * @param uniqueId The unique ID to be set.
     * @return This product builder.
     */
    public ProductBuilder withUniqueId(String uniqueId) {
        product.setUniqueId(uniqueId);
        return this;
    }

    /**
     * Sets a name to the product.
     *
     * @param name The name to be set.
     * @return This product builder.
     */
    public ProductBuilder withName(String name) {
        product.setName(name);
        return this;
    }

    /**
     * Sets a manufacturer to the product.
     *
     * @param manufacturer The manufacturer to be set.
     * @return This product builder.
     */
    public ProductBuilder withManufacturer(Manufacturer manufacturer) {
        product.setManufacturer(manufacturer);
        return this;
    }

    /**
     * Sets a price to the product.
     *
     * @param price The price to be set.
     * @return This product builder.
     */
    public ProductBuilder withPrice(double price) {
        product.setPrice(price);
        return this;
    }

    /**
     * Sets a quantity to the product.
     *
     * @param quantity The quantity to be set.
     * @return This product builder.
     */
    public ProductBuilder withQuantity(int quantity) {
        product.setQuantity(quantity);
        return this;
    }

    /**
     * Sets a discount to the product.
     *
     * @param discount The discount to be set.
     * @return This product builder.
     */
    public ProductBuilder withDiscount(Discount discount) {
        product.setDiscount(discount);
        return this;
    }

    /**
     * Returns the built product.
     *
     * @return The built product.
     */
    public Product build() {
        return product;
    }
}
