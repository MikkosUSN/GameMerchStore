// Mikkos Thomas
// CST-239
// 04/23/2025
// I used my own work

package com.gamestore.main;

import com.gamestore.model.StoreFront;
import java.util.Scanner;

/**
 * Game Store application to demonstrate the store functionality.
 * This class serves as the main entry point for the application and handles
 * user interaction through a console-based menu system.
 */
public class GameStoreApp 
{
    /**
     * The main method that starts the application.
     * It initializes the store, displays a welcome message, and
     * presents a menu for user interaction.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) 
    {
        // Initialize the store and its inventory
        StoreFront store = new StoreFront();
        store.initialize();
        
        // Create scanner for user input
        Scanner scanner = new Scanner(System.in);
        
        // Display welcome banner
        System.out.println("");
        System.out.println(" Welcome to " + store.getStoreName() + "! ");
        System.out.println(" Gear up for your next mission, Agent! ");
        System.out.println("");
        
        // Control variable for the main application loop
        boolean running = true;
        
        // Main application loop
        while (running) 
        {
            // Display the main menu options
            displayMainMenu(store.getStoreName());
            
            // Get and validate user choice
            int choice = getUserChoice(scanner);
            
            // Process the user's choice
            running = processUserChoice(choice, store, scanner);
        }
        
        // Close the scanner to prevent resource leaks
        scanner.close();
    }
    
    /**
     * Displays the main menu options to the user.
     * @param storeName The name of the store to display in the menu header
     */
    private static void displayMainMenu(String storeName) 
    {
        System.out.println("\n=== " + storeName + " Menu ===");
        System.out.println("1. Browse Products");
        System.out.println("2. Purchase Product");
        System.out.println("3. Cancel Purchase");
        System.out.println("4. View Cart");
        System.out.println("5. Checkout");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }
    
    /**
     * Gets and validates the user's menu choice.
     * @param scanner The Scanner object to read user input
     * @return The validated user choice as an integer, or 0 if input was invalid
     */
    private static int getUserChoice(Scanner scanner) 
    {
        try 
        {
            return Integer.parseInt(scanner.nextLine());
        } 
        catch (NumberFormatException e) 
        {
            System.out.println("Error: Please enter a number.");
            return 0; // Return 0 to indicate invalid input
        }
    }
    
    /**
     * Processes the user's menu choice and performs the corresponding action.
     * @param choice The user's menu choice
     * @param store The StoreFront object to interact with
     * @param scanner The Scanner object to read additional user input
     * @return true if the application should continue running, false if it should exit
     */
    private static boolean processUserChoice(int choice, StoreFront store, Scanner scanner) 
    {
        switch (choice) 
        {
            case 1:
                // Browse products - display all available products
                store.displayProducts();
                return true;
                
            case 2:
                // Purchase product - get product name and quantity from user
                handleProductPurchase(store, scanner);
                return true;
                
            case 3:
                // Cancel purchase - remove an item from the cart
                handlePurchaseCancellation(store, scanner);
                return true;
                
            case 4:
                // View cart - display current cart contents
                store.displayCart();
                return true;
                
            case 5:
                // Checkout - process the purchase
                store.checkout();
                return true;
                
            case 6:
                // Exit the application
                System.out.println("Thank you for visiting the " + store.getStoreName() + "!");
                System.out.println("Stay frosty out there, Guardian.");
                return false;
                
            default:
                // Handle invalid menu choices
                System.out.println("Error: Invalid option. Please try again.");
                return true;
        }
    }
    
    /**
     * Handles the process of purchasing a product.
     * Gets the product name and quantity from the user and attempts to add it to the cart.
     * @param store The StoreFront object to interact with
     * @param scanner The Scanner object to read user input
     */
    private static void handleProductPurchase(StoreFront store, Scanner scanner) 
    {
        // Get product name from user
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        
        // Get and validate quantity
        System.out.print("Enter quantity: ");
        try 
        {
            int qty = Integer.parseInt(scanner.nextLine());
            
            // Ensure quantity is positive
            if (qty <= 0) 
            {
                System.out.println("Error: Quantity must be greater than zero.");
            } 
            else 
            {
                // Attempt to add the product to the cart
                store.purchaseProduct(name, qty);
            }
        } 
        catch (NumberFormatException e) 
        {
            System.out.println("Error: Please enter a valid number for quantity.");
        }
    }
    
    /**
     * Handles the process of canceling a purchase.
     * Gets the product name from the user and attempts to remove it from the cart.
     * @param store The StoreFront object to interact with
     * @param scanner The Scanner object to read user input
     */
    private static void handlePurchaseCancellation(StoreFront store, Scanner scanner) 
    {
        // Get product name to remove
        System.out.print("Enter product name to remove: ");
        String removeName = scanner.nextLine();
        
        // Attempt to remove the product from the cart
        store.cancelPurchase(removeName);
    }
}
