/**
 * Mikkos Thomas
 * CST-239 Milestone 7
 * 5/20/2025
 * I used my own work with reference from website:
 * https://mygcuedu6961.sharepoint.com/:w:/r/sites/CSETGuides/_layouts/15/Doc.aspx?sourcedoc=%7B3C90DE60-F1E6-4324-AE2D-AC0DCB1DBE9C%7D&file=CST-239-RS-Activity7Guide.docx&action=default&mobileredirect=true
 */
package com.gamestore.test;

import com.gamestore.model.SalableProduct;
import com.gamestore.model.Armor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.Assert.*;

/**
 * Unit tests for the Armor class using real data from inventory.json.
 * This test class validates the proper deserialization of Armor objects
 * from JSON and verifies their properties and behaviors.
 */
public class ArmorTest {
    
    /**
     * Path to the JSON file containing inventory data for testing
     */
    private static final String JSON_PATH = "inventory.json";
    
    /**
     * Tests the properties of the first Armor object loaded from the JSON file.
     * Verifies that all properties are correctly deserialized and match expected values.
     * @throws Exception If there's an error reading or parsing the JSON file
     */
    @Test
    public void testFirstArmorPropertiesFromJson() throws Exception {
        // Create Jackson ObjectMapper to deserialize JSON
        ObjectMapper mapper = new ObjectMapper();
        
        // Read all products from JSON file into a list
        List<SalableProduct> all = mapper.readValue(new File(JSON_PATH),
                new TypeReference<List<SalableProduct>>() {});
        
        // Filter the list to get only Armor objects
        List<Armor> armors = all.stream()
                .filter(p -> p instanceof Armor)
                .map(p -> (Armor) p)
                .collect(Collectors.toList());
        
        // Get the first armor item from the filtered list (Division Agent Vest)
        Armor a = armors.get(0);
        
        // Verify the basic SalableProduct properties
        assertEquals("Division Agent Vest", a.getName());
        assertEquals("Bulletproof tactical vest with multiple pouches", a.getDescription());
        assertEquals(175.75, a.getPrice(), 0.001); // Using delta for floating point comparison
        assertEquals(16, a.getQuantity());
        
        // Verify Armor-specific properties
        assertEquals(35, a.getDefense());
        assertEquals(85, a.getMobility());
        assertEquals(70, a.getDurability());
    }
    
    /**
     * Tests the toString() method of an Armor object loaded from JSON.
     * Verifies that the string representation contains expected information
     * about the armor, including its name and armor-specific attributes.
     * @throws Exception If there's an error reading or parsing the JSON file
     */
    @Test
    public void testToStringOutputFromJson() throws Exception {
        // Create Jackson ObjectMapper to deserialize JSON
        ObjectMapper mapper = new ObjectMapper();
        
        // Read all products from JSON file into a list
        List<SalableProduct> all = mapper.readValue(new File(JSON_PATH),
                new TypeReference<List<SalableProduct>>() {});
        
        // Filter the list to get only Armor objects
        List<Armor> armors = all.stream()
                .filter(p -> p instanceof Armor)
                .map(p -> (Armor) p)
                .collect(Collectors.toList());
        
        // Get the second armor item from the filtered list (Power Armor Frame)
        Armor a = armors.get(1);
        
        // Get the string representation of the armor
        String output = a.toString();
        
        // Verify the string is not null
        assertNotNull(output);
        
        // Verify the string contains expected information
        assertTrue("toString() should contain the armor name", output.contains("Power Armor"));
        assertTrue("toString() should contain defense attribute", output.contains("Defense"));
        assertTrue("toString() should contain durability attribute", output.contains("Durability"));
    }
}
