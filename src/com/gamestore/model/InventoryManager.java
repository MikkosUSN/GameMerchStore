// Mikkos Thomas
// CST-239 Milestone 5
// 5/5/2025
// I used my own work

package com.gamestore.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.gamestore.util.FileService;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Manages the inventory of salable products in the store.
 * Supports adding, retrieving, updating, sorting, and persisting inventory data.
 */
public class InventoryManager {
    /** A list holding all products currently in inventory */
    private List<SalableProduct> products;

    /**
     * Constructor initializes an empty product list.
     */
    public InventoryManager() {
        this.products = new ArrayList<>();
    }

    /**
     * Loads the product inventory from a JSON file using FileService.
     * @param filePath the path to the JSON file to read from
     */
    public void loadFromFile(String filePath) {
        try {
            this.products = FileService.loadInventory(filePath);
            System.out.println("Inventory loaded from JSON.");
        } catch (IOException e) {
            System.out.println("Could not load inventory from file: " + e.getMessage());
        }
    }

    /**
     * Saves the current inventory list to a JSON file using FileService.
     * @param filePath the path to the JSON file to write to
     */
    public void saveToFile(String filePath) {
        try {
            FileService.saveInventory(this.products, filePath);
            System.out.println("Inventory saved to JSON.");
        } catch (IOException e) {
            System.out.println("Could not save inventory to file: " + e.getMessage());
        }
    }

    /**
     * Adds a new product to the inventory list.
     * @param product the product to add
     */
    public void addProduct(SalableProduct product) {
        products.add(product);
    }

    /**
     * Retrieves all products currently in the inventory.
     * @return a list of all salable products
     */
    public List<SalableProduct> getAllProducts() {
        return products;
    }

    /**
     * Retrieves a product by name using case-insensitive matching.
     * @param name the name of the product to retrieve
     * @return the product if found, or null if not found
     */
    public SalableProduct getProduct(String name) {
        for (SalableProduct product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    /**
     * Updates the quantity of a product in the inventory by name.
     * @param name the name of the product
     * @param quantity the new quantity to set
     */
    public void updateQuantity(String name, int quantity) {
        SalableProduct product = getProduct(name);
        if (product != null) {
            product.setQuantity(quantity);
        }
    }

    /**
     * Returns a list of products sorted by name.
     * @param ascending true for ascending, false for descending
     * @return a sorted list of products
     */
    public List<SalableProduct> getProductsSortedByName(boolean ascending) {
        return products.stream()
            .sorted(ascending
                ? Comparator.comparing(SalableProduct::getName, String.CASE_INSENSITIVE_ORDER)
                : Comparator.comparing(SalableProduct::getName, String.CASE_INSENSITIVE_ORDER).reversed())
            .collect(Collectors.toList());
    }

    /**
     * Returns a list of products sorted by price.
     * @param ascending true for ascending, false for descending
     * @return a sorted list of products
     */
    public List<SalableProduct> getProductsSortedByPrice(boolean ascending) {
        return products.stream()
            .sorted(ascending
                ? Comparator.comparingDouble(SalableProduct::getPrice)
                : Comparator.comparingDouble(SalableProduct::getPrice).reversed())
            .collect(Collectors.toList());
    }
}
