// Mikkos Thomas
// CST-239
// 04/23/2025
// I used my own work

package com.gamestore.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the inventory of products in the store.
 */
public class InventoryManager 
{
    private List<SalableProduct> products;
    
    /**
     * Constructs a new InventoryManager
     */
    public InventoryManager() 
    {
        this.products = new ArrayList<>();
    }
    
    /**
     * Adds a product to the inventory
     * @param product The product to add to inventory
     */
    public void addProduct(SalableProduct product) 
    {
        products.add(product);
    }
    
    /**
     * Gets all products in the inventory
     * @return A list of all products in inventory
     */
    public List<SalableProduct> getAllProducts() 
    {
        return products;
    }
    
    /**
     * Gets a product by name
     * @param name The name of the product to find
     * @return The product if found, null otherwise
     */
    public SalableProduct getProduct(String name) 
    {
        for (SalableProduct product : products) 
        {
            if (product.getName().equals(name)) 
            {
                return product;
            }
        }
        return null;
    }
    
    /**
     * Updates the quantity of a product
     * @param name The name of the product to update
     * @param quantity The new quantity to set
     */
    public void updateQuantity(String name, int quantity) 
    {
        SalableProduct product = getProduct(name);
        if (product != null) 
        {
            product.setQuantity(quantity);
        }
    }
}
