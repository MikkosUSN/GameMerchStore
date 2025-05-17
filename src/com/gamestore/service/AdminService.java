/**
 * Mikkos Thomas
 * CST-239 Milestone 6
 * 05/15/2025
 * I used my own work with reference from website: https://www.baeldung.com/a-guide-to-java-sockets
 * This background service receives admin commands to read or update inventory over a network socket.
 */

package com.gamestore.service;

// Import classes for managing networking and JSON
import com.gamestore.model.SalableProduct;
import com.gamestore.model.InventoryManager;
import com.gamestore.util.FileService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class AdminService implements Runnable // Allows this class to be executed as a thread
{
    // Port that the AdminService will listen on for Admin client connections
    private static final int PORT = 9006;

    private InventoryManager inventoryManager; // Reference to the StoreFront’s inventory
    private ObjectMapper objectMapper;         // Used to convert objects to/from JSON

    /**
     * Constructor accepts an InventoryManager used to access and update inventory data.
     * @param inventoryManager Inventory management instance from the StoreFront
     */
    public AdminService(InventoryManager inventoryManager)
    {
        this.inventoryManager = inventoryManager;
        this.objectMapper = new ObjectMapper(); // Initialize Jackson ObjectMapper
    }

    /**
     * Main method that listens for Admin commands and processes them.
     * Supports two commands:
     * "R" - Return current inventory as JSON.
     * "U" - Update inventory with provided JSON payload.
     */
    @Override
    public void run()
    {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) // Start server on specified port
        {
            System.out.println("AdminService is running on port " + PORT + "...");

            // Continuously listen for incoming Admin connections
            while (true)
            {
                try (
                    Socket clientSocket = serverSocket.accept(); // Accept a client connection
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Input from Admin
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true) // Output to Admin
                )
                {
                    // Read the first command sent by Admin
                    String command = in.readLine();

                    // Handle command: "R" returns inventory JSON to admin
                    if ("R".equalsIgnoreCase(command))
                    {
                        List<SalableProduct> products = inventoryManager.getAllProducts(); // Get all products
                        String json = objectMapper.writeValueAsString(products);           // Convert list to JSON
                        out.println(json);                                                  // Send JSON to Admin
                        System.out.println("Sent current inventory to Admin.");
                    }
                    // Handle command: "U" expects JSON payload to update inventory
                    else if ("U".equalsIgnoreCase(command))
                    {
                        String jsonPayload = in.readLine(); // Read JSON string from Admin

                        // Convert JSON string into List<SalableProduct>
                        List<SalableProduct> updatedList = objectMapper.readValue(
                            jsonPayload,
                            new TypeReference<List<SalableProduct>>() {}
                        );

                        // Update inventory and save changes to file
                        inventoryManager.setInventory(updatedList);
                        FileService.saveInventory(updatedList, "inventory.json");

                        out.println("Inventory successfully updated."); // Notify Admin
                        System.out.println("Inventory updated via Admin command.");
                    }
                    else
                    {
                        // Inform Admin if command is unrecognized
                        out.println("Invalid command received.");
                    }
                }
                catch (Exception e)
                {
                    // Display error if something goes wrong with a single request
                    System.out.println("Error handling Admin request: " + e.getMessage());
                }
            }
        }
        catch (IOException e)
        {
            // Display error if server fails to start
            System.out.println("AdminService failed to start: " + e.getMessage());
        }
    }
}
