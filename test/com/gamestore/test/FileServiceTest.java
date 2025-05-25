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
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Final mock test for SalableProduct serialization using type info.
 * This test class validates the JSON serialization and deserialization
 * of different SalableProduct subclasses with proper type information
 * to ensure polymorphic behavior is preserved.
 */
public class FileServiceTest {
    
    /**
     * Tests the serialization and deserialization of a list of different
     * SalableProduct subclasses (Weapon, Armor, Health) to and from JSON.
     * This test verifies that:
     * 1. Objects can be properly serialized to JSON with type information
     * 2. JSON can be deserialized back into the correct object types
     * 3. All properties are preserved during the process
     * @throws Exception If there's an error during serialization or deserialization
     */
    @Test
    public void testSerializeDeserializeWithMockJson() throws Exception {
        // Arrange - Create a list of different SalableProduct subclasses
        List<SalableProduct> products = new ArrayList<>();
        
        // Add a Weapon instance with specific properties
        products.add(new Weapon("Plasma SMG", "High ROF plasma gun", 275.50, 10, 55, 1000, 40));
        
        // Add an Armor instance with specific properties
        products.add(new Armor("Shadow Cloak", "Stealth suit", 450.00, 5, 40, 95, 60));
        
        // Add a Health instance with specific properties
        products.add(new Health("Adrenaline Shot", "Instant energy", 60.00, 12, 75, 3, "Jitters"));
        
        // Configure ObjectMapper with type registration for polymorphic deserialization
        ObjectMapper mapper = new ObjectMapper();
        
        // Register subtypes with explicit type names to be included in JSON
        mapper.registerSubtypes(
            new NamedType(Weapon.class, "Weapon"),
            new NamedType(Armor.class, "Armor"),
            new NamedType(Health.class, "Health")
        );
        
        // Create a writer that preserves type information for SalableProduct list
        // The TypeReference ensures Jackson knows we're serializing List<SalableProduct>
        // and should include type information for proper polymorphic deserialization
        ObjectWriter writer = mapper
            .writerFor(new TypeReference<List<SalableProduct>>() {})
            .withDefaultPrettyPrinter();  // Makes the JSON output more readable
        
        // Act - Serialize the list of products to a JSON string
        String json = writer.writeValueAsString(products);
        
        // Debug output to console (helpful during development)
        System.out.println("--- Mock JSON ---");
        System.out.println(json);
        
        // Act - Deserialize the JSON string back into a list of SalableProduct objects
        // The TypeReference tells Jackson to create the appropriate subclass instances
        List<SalableProduct> loaded = mapper.readValue(
            json, new TypeReference<List<SalableProduct>>() {});
        
        // Assert - Verify the deserialization worked correctly
        
        // Check that the list was created and has the expected number of items
        assertNotNull("Deserialized list should not be null", loaded);
        assertEquals("List should contain 3 items", 3, loaded.size());
        
        // Verify each object was deserialized to the correct type
        assertTrue("First item should be a Weapon", loaded.get(0) instanceof Weapon);
        assertTrue("Second item should be an Armor", loaded.get(1) instanceof Armor);
        assertTrue("Third item should be a Health item", loaded.get(2) instanceof Health);
        
        // Verify the properties were preserved during serialization/deserialization
        assertEquals("Weapon name should match", "Plasma SMG", loaded.get(0).getName());
        assertEquals("Armor name should match", "Shadow Cloak", loaded.get(1).getName());
        assertEquals("Health item name should match", "Adrenaline Shot", loaded.get(2).getName());
    }
}
