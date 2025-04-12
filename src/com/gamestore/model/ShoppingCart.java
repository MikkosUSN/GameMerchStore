// Mikkos Thomas
// CST-239
// 04/10/2025
// This is my own work

package com.gamestore.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart for the store.
 */
public class ShoppingCart {
    private List<SalableProduct> items;
    private InventoryManager inventory;
    
    /**
     * Constructs a new ShoppingCart
     */
    public ShoppingCart(InventoryManager inventory) {
        this.items = new ArrayList<>();
        this.inventory = inventory;
    }
    
    /**
     * Adds an item to the cart
     */
    public void addItem(String productName, int quantity) {
        SalableProduct inventoryProduct = inventory.getProduct(productName);
        if (inventoryProduct != null && inventoryProduct.getQuantity() >= quantity) {
            // Create a new product for the cart
            SalableProduct cartProduct = new SalableProduct(
                inventoryProduct.getName(),
                inventoryProduct.getDescription(),
                inventoryProduct.getPrice(),
                quantity
            );
            
            items.add(cartProduct);
            
            // Update inventory
            inventory.updateQuantity(productName, inventoryProduct.getQuantity() - quantity);
        }
    }
    
    /**
     * Removes an item from the cart
     */
    public void removeItem(String productName) {
        SalableProduct toRemove = null;
        for (SalableProduct product : items) {
            if (product.getName().equals(productName)) {
                toRemove = product;
                break;
            }
        }
        
        if (toRemove != null) {
            // Return quantity to inventory
            SalableProduct inventoryProduct = inventory.getProduct(productName);
            inventory.updateQuantity(productName, 
                inventoryProduct.getQuantity() + toRemove.getQuantity());
            
            items.remove(toRemove);
        }
    }
    
    /**
     * Gets all items in the cart
     */
    public List<SalableProduct> getItems() {
        return items;
    }
    
    /**
     * Calculates the total price of the cart
     */
    public double getTotalPrice() {
        double total = 0.0;
        for (SalableProduct product : items) {
            total += product.getPrice() * product.getQuantity();
        }
        return total;
    }
    
    /**
     * Clears the cart
     */
    public void clearCart() {
        items.clear();
    }
    
    /**
     * Gets the number of items in the cart
     */
    public int getItemCount() {
        return items.size();
    }
}
