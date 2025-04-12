// Mikkos Thomas
// CST-239
// 04/10/2025
// This is my own work

package com.gamestore.model;

/**
 * Represents a product that can be sold in the game merchandise store.
 */
public class SalableProduct {
    private String name;
    private String description;
    private double price;
    private int quantity;
    
    /**
     * Default constructor
     */
    public SalableProduct() {
    }
    
    /**
     * Constructs a new SalableProduct with specified attributes
     */
    public SalableProduct(String name, String description, double price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
    
    // Getters and setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return "Name: " + name +
               "\nDescription: " + description +
               "\nPrice: $" + String.format("%.2f", price) +
               "\nQuantity: " + quantity;
    }
}
