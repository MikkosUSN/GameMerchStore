/**
 * Mikkos Thomas
 * CST-239 Milestone 7
 * 5/20/2025
 * I used my own work with reference from website:
 * https://mygcuedu6961.sharepoint.com/:w:/r/sites/CSETGuides/_layouts/15/Doc.aspx?sourcedoc=%7B3C90DE60-F1E6-4324-AE2D-AC0DCB1DBE9C%7D&file=CST-239-RS-Activity7Guide.docx&action=default&mobileredirect=true
 */
package com.gamestore.test;

import com.gamestore.model.SalableProduct;
import com.gamestore.model.Weapon;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.Assert.*;

/**
 * Unit tests for the Weapon class using real data from inventory.json.
 * This test class validates the proper deserialization of Weapon objects
 * from JSON and verifies their properties and behaviors.
 */
public class WeaponTest {
    
    /**
     * Path to the JSON file containing inventory data for testing
     */
    private static final String JSON_PATH = "inventory.json";
    
    /**
     * Tests the properties of the first Weapon object loaded from the JSON file.
     * Verifies that all properties are correctly deserialized and match expected values.
     * @throws Exception If there's an error reading or parsing the JSON file
     */
    @Test
    public void testFirstWeaponPropertiesFromJson() throws Exception {
        // Create Jackson ObjectMapper to deserialize JSON
        ObjectMapper mapper = new ObjectMapper();
        
        // Read all products from JSON file into a list
        List<SalableProduct> all = mapper.readValue(new File(JSON_PATH),
                new TypeReference<List<SalableProduct>>() {});
        
        // Filter the list to get only Weapon objects
        List<Weapon> weapons = all.stream()
                .filter(p -> p instanceof Weapon)
                .map(p -> (Weapon) p)
                .collect(Collectors.toList());
        
        // Get the first weapon from the filtered list (Exotic Pulse Rifle)
        Weapon w = weapons.get(0);
        
        // Verify the basic SalableProduct properties
        assertEquals("Name should match expected value", "Exotic Pulse Rifle", w.getName());
        assertEquals("Description should match expected value", 
                "A high-tech rifle with energy ammunition", w.getDescription());
        assertEquals("Price should match expected value", 299.99, w.getPrice(), 0.001); // Using delta for floating point comparison
        assertEquals("Quantity should match expected value", 10, w.getQuantity());
        
        // Verify Weapon-specific properties
        assertEquals("Damage should match expected value", 45, w.getDamage());
        assertEquals("Rate of fire should match expected value", 650, w.getRateOfFire());
        assertEquals("Range should match expected value", 85, w.getRange());
    }
    
    /**
     * Tests the toString() method of a Weapon object loaded from JSON.
     * Verifies that the string representation contains expected information
     * about the weapon, including its name and weapon-specific attributes. 
     * @throws Exception If there's an error reading or parsing the JSON file
     */
    @Test
    public void testToStringOutputFromJson() throws Exception {
        // Create Jackson ObjectMapper to deserialize JSON
        ObjectMapper mapper = new ObjectMapper();
        
        // Read all products from JSON file into a list
        List<SalableProduct> all = mapper.readValue(new File(JSON_PATH),
                new TypeReference<List<SalableProduct>>() {});
        
        // Filter the list to get only Weapon objects
        List<Weapon> weapons = all.stream()
                .filter(p -> p instanceof Weapon)
                .map(p -> (Weapon) p)
                .collect(Collectors.toList());
        
        // Get the third weapon from the filtered list (Plasma Cannon)
        Weapon w = weapons.get(2);
        
        // Get the string representation of the weapon
        String output = w.toString();
        
        // Verify the string is not null
        assertNotNull("toString() should not return null", output);
        
        // Verify the string contains expected information
        assertTrue("toString() should contain the weapon name", output.contains("Plasma"));
        assertTrue("toString() should contain damage attribute", output.contains("Damage"));
        assertTrue("toString() should contain range attribute", output.contains("Range"));
    }
}
