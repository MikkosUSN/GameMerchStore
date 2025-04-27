// Mikkos Thomas
// CST-239
// 04/23/2025
// I used my own work

package com.gamestore.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart for the store.
 */
public class ShoppingCart 
{
    private List<SalableProduct> items;
    private InventoryManager inventory;
    
    /**
     * Constructs a new ShoppingCart
     * @param inventory The inventory manager to interact with
     */
    public ShoppingCart(InventoryManager inventory) 
    {
        this.items = new ArrayList<>();
        this.inventory = inventory;
    }
    
    /**
     * Adds an item to the cart
     * @param productName The name of the product to add
     * @param quantity The quantity to add
     */
    public void addItem(String productName, int quantity) 
    {
        SalableProduct inventoryProduct = inventory.getProduct(productName);
        if (inventoryProduct != null && inventoryProduct.getQuantity() >= quantity) 
        {
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
     * @param productName The name of the product to remove
     */
    public void removeItem(String productName) 
    {
        SalableProduct toRemove = null;
        for (SalableProduct product : items) 
        {
            if (product.getName().equals(productName)) 
            {
                toRemove = product;
                break;
            }
        }
        
        if (toRemove != null) 
        {
            // Return quantity to inventory
            SalableProduct inventoryProduct = inventory.getProduct(productName);
            inventory.updateQuantity(productName, inventoryProduct.getQuantity() + toRemove.getQuantity());
            items.remove(toRemove);
        }
    }
    
    /**
     * Gets all items in the cart
     * @return A list of all items in the cart
     */
    public List<SalableProduct> getItems() 
    {
        return items;
    }
    
    /**
     * Calculates the total price of the cart
     * @return The total price of all items in the cart
     */
    public double getTotalPrice() 
    {
        double total = 0.0;
        for (SalableProduct product : items) 
        {
            total += product.getPrice() * product.getQuantity();
        }
        return total;
    }
    
    /**
     * Clears the cart
     */
    public void clearCart() 
    {
        items.clear();
    }
    
    /**
     * Gets the number of items in the cart
     * @return The number of items in the cart
     */
    public int getItemCount() 
    {
        return items.size();
    }
}
