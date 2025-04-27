// Mikkos Thomas
// CST-239
// 04/23/2025
// I used my own work

package com.gamestore.model;

/**
 * Represents a product that can be sold in the game merchandise store.
 * This is the base class for all salable items in the store inventory,
 * providing common attributes and behaviors for all products.
 */
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
    public SalableProduct() 
    {
        // Default constructor with no initialization
    }
    
    /**
     * Constructs a new SalableProduct with specified attributes.
     * @param name The name of the product
     * @param description The description of the product
     * @param price The price of the product in dollars
     * @param quantity The quantity available in inventory
     */
    public SalableProduct(String name, String description, double price, int quantity) 
    {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
    
    /**
     * Gets the name of the product.
     * @return The product name
     */
    public String getName() 
    {
        return name;
    }
    
    /**
     * Sets the name of the product.
     * @param name The product name to set
     */
    public void setName(String name) 
    {
        this.name = name;
    }
    
    /**
     * Gets the description of the product.
     * @return The product description
     */
    public String getDescription() 
    {
        return description;
    }
    
    /**
     * Sets the description of the product.
     * @param description The product description to set
     */
    public void setDescription(String description) 
    {
        this.description = description;
    }
    
    /**
     * Gets the price of the product.
     * @return The product price in dollars
     */
    public double getPrice() 
    {
        return price;
    }
    
    /**
     * Sets the price of the product.
     * @param price The product price to set in dollars
     */
    public void setPrice(double price) 
    {
        this.price = price;
    }
    
    /**
     * Gets the quantity of the product available in inventory.
     * @return The available quantity
     */
    public int getQuantity() 
    {
        return quantity;
    }
    
    /**
     * Sets the quantity of the product available in inventory.
     * @param quantity The available quantity to set
     */
    public void setQuantity(int quantity) 
    {
        this.quantity = quantity;
    }
    
    /**
     * Compares this product with another product based on name (case-insensitive).
     * @param other The other product to compare with
     * @return A negative integer, zero, or a positive integer as this product's name
     * is less than, equal to, or greater than the other product's name
     */
    @Override
    public int compareTo(SalableProduct other) 
    {
        return this.name.compareToIgnoreCase(other.getName());
    }
    
    /**
     * Returns a string representation of the product.
     * Includes the name, description, price (formatted to 2 decimal places),
     * and available quantity.
     * @return A formatted string with product details
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
