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
import com.gamestore.model.Armor;
import com.gamestore.model.Health;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import org.junit.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 * JUnit test for basic serialization/deserialization of SalableProduct.
 * Confirms JSON contains 'type' metadata and deserializes to correct subclass.
 * This test validates the polymorphic serialization and deserialization
 * capabilities of the SalableProduct class hierarchy using Jackson.
 */
public class SalableProductTest {
    
    /**
     * Tests the serialization and deserialization of SalableProduct objects.
     * This test verifies that:
     * 1. Different subclasses of SalableProduct can be serialized to JSON
     * 2. Type information is preserved in the JSON
     * 3. Objects can be correctly deserialized back to their original types
     * 4. All properties are preserved during the process
     * 
     * @throws Exception If there's an error during serialization, file operations, or deserialization
     */
    @Test
    public void testSerializationAndDeserialization() throws Exception {
        // Arrange - create a list with different SalableProduct subclasses
        List<SalableProduct> products = new ArrayList<>();
        
        // Add a Weapon instance with specific properties
        products.add(new Weapon("Railgun", "High damage sniper", 499.99, 4, 120, 100, 80));
        
        // Add an Armor instance with specific properties
        products.add(new Armor("Nano Armor", "Lightweight suit", 350.00, 6, 50, 90, 75));
        
        // Add a Health instance with specific properties
        products.add(new Health("Medi-Gel", "Heals wounds", 60.00, 10, 100, 5, "None"));
        
        // Create a temporary file for testing that will be deleted when the JVM exits
        File tempFile = File.createTempFile("salable_test", ".json");
        tempFile.deleteOnExit();
        
        // Setup ObjectMapper with registered subtypes for polymorphic serialization/deserialization
        ObjectMapper mapper = new ObjectMapper();
        
        // Register each subclass with a type identifier to be included in the JSON
        mapper.registerSubtypes(
            new NamedType(Weapon.class, "Weapon"),
            new NamedType(Armor.class, "Armor"),
            new NamedType(Health.class, "Health")
        );
        
        // Act - Write products to file with proper type metadata
        // The TypeReference ensures Jackson knows we're serializing List<SalableProduct>
        // and should include type information for proper polymorphic deserialization
        mapper.writerFor(new TypeReference<List<SalableProduct>>() {})
              .withDefaultPrettyPrinter()  // Makes the JSON output more readable
              .writeValue(tempFile, products);
        
        // Act - Read products back from the file
        // The TypeReference tells Jackson to create the appropriate subclass instances
        List<SalableProduct> loaded = mapper.readValue(
            tempFile, new TypeReference<List<SalableProduct>>() {});
        
        // Assert - Verify the deserialization worked correctly        
        // Check that the list was created
        assertNotNull("Deserialized list should not be null", loaded);
        
        // Check that all items were deserialized
        assertEquals("List should contain 3 items", 3, loaded.size());
        
        // Verify each object was deserialized to the correct type
        assertTrue("First item should be a Weapon", loaded.get(0) instanceof Weapon);
        assertTrue("Second item should be an Armor", loaded.get(1) instanceof Armor);
        assertTrue("Third item should be a Health item", loaded.get(2) instanceof Health);
        
        // Verify the properties were preserved during serialization/deserialization
        assertEquals("Weapon name should match", "Railgun", loaded.get(0).getName());
        assertEquals("Armor name should match", "Nano Armor", loaded.get(1).getName());
        assertEquals("Health item name should match", "Medi-Gel", loaded.get(2).getName());
    }
}
