// Mikkos Thomas
// CST-239 Milestone 5
// 5/5/2025
// I used my own work, Sorting functionality feature added
package com.gamestore.main;

import com.gamestore.model.SalableProduct;
import com.gamestore.model.StoreFront;
import java.util.List;
import java.util.Scanner;

/**
 * Game Store application to demonstrate the store functionality.
 * This class serves as the main entry point for the application and handles
 * user interaction through a console-based menu system. 
 * The application provides a text-based interface for browsing products,
 * making purchases, managing a shopping cart, and checking out.
 * Adds product sorting capabilities.
 */
public class GameStoreApp {
    /**
     * The main method that starts the application.
     * It initializes the store, displays a welcome message, and
     * presents a menu for user interaction.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Initialize the store and load inventory
        StoreFront store = new StoreFront();
        store.initialize(); // Load or create inventory
        
        // Create scanner for user input
        Scanner scanner = new Scanner(System.in);
        
        // Display welcome banner
        System.out.println("\n Welcome to " + store.getStoreName() + "!");
        System.out.println(" Gear up for your next mission, Slayer! \n");
        
        // Control variable for the main application loop
        boolean running = true;
        
        // Main application loop
        while (running) {
            displayMainMenu(store.getStoreName());
            int choice = getUserChoice(scanner);
            running = processUserChoice(choice, store, scanner);
        }
        
        // Close the scanner to prevent resource leaks
        scanner.close();
    }

    /**
     * Displays the main menu options to the user.
     * @param storeName The name of the store to display in the menu header
     */
    private static void displayMainMenu(String storeName) {
        System.out.println("\n=== " + storeName + " Menu ===");
        System.out.println("1. Browse Products");
        System.out.println("2. Purchase Product");
        System.out.println("3. Cancel Purchase");
        System.out.println("4. View Cart");
        System.out.println("5. Sort Products"); // New sorting option
        System.out.println("6. Checkout");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Gets and validates the user's menu choice.
     * @param scanner The Scanner object to read user input
     * @return The validated user choice as an integer, or 0 if input was invalid
     */
    private static int getUserChoice(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
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
    private static boolean processUserChoice(int choice, StoreFront store, Scanner scanner) {
        switch (choice) {
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
                // Sort products - new feature 
                handleSortMenu(store, scanner); 
                return true;
            case 6:
                // Checkout - process the purchase
            	store.checkout();
                return true;
            case 7:
                // Exit the application
                System.out.println("Thank you for visiting " + store.getStoreName() + "!");
                System.out.println("Stay Vigilant, Slayer and remember to WATCH YOUR 6!");
                return false;
            default:
                // Handle invalid menu choices
                System.out.println("Error: Invalid option. Please try again.");
                return true;
        }
    }

    /**
     * Handles user interaction for sorting the product inventory.
     * Prompts the user for sorting criteria and displays sorted results.
     * This is a new feature added in Milestone 5 to demonstrate sorting capabilities.
     * @param store The StoreFront to get inventory from
     * @param scanner The Scanner for user input
     */
    private static void handleSortMenu(StoreFront store, Scanner scanner) {
        // Display sorting options
        System.out.println("\nSort by:");
        System.out.println("1. Name Ascending");
        System.out.println("2. Name Descending");
        System.out.println("3. Price Ascending");
        System.out.println("4. Price Descending");
        System.out.print("Enter your choice: ");
        
        // Get user's sorting preference
        String input = scanner.nextLine().trim();
        List<SalableProduct> sorted = null;
        
        // Process the sorting choice
        switch (input) {
            case "1":
                sorted = store.getInventory().getProductsSortedByName(true); 
                break;
            case "2":
                sorted = store.getInventory().getProductsSortedByName(false); 
                break;
            case "3":
                sorted = store.getInventory().getProductsSortedByPrice(true); 
                break;
            case "4":
                sorted = store.getInventory().getProductsSortedByPrice(false); 
                break;
            default:
                System.out.println("Invalid choice."); 
                return;
        }
        
        // Display the sorted products
        System.out.println("\n--- Sorted Products ---");
        for (SalableProduct product : sorted) {
            System.out.println("----------------------");
            System.out.println(product);
        }
    }

    /**
     * Handles the process of purchasing a product.
     * Gets product and quantity input from user and attempts to add to cart.
     * Uses a numbered product list to reduce typing errors. 
     * @param store The StoreFront object to interact with
     * @param scanner The Scanner object to read user input
     */
    private static void handleProductPurchase(StoreFront store, Scanner scanner) {
        // Get product selection from user using the helper method
        String selectedProduct = store.getProductSelectionFromUser(scanner);
        
        // If selection is invalid, exit early
        if (selectedProduct == null) {
            System.out.println("Product name does not exist. Please check selection.");
            return;
        }
        
        // Ask for quantity only after valid product is selected
        System.out.print("Enter quantity: ");
        try {
            int qty = Integer.parseInt(scanner.nextLine().trim());
            if (qty <= 0) {
                System.out.println("Error: Quantity must be greater than zero.");
            } else {
                // Attempt to add the product to the cart
                store.purchaseProduct(selectedProduct, qty);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid whole number for quantity.");
        }
    }

    /**
     * Handles the process of canceling items from the shopping cart.
     * Allows complete or partial removal of items by prompting user input.
     * This enhanced implementation allows users to remove multiple different items
     * from their cart in a single operation.
     * @param store The StoreFront object to interact with
     * @param scanner The Scanner object to read user input
     */
    private static void handlePurchaseCancellation(StoreFront store, Scanner scanner) {
        // Control flag to determine whether to continue the removal process
        boolean continueRemoving = true;
        
        // Main removal loop - continues until user is done or cart is empty
        while (continueRemoving) {
            // Retrieve the current items in the shopping cart
            List<SalableProduct> cartItems = store.getCart().getItems();
            
            // Check if cart is empty - if so, inform the user and exit the method
            if (cartItems.isEmpty()) {
                System.out.println("Cart is empty. Nothing to cancel.");
                return;
            }
            
            // Display all items currently in the cart with numbered indices for selection
            System.out.println("\nItems in cart:");
            for (int i = 0; i < cartItems.size(); i++) {
                SalableProduct item = cartItems.get(i);
                System.out.println((i + 1) + ") " + item.getName() + " x " + item.getQuantity());
            }
            
            // Add an option to finish the removal process
            System.out.println((cartItems.size() + 1) + ") Done removing items");
            
            // Prompt the user to select an item to remove or to finish
            System.out.print("Pick a number to remove (1 - " + cartItems.size() + ") or " + 
                             (cartItems.size() + 1) + " when finished: ");
            try {
                // Parse the user's selection, ensuring it's a valid integer
                int choice = Integer.parseInt(scanner.nextLine().trim());
                
                // Check if user selected the "Done" option
                if (choice == cartItems.size() + 1) {
                    continueRemoving = false;
                    System.out.println("Finished removing items.");
                    continue; // Skip the rest of the loop and exit
                }
                
                // Validate that the selection is within the valid range of items
                if (choice >= 1 && choice <= cartItems.size()) {
                    // Get the selected product details from the cart
                    SalableProduct selectedItem = cartItems.get(choice - 1);
                    String productName = selectedItem.getName();
                    int currentQuantity = selectedItem.getQuantity();
                    
                    // Display the current quantity and prompt for how many to remove
                    System.out.println("Current quantity in cart: " + currentQuantity);
                    System.out.print("How many would you like to remove? (1-" + currentQuantity + "): ");
                    
                    try {
                        // Parse the quantity to remove, ensuring it's a valid integer
                        int quantityToRemove = Integer.parseInt(scanner.nextLine().trim());
                        
                        // Validate the quantity is within acceptable range
                        if (quantityToRemove <= 0 || quantityToRemove > currentQuantity) {
                            System.out.println("Invalid quantity. Please enter a number between 1 and " + currentQuantity);
                        } else if (quantityToRemove == currentQuantity) {
                            // If removing all units, use the existing cancelPurchase method
                            store.cancelPurchase(productName);
                        } else {
                            // If removing only some units, use the cancelPartialPurchase method
                            store.cancelPartialPurchase(productName, quantityToRemove);
                        }
                        
                        // After successful removal, check if there are more items in the cart
                        if (!store.getCart().getItems().isEmpty()) {
                            // Ask if the user wants to continue removing items
                            System.out.print("Would you like to remove more items? (y/n): ");
                            String response = scanner.nextLine().trim().toLowerCase();
                            // If response doesn't start with 'y', exit the removal process
                            if (!response.startsWith("y")) {
                                continueRemoving = false;
                                System.out.println("Finished removing items.");
                            }
                        } else {
                            // If cart is now empty, exit the loop automatically
                            continueRemoving = false;
                        }
                        
                    } catch (NumberFormatException e) {
                        // Handle the case where the user enters a non-numeric value for quantity
                        System.out.println("Invalid input. Please enter a number.");
                    }
                } else {
                    // Handle the case where the user's selection is out of range
                    System.out.println("Invalid selection. Please enter a number between 1 and " + 
                                      (cartItems.size() + 1));
                }
            } catch (NumberFormatException e) {
                // Handle the case where the user enters a non-numeric value for item selection
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
