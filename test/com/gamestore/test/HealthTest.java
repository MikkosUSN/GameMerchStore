/**
 * Mikkos Thomas
 * CST-239 Milestone 7
 * 5/20/2025
 * I used my own work with reference from website:
 * https://mygcuedu6961.sharepoint.com/:w:/r/sites/CSETGuides/_layouts/15/Doc.aspx?sourcedoc=%7B3C90DE60-F1E6-4324-AE2D-AC0DCB1DBE9C%7D&file=CST-239-RS-Activity7Guide.docx&action=default&mobileredirect=true
 */
package com.gamestore.test;

import com.gamestore.model.SalableProduct;
import com.gamestore.model.Health;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.Assert.*;

/**
 * Unit tests for the Health class using real data from inventory.json.
 * This test class validates the proper deserialization of Health objects
 * from JSON and verifies their properties and behaviors.
 */
public class HealthTest {
    
    /**
     * Path to the JSON file containing inventory data for testing
     */
    private static final String JSON_PATH = "inventory.json";
    
    /**
     * Tests the properties of the first Health item loaded from the JSON file.
     * Verifies that all properties are correctly deserialized and match expected values.
     * @throws Exception If there's an error reading or parsing the JSON file
     */
    @Test
    public void testFirstHealthItemFromJson() throws Exception {
        // Create Jackson ObjectMapper to deserialize JSON
        ObjectMapper mapper = new ObjectMapper();
        
        // Read all products from JSON file into a list
        List<SalableProduct> all = mapper.readValue(new File(JSON_PATH),
                new TypeReference<List<SalableProduct>>() {});
        
        // Filter the list to get only Health objects
        List<Health> healthItems = all.stream()
                .filter(p -> p instanceof Health)
                .map(p -> (Health) p)
                .collect(Collectors.toList());
        
        // Get the first health item from the filtered list (Stimpak)
        Health h = healthItems.get(0);
        
        // Verify the basic SalableProduct properties
        assertEquals("Name should match expected value", "Stimpak", h.getName());
        assertEquals("Description should match expected value", 
                "Military-grade auto-injector that rapidly heals wounds", h.getDescription());
        assertEquals("Price should match expected value", 45.50, h.getPrice(), 0.001); // Using delta for floating point comparison
        assertEquals("Quantity should match expected value", 26, h.getQuantity());
        
        // Verify Health-specific properties
        assertEquals("Heal amount should match expected value", 65, h.getHealAmount());
        assertEquals("Duration should match expected value", 5, h.getDuration());
        assertEquals("Side effects should match expected value", "None", h.getSideEffects());
    }
    
    /**
     * Tests the toString() method of a Health object loaded from JSON.
     * Verifies that the string representation contains expected information
     * about the health item, including its name and health-specific attributes.
     * @throws Exception If there's an error reading or parsing the JSON file
     */
    @Test
    public void testToStringOutputFromJson() throws Exception {
        // Create Jackson ObjectMapper to deserialize JSON
        ObjectMapper mapper = new ObjectMapper();
        
        // Read all products from JSON file into a list
        List<SalableProduct> all = mapper.readValue(new File(JSON_PATH),
                new TypeReference<List<SalableProduct>>() {});
        
        // Filter the list to get only Health objects
        List<Health> healthItems = all.stream()
                .filter(p -> p instanceof Health)
                .map(p -> (Health) p)
                .collect(Collectors.toList());
        
        // Get the third health item from the filtered list (Nano-Med Kit)
        Health h = healthItems.get(2);
        
        // Get the string representation of the health item
        String output = h.toString();
        
        // Verify the string is not null
        assertNotNull("toString() should not return null", output);
        
        // Verify the string contains expected information
        assertTrue("toString() should contain the health item name", output.contains("Nano-Med"));
        assertTrue("toString() should contain heal amount attribute", output.contains("Heal Amount"));
        assertTrue("toString() should contain side effects attribute", output.contains("Side Effects"));
    }
}
