/**
 * Mikkos Thomas
 * CST-239 Milestone 6
 * 5/15/2025
 * I used my own work
 */

package com.gamestore.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart for the store.
 * Manages the collection of items a customer intends to purchase.
 * Provides methods for adding, removing, and managing cart items
 * while maintaining proper inventory synchronization.
 */
public class ShoppingCart {
    /** List of products currently in the shopping cart */
    private List<SalableProduct> items;
    
    /** Reference to the inventory manager for stock validation and updates */
    private InventoryManager inventory;
    
    /**
     * Constructs a new ShoppingCart with a reference to the store inventory.
     * The inventory reference is used to validate product availability
     * and update stock levels when items are added to or removed from the cart.
     * @param inventory The inventory manager to interact with
     */
    public ShoppingCart(InventoryManager inventory) {
        this.items = new ArrayList<>();
        this.inventory = inventory;
    }

    /**
     * Adds an item to the cart if available in inventory.
     * Creates a new product instance for the cart to avoid modifying the original inventory item.
     * Updates the inventory quantity to reflect the items being held in the cart.
     * @param productName The name of the product to add
     * @param quantity The quantity to add
     */
    public void addItem(String productName, int quantity) {
        // Get the product from inventory to check availability
        SalableProduct inventoryProduct = inventory.getProduct(productName);
        
        // Verify product exists and has sufficient quantity
        if (inventoryProduct != null && inventoryProduct.getQuantity() >= quantity) {
            // Create a new product instance for the cart with the requested quantity
            SalableProduct cartProduct = new SalableProduct(
                inventoryProduct.getName(),
                inventoryProduct.getDescription(),
                inventoryProduct.getPrice(),
                quantity
            );
            items.add(cartProduct);
            
            // Update inventory to reflect items now in cart
            inventory.updateQuantity(productName, inventoryProduct.getQuantity() - quantity);
        }
    }

    /**
     * Removes an item from the cart completely.
     * Returns all units of the product to inventory.
     * @param productName The name of the product to remove
     */
    public void removeItem(String productName) {
        // Find the product in the cart
        SalableProduct toRemove = null;
        for (SalableProduct product : items) {
            if (product.getName().equals(productName)) {
                toRemove = product;
                break;
            }
        }
        
        // If product was found in cart, remove it and update inventory
        if (toRemove != null) {
            // Return quantity to inventory
            SalableProduct inventoryProduct = inventory.getProduct(productName);
            inventory.updateQuantity(productName, inventoryProduct.getQuantity() + toRemove.getQuantity());
            items.remove(toRemove);
        }
    }
    
    /**
     * Removes a specific quantity of an item from the cart.
     * Returns the specified quantity back to inventory.
     * If all units of an item are removed, the item is completely removed from the cart.
     * @param productName The name of the product to remove
     * @param quantityToRemove The quantity to remove
     * @return true if successful, false if the product wasn't found or quantity was invalid
     */
    public boolean removeItemQuantity(String productName, int quantityToRemove) {
        // Validate quantity is positive
        if (quantityToRemove <= 0) {
            return false;
        }
        
        // Find the product in the cart
        for (SalableProduct product : items) {
            if (product.getName().equals(productName)) {
                // Check if trying to remove more than what's in cart
                if (quantityToRemove > product.getQuantity()) {
                    return false;
                }
                
                // Return the removed quantity to inventory
                SalableProduct inventoryProduct = inventory.getProduct(productName);
                inventory.updateQuantity(productName, inventoryProduct.getQuantity() + quantityToRemove);
                
                // Update cart item quantity
                if (quantityToRemove == product.getQuantity()) {
                    // Remove the item completely if all units are removed
                    items.remove(product);
                } else {
                    // Otherwise just reduce the quantity
                    product.setQuantity(product.getQuantity() - quantityToRemove);
                }
                return true;
            }
        }
        return false; // Product not found in cart
    }

    /**
     * Gets all items currently in the cart.
     * @return A list of all items in the cart
     */
    public List<SalableProduct> getItems() {
        return items;
    }

    /**
     * Calculates the total price of all items in the cart.
     * Multiplies each product's price by its quantity and sums the results.
     * @return The total price of all items in the cart
     */
    public double getTotalPrice() {
        double total = 0.0;
        for (SalableProduct product : items) {
            total += product.getPrice() * product.getQuantity();
        }
        return total;
    }

    /**
     * Clears all items from the cart.
     * Note: This method does not return items to inventory as it's typically
     * called after checkout when inventory has already been finalized.
     */
    public void clearCart() {
        items.clear();
    }

    /**
     * Gets the number of distinct items in the cart.
     * Note: This counts the number of different products, not the total quantity.
     * @return The number of distinct items in the cart
     */
    public int getItemCount() {
        return items.size();
    }
}
