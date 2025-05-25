/**
 * Mikkos Thomas
 * CST-239 Milestone 6
 * 5/15/2025
 * I used my own work
 * Admin client connects to AdminService on port 9006
 * and allows admin users to retrieve or update store inventory remotely.
 */

package com.gamestore.admin;

import java.io.*;           // Required for socket input/output
import java.net.Socket;     // Required to connect to AdminService
import java.util.Scanner;   // Used for admin input

public class AdminApp {

    // Define the port and host used to connect to the AdminService
    private static final int PORT = 9006;
    private static final String HOST = "localhost";

    /**
     * Main method to start the Admin application.
     * Allows admin to choose between retrieving or updating inventory.
     * @param args Not used
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Admin Console Connected to StoreFront");
            System.out.println("-------------------------------------");
            System.out.println("Choose a command:");
            System.out.println("R - Retrieve current inventory (JSON)");
            System.out.println("U - Upload new inventory (JSON)");
            System.out.print("Enter command (R/U): ");

            String command = scanner.nextLine().trim().toUpperCase();

         // Ensure valid input
            if (!command.equals("R") && !command.equals("U")) {
                System.out.println("Invalid command.");
                System.out.println("Click ENTER to continue...");
                scanner.nextLine(); // Wait for user to acknowledge the error
                return;
            }

            // Try connecting to the AdminService socket
            try (
                Socket socket = new Socket(HOST, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
            ) {
                // Send the command to the AdminService
                out.println(command);

                if (command.equals("R")) {
                    // Read inventory JSON returned from store and print
                    String response = in.readLine();
                    System.out.println("\n--- Current Store Inventory (JSON) ---");
                    System.out.println(response);
                } else if (command.equals("U")) {
                    // Prompt admin to paste JSON string
                    System.out.println("\nPaste new inventory JSON (single line):");
                    String jsonPayload = scanner.nextLine().trim();

                    // Send the JSON payload to the server
                    out.println(jsonPayload);

                    // Read confirmation from AdminService
                    String confirmation = in.readLine();
                    System.out.println("\nServer Response: " + confirmation);
                }

            } catch (IOException e) {
                System.out.println("Error connecting to AdminService: " + e.getMessage());
            }
        }
    }
}