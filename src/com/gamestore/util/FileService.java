/**
 * Mikkos Thomas
 * CST-239 Milestone 6
 * 5/15/2025
 * I used my own work with help from website: https://jenkov.com/tutorials/java-json/jackson-objectmapper.html
 */ 

package com.gamestore.util;

import com.gamestore.model.SalableProduct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * FileService is a utility class responsible for handling
 * file input/output operations involving the inventory data.
 * It provides methods to load and save a list of SalableProduct
 * objects to and from a JSON file using the Jackson library.
 */
public class FileService {
    /**
     * Jackson ObjectMapper used for JSON serialization and deserialization.
     */
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Loads a list of SalableProduct objects from the specified JSON file.
     * @param filePath The path to the JSON file to read from
     * @return A List of SalableProduct objects parsed from the file
     * @throws IOException If the file is not found or contains invalid JSON
     */
    public static List<SalableProduct> loadInventory(String filePath) throws IOException {
        return mapper.readValue(new File(filePath), new TypeReference<List<SalableProduct>>() {});
    }

    /**
     * Saves a list of SalableProduct objects to the specified JSON file.
     * @param products The list of SalableProduct objects to write to file
     * @param filePath The path to the JSON file to save to
     * @throws IOException If an error occurs during file writing
     */
    public static void saveInventory(List<SalableProduct> products, String filePath) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), products);
    }
}
