// Mikkos Thomas
// CST-239 Milestone 5
// 5/5/2025
// I used my own work

package com.gamestore.model;

/**
 * Represents the main interface for the game merchandise store.
 * Manages store inventory, shopping cart, and user interaction logic.
 * Provides methods for browsing products, making purchases, and checkout.
 */
public class StoreFront {
    /** Manages the store's inventory of products */
    private InventoryManager inventory;
    
    /** Manages the shopping cart for the customer */
    private ShoppingCart cart;
    
    /** Name of the store */
    private String storeName;

    /**
     * Default constructor initializes inventory, cart, and store name.
     * Creates a new InventoryManager and ShoppingCart instance.
     */
    public StoreFront() {
        this.inventory = new InventoryManager();
        this.cart = new ShoppingCart(inventory);
        this.storeName = "Wasteland Arsenal";
    }

    /**
     * Returns the name of the store.
     * @return the store name as a String
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * Initializes the store's inventory from file.
     * If no products are loaded, sample products are added and saved.
     * This method is called when the store is first started.
     */
    public void initialize() {
        // Define the path to the inventory JSON file
        String filePath = "inventory.json";
        
        // Attempt to load inventory from the file
        inventory.loadFromFile(filePath);
        
        // If no products were loaded (file doesn't exist or is empty), create sample products
        if (inventory.getAllProducts().isEmpty()) {
            System.out.println("Loading default sample products...");
            
            // Add sample weapons to inventory with all new attributes
            inventory.addProduct(new Weapon("Exotic Pulse Rifle", "A high-tech rifle with energy ammunition", 
                299.99, 5, 45, 650, 85));
            inventory.addProduct(new Weapon("Tactical Vector SMG", "Compact submachine gun with high rate of fire", 
                199.50, 8, 30, 1200, 40));
            
            // Add sample armor to inventory with all new attributes
            inventory.addProduct(new Armor("Division Agent Vest", "Bulletproof tactical vest with multiple pouches", 
                175.75, 10, 35, 85, 70));
            inventory.addProduct(new Armor("Power Armor Frame", "Exoskeleton frame that enhances strength and protection", 
                450.00, 3, 75, 50, 90));
            
            // Add sample health items to inventory with all new attributes
            inventory.addProduct(new Health("Stimpak", "Military-grade auto-injector that rapidly heals wounds", 
                45.50, 20, 65, 5, "None"));
            inventory.addProduct(new Health("Regeneration Serum", "Advanced formula that accelerates natural healing processes", 
                85.75, 12, 120, 15, "Mild nausea"));
            
            // Save the newly created inventory to the file
            inventory.saveToFile(filePath);
        }
    }

    /**
     * Displays all available products in the inventory.
     * Prints each product's details to the console.
     */
    public void displayProducts() {
        System.out.println("Available Products:");
        for (SalableProduct product : inventory.getAllProducts()) {
            System.out.println("------------------------");
            System.out.println(product);
        }
    }

    /**
     * Displays numbered product list and prompts user to select by number.
     * This prevents name-typing errors and improves user experience.
     * @param scanner The Scanner object to read user input
     * @return The selected product name or null if the selection is invalid
     */
    public String getProductSelectionFromUser(java.util.Scanner scanner) {
        // Get the list of all products from inventory
        java.util.List<SalableProduct> products = inventory.getAllProducts();
        
        // Show numbered product list for easier selection
        System.out.println("\nAvailable Products:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ") " + products.get(i).getName());
        }
        
