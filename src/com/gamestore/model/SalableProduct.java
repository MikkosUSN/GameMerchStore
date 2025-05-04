// Mikkos Thomas
// CST-239 Milestone 4
// 04/30/2025
// I used my own work with reference from website: https://www.baeldung.com/jackson-annotations

package com.gamestore.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents a product that can be sold in the game merchandise store.
 * This is the base class for all salable items in the store inventory,
 * providing common attributes and behaviors for all products.
 */

// Added Jackson annotations for polymorphic deserialization
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Weapon.class, name = "Weapon"),
    @JsonSubTypes.Type(value = Armor.class, name = "Armor"),
    @JsonSubTypes.Type(value = Health.class, name = "Health")
})
public class SalableProduct implements Comparable<SalableProduct>
{
    /** The name of the product */
    private String name;

    /** The description of the product */
    private String description;

    /** The price of the product in dollars */
    private double price;

    /** The quantity of the product available in inventory */
    private int quantity;

    /**
     * Default constructor.
     * Creates an empty product with no initialized values.
     */
    public SalableProduct() {}

    /**
     * Overloaded constructor.
     * Initializes a salable product with specified values.
     * @param name The name of the product
     * @param description The product's description
     * @param price The price in dollars
     * @param quantity The available quantity
     */
    public SalableProduct(String name, String description, double price, int quantity)
    {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Gets the product name.
     * @return the name of the product
     */
    public String getName() { return name; }

    /**
     * Sets the product name.
     * @param name the new name of the product
     */
    public void setName(String name) { this.name = name; }

    /**
     * Gets the product description.
     * @return the description of the product
     */
    public String getDescription() { return description; }

    /**
     * Sets the product description.
     * @param description the new description
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Gets the product price.
     * @return the price of the product
     */
    public double getPrice() { return price; }

    /**
     * Sets the product price.
     * @param price the new price
     */
    public void setPrice(double price) { this.price = price; }

    /**
     * Gets the product quantity.
     * @return the available quantity
     */
    public int getQuantity() { return quantity; }

    /**
     * Sets the product quantity.
     * @param quantity the new quantity
     */
    public void setQuantity(int quantity) { this.quantity = quantity; }

    /**
     * Compares this product with another by name (case-insensitive).
     * @param other the other product to compare
     * @return negative if less, positive if greater, 0 if equal
     */
    @Override
    public int compareTo(SalableProduct other)
    {
        return this.name.compareToIgnoreCase(other.getName());
    }

    /**
     * Returns a string representation of the product, including
     * name, description, price, and quantity.
     * @return formatted string with product details
     */
    @Override
    public String toString()
    {
        return "Name: " + name +
               "\nDescription: " + description +
               "\nPrice: $" + String.format("%.2f", price) +
               "\nQuantity: " + quantity;
    }
}
