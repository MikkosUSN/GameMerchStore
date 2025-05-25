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
import com.gamestore.model.ShoppingCart;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Unit tests for ShoppingCart using real products from inventory.json.
 * This test class validates the functionality of the ShoppingCart class
 * by testing its interaction with the InventoryManager and SalableProduct classes.
 */
public class ShoppingCartTest {
    
    /**
     * Path to the JSON file containing inventory data for testing
     */
    private static final String JSON_PATH = "inventory.json";
    
    /**
     * The inventory manager instance used for testing
     */
    private InventoryManager inventory;
    
    /**
     * The shopping cart instance being tested
     */
    private ShoppingCart cart;
    
    /**
     * Sets up the test environment before each test method.
     * Loads product data from JSON file, initializes the inventory manager,
     * and creates a new shopping cart.
     * @throws Exception If there's an error reading the JSON file
     */
    @Before
    public void setup() throws Exception {
        // Create Jackson ObjectMapper to deserialize JSON
        ObjectMapper mapper = new ObjectMapper();
        
        // Read products from JSON file into a list
        List<SalableProduct> products = mapper.readValue(new File(JSON_PATH),
                new TypeReference<List<SalableProduct>>() {});
        
        // Initialize inventory manager
        inventory = new InventoryManager();
        
        // Add all products to the inventory
        for (SalableProduct p : products) {
            inventory.addProduct(p);
        }
        
        // Create a new shopping cart with the initialized inventory
        cart = new ShoppingCart(inventory);
    }
    
    /**
     * Tests adding items to the shopping cart.
     * Verifies that:
     * 1. The item is correctly added to the cart
     * 2. The quantity in the cart is correct
     * 3. The inventory quantity is properly decreased
     */
    @Test
    public void testAddItemToCart() {
        // Add 3 Shock Batons to the cart
        cart.addItem("Shock Baton", 3);
        
        // Get all items in the cart
        List<SalableProduct> items = cart.getItems();
        
        // Verify there's exactly one type of item in the cart
        assertEquals(1, items.size());
        
        // Verify the item name is correct
        assertEquals("Shock Baton", items.get(0).getName());
        
        // Verify the quantity is correct
        assertEquals(3, items.get(0).getQuantity());
        
        // Verify inventory quantity has decreased appropriately
        int remaining = inventory.getProduct("Shock Baton").getQuantity();
        assertEquals(9, remaining); // Originally 12
    }
    
    /**
     * Tests removing an entire item from the shopping cart.
     * Verifies that:
     * 1. The item is completely removed from the cart
     * 2. The inventory quantity is properly restored
     */
    @Test
    public void testRemoveItemFromCart() {
        // Add 2 Gauss Pistols to the cart
        cart.addItem("Gauss Pistol", 2);
        
        // Remove the item completely
        cart.removeItem("Gauss Pistol");
        
        // Verify the cart is now empty
        assertEquals(0, cart.getItemCount());
        
        // Verify inventory quantity is restored to original value
        int restoredQty = inventory.getProduct("Gauss Pistol").getQuantity();
        assertEquals(12, restoredQty);
    }
    
    /**
     * Tests removing a partial quantity of an item from the cart.
     * Verifies that:
     * 1. The item quantity in the cart is properly reduced
     * 2. The inventory quantity is properly increased
     * 3. The method returns true for successful removal
     */
    @Test
    public void testRemoveItemQuantity() {
        // Add 4 Railgun Prototypes to the cart
        cart.addItem("Railgun Prototype", 4);
        
        // Remove 2 of them
        boolean result = cart.removeItemQuantity("Railgun Prototype", 2);
        
        // Verify the operation was successful
        assertTrue(result);
        
        // Verify the remaining quantity in cart is correct
        SalableProduct item = cart.getItems().get(0);
        assertEquals(2, item.getQuantity());
        
        // Verify inventory quantity is properly adjusted
        // Original: 6, Added to cart: 4, Removed from cart: 2, Expected remaining: 4
        assertEquals(4, inventory.getProduct("Railgun Prototype").getQuantity());
    }
    
    /**
     * Tests the calculation of the total price of all items in the cart.
     * Verifies that the total price is correctly calculated based on
     * item prices and quantities.
     */
    @Test
    public void testGetTotalPrice() {
        // Add multiple items with different prices
        cart.addItem("Exotic Pulse Rifle", 2); // $299.99 × 2
        cart.addItem("Stimpak", 3);            // $45.50 × 3
        
        // Calculate expected total
        double total = cart.getTotalPrice();
        
        // Verify total with a small delta to account for floating point precision
        assertEquals((299.99 * 2) + (45.50 * 3), total, 0.001);
    }
    
    /**
     * Tests clearing all items from the shopping cart.
     * Verifies that the cart is empty after clearing.
     */
    @Test
    public void testClearCart() {
        // Add items to the cart
        cart.addItem("Adrenaline Shot", 2);
        
        // Clear the cart
        cart.clearCart();
        
        // Verify the cart is empty
        assertEquals(0, cart.getItemCount());
    }
}
