// Mikkos Thomas
// CST-239
// 04/10/2025
// This is my own work

package com.gamestore.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the inventory of products in the store.
 */
public class InventoryManager {
    private List<SalableProduct> products;
    
    /**
     * Constructs a new InventoryManager
     */
    public InventoryManager() {
        this.products = new ArrayList<>();
    }
    
    /**
     * Adds a product to the inventory
     */
    public void addProduct(SalableProduct product) {
        products.add(product);
    }
    
    /**
     * Gets all products in the inventory
     */
    public List<SalableProduct> getAllProducts() {
        return products;
    }
    
    /**
     * Gets a product by name
     */
    public SalableProduct getProduct(String name) {
        for (SalableProduct product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }
    
    /**
     * Updates the quantity of a product
     */
    public void updateQuantity(String name, int quantity) {
        SalableProduct product = getProduct(name);
        if (product != null) {
            product.setQuantity(quantity);
        }
    }
}
