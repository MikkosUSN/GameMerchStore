/**
 * Mikkos Thomas
 * CST-239 Milestone 7
 * 5/20/2025
 * I used my own work with reference from website:
 * https://mygcuedu6961.sharepoint.com/:w:/r/sites/CSETGuides/_layouts/15/Doc.aspx?sourcedoc=%7B3C90DE60-F1E6-4324-AE2D-AC0DCB1DBE9C%7D&file=CST-239-RS-Activity7Guide.docx&action=default&mobileredirect=true
 */
package com.gamestore.test;

import com.gamestore.model.InventoryManager;
import com.gamestore.model.SalableProduct;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Unit tests for InventoryManager using inventory.json data.
 * This test class validates the functionality of the InventoryManager class
 * by loading real product data from a JSON file and testing various operations
 * such as retrieving, updating, and sorting products.
 */
public class InventoryManagerTest {
    
    /**
     * Path to the JSON file containing inventory data for testing
     */
    private static final String JSON_PATH = "inventory.json";
    
    /**
     * The inventory manager instance being tested
     */
    private InventoryManager manager;
    
    /**
     * Sets up the test environment before each test method.
     * Loads product data from JSON file and initializes the inventory manager
     * with these products.
     * @throws Exception If there's an error reading or parsing the JSON file
     */
    @Before
    public void setup() throws Exception {
        // Create Jackson ObjectMapper to deserialize JSON
        ObjectMapper mapper = new ObjectMapper();
        
        // Read products from JSON file into a list
        List<SalableProduct> products = mapper.readValue(new File(JSON_PATH),
                new TypeReference<List<SalableProduct>>() {});
        
        // Initialize inventory manager
        manager = new InventoryManager();
        
        // Add all products to the inventory manager
        for (SalableProduct product : products) {
            manager.addProduct(product);
        }
    }
    
    /**
     * Tests that getAllProducts() returns the correct number of products.
     * Verifies that the inventory contains at least the expected number of products
     * from the JSON file.
     */
    @Test
    public void testGetAllProductsCount() {
        // Get all products from the inventory manager
        List<SalableProduct> all = manager.getAllProducts();
        
        // Verify that the inventory contains at least 25 products
        // (the minimum expected from the JSON file)
        assertTrue("Inventory should contain at least 25 products", all.size() >= 25);
    }
    
    /**
     * Tests the ability to retrieve a product by its name.
     * Verifies that the correct product is returned based on the name.
     */
    @Test
    public void testGetProductByName() {
        // Retrieve a specific product by name
        SalableProduct found = manager.getProduct("Quantum Blade");
        
        // Verify the product was found
        assertNotNull("Product should not be null", found);
        
        // Verify the product has the correct name
        assertEquals("Product name should match", "Quantum Blade", found.getName());
    }
    
    /**
     * Tests the ability to update a product's quantity.
     * Verifies that the quantity is correctly updated in the inventory.
     */
    @Test
    public void testUpdateQuantity() {
        // Update the quantity of a specific product
        manager.updateQuantity("Plasma Cannon", 99);
        
        // Retrieve the updated product
        SalableProduct updated = manager.getProduct("Plasma Cannon");
        
        // Verify the quantity was updated correctly
        assertEquals("Product quantity should be updated", 99, updated.getQuantity());
    }
    
    /**
     * Tests sorting products by name in ascending order.
     * Verifies that products are correctly sorted alphabetically.
     */
    @Test
    public void testSortByNameAscending() {
        // Get products sorted by name in ascending order
        List<SalableProduct> sorted = manager.getProductsSortedByName(true);
        
        // Verify that the first product name comes before or is equal to the second product name
        // (case-insensitive comparison)
        assertTrue("Products should be sorted alphabetically",
                sorted.get(0).getName().compareToIgnoreCase(sorted.get(1).getName()) <= 0);
    }
    
    /**
     * Tests sorting products by price in descending order.
     * Verifies that products are correctly sorted from highest to lowest price.
     */
    @Test
    public void testSortByPriceDescending() {
        // Get products sorted by price in descending order
        List<SalableProduct> sorted = manager.getProductsSortedByPrice(false);
        
        // Verify that the first product price is greater than or equal to the second product price
        assertTrue("Products should be sorted by price in descending order",
                sorted.get(0).getPrice() >= sorted.get(1).getPrice());
    }
}