        // Prompt user to select a product by number
        System.out.print("Pick a product number (1 - " + products.size() + "): ");
        try {
            // Parse and validate the user's selection
            int selection = Integer.parseInt(scanner.nextLine().trim());
            if (selection >= 1 && selection <= products.size()) {
                return products.get(selection - 1).getName(); // Return the product name
            } else {
                // Inform user if selection is out of range
                System.out.println("Invalid selection. Please enter a number between 1 and " + products.size());
                return null;
            }
        } catch (NumberFormatException e) {
            // Handle non-numeric input
            System.out.println("Invalid input. Please enter a number.");
            return null;
        }
    }

    /**
     * Attempts to purchase a product by name and quantity.
     * Validates product availability and adds item to cart if valid.
     * @param productName the name of the product to purchase
     * @param quantity the quantity to purchase
     */
    public void purchaseProduct(String productName, int quantity) {
        // Look up the product in inventory
        SalableProduct product = inventory.getProduct(productName);
        
        // Check if the product exists in the inventory
        if (product == null) {
            System.out.println("Error: '" + productName + "' not found in inventory.");
            return;
        }
        
        // Check if the requested quantity is a positive number
        if (quantity <= 0) {
            System.out.println("Error: Quantity must be greater than zero.");
            return;
        }
        
        // Check if the inventory has enough quantity to fulfill the request
        if (product.getQuantity() < quantity) {
            System.out.println("Error: Only " + product.getQuantity() + " unit(s) of '" + productName + "' available.");
            return;
        }
        
        // All checks passed; add product to cart
        cart.addItem(productName, quantity);
        System.out.println(quantity + " x " + productName + " added to cart.");
    }

    /**
     * Removes a product from the shopping cart completely.
     * Returns all units of the product to inventory.
     * @param productName The name of the product to remove
     */
    public void cancelPurchase(String productName) {
        cart.removeItem(productName);
        System.out.println(productName + " removed from cart.");
    }

    /**
     * Removes a specific quantity of a product from the shopping cart.
     * Allows partial cancellation of a purchase, rather than removing all units.
     * Returns the specified quantity back to inventory.
     * @param productName The name of the product to remove
     * @param quantity The quantity to remove
     * @return true if successful, false if the product wasn't found or quantity was invalid
     */
    public boolean cancelPartialPurchase(String productName, int quantity) {
        // Attempt to remove the specified quantity from cart
        boolean success = cart.removeItemQuantity(productName, quantity);
        
        // Provide appropriate feedback based on the result
        if (success) {
            System.out.println(quantity + " x " + productName + " removed from cart.");
        } else {
            System.out.println("Failed to remove items. Please check product name and quantity.");
        }
        
        return success;
    }

    /**
     * Displays the contents of the shopping cart in numbered format.
     * Uses consistent user-friendly display style.
     */
    public void displayCart() {
        // Check if cart is empty
        if (cart.getItemCount() == 0) {
            System.out.println("Cart is empty.");
            return;
        }
        
        // Display cart items with numbers and prices
        System.out.println("\nItems in cart:");
        java.util.List<SalableProduct> cartItems = cart.getItems();
        for (int i = 0; i < cartItems.size(); i++) {
            SalableProduct product = cartItems.get(i);
            System.out.println((i + 1) + ") " + product.getName() + " x " + product.getQuantity() +
                " - $" + String.format("%.2f", product.getPrice() * product.getQuantity()));
        }
        
        // Display the total price of all items in cart
        System.out.println("Total: $" + String.format("%.2f", cart.getTotalPrice()));
    }

    /**
     * Processes the checkout and clears the cart.
     * Displays summary and confirmation.
     * In a real system, this would handle payment processing.
     */
    public void checkout() {
        // Check if cart is empty
        if (cart.getItemCount() == 0) {
            System.out.println("Cannot checkout with empty cart.");
            return;
        }
        
        // Display checkout summary
        System.out.println("\n=== Checkout Summary ===");
        displayCart();
        
        // Process payment (in a real system, this would connect to payment gateway)
        System.out.println("\nProcessing payment of $" + String.format("%.2f", cart.getTotalPrice()));
        
        // Clear the cart after successful checkout
        cart.clearCart();
        System.out.println("Checkout complete. Thank you for your purchase!");
    }

    /**
     * Gets the inventory manager.
     * @return The inventory manager instance
     */
    public InventoryManager getInventory() {
        return inventory;
    }

    /**
     * Gets the shopping cart.
     * @return The shopping cart instance
     */
    public ShoppingCart getCart() {
        return cart;
    }
}
