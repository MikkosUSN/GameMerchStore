// Mikkos Thomas
// CST-239 Milestone 4
// 04/30/2025
// I used my own work

package com.gamestore.model;

/**
 * Represents the main interface for the game merchandise store.
 * Manages store inventory, shopping cart, and user interaction logic.
 */
public class StoreFront
{
    /** Manages the store's inventory of products */
    private InventoryManager inventory;

    /** Manages the shopping cart for the customer */
    private ShoppingCart cart;

    /** Name of the store */
    private String storeName;

    /**
     * Default constructor initializes inventory, cart, and store name.
     */
    public StoreFront()
    {
        this.inventory = new InventoryManager();
        this.cart = new ShoppingCart(inventory);
        this.storeName = "Wasteland Arsenal";
    }

    /**
     * Returns the name of the store.
     * @return the store name
     */
    public String getStoreName()
    {
        return storeName;
    }

    /**
     * Initializes the store's inventory from file.
     * If no products are loaded, sample products are added and saved.
     */
    public void initialize()
    {
        String filePath = "inventory.json";
        inventory.loadFromFile(filePath);

        if (inventory.getAllProducts().isEmpty())
        {
            System.out.println("Loading default sample products...");

            inventory.addProduct(new Weapon("Exotic Pulse Rifle", "A high-tech rifle with energy ammunition", 299.99, 5, 45));
            inventory.addProduct(new Weapon("Tactical Vector SMG", "Compact submachine gun with high rate of fire", 199.50, 8, 30));
            inventory.addProduct(new Armor("Division Agent Vest", "Bulletproof tactical vest with multiple pouches", 175.75, 10, 35));
            inventory.addProduct(new Armor("Power Armor Frame", "Exoskeleton frame that enhances strength and protection", 450.00, 3, 75));
            inventory.addProduct(new Health("Stimpak", "Military-grade auto-injector that rapidly heals wounds", 45.50, 20, 65));

            inventory.saveToFile(filePath);
        }
    }

    /**
     * Displays all available products in the inventory.
     */
    public void displayProducts()
    {
        System.out.println("Available Products:");
        for (SalableProduct product : inventory.getAllProducts())
        {
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
    public String getProductSelectionFromUser(java.util.Scanner scanner)
    {
        java.util.List<SalableProduct> products = inventory.getAllProducts();

        // Show numbered product list
        System.out.println("\nAvailable Products:");
        for (int i = 0; i < products.size(); i++) 
        {
            System.out.println((i + 1) + ") " + products.get(i).getName());
        }

        System.out.print("Pick a product number (1 - " + products.size() + "): ");

        try 
        {
            int selection = Integer.parseInt(scanner.nextLine().trim());

            if (selection >= 1 && selection <= products.size()) 
            {
                return products.get(selection - 1).getName(); // Return the product name
            } 
            else 
            {
                System.out.println("Invalid selection. Please enter a number between 1 and " + products.size());
                return null;
            }
        } 
        catch (NumberFormatException e) 
        {
            System.out.println("Invalid input. Please enter a number.");
            return null;
        }
    }

    /**
     * Attempts to purchase a product by name and quantity.
     * Validates product availability and adds item to cart if valid.
     * @param productName the name of the product
     * @param quantity the quantity to purchase
     */
    public void purchaseProduct(String productName, int quantity)
    {
        SalableProduct product = inventory.getProduct(productName);

        // Check if the product exists in the inventory
        if (product == null)
        {
            System.out.println("Error: '" + productName + "' not found in inventory.");
            return;
        }

        // Check if the requested quantity is a positive number
        if (quantity <= 0)
        {
            System.out.println("Error: Quantity must be greater than zero.");
            return;
        }

        // Check if the inventory has enough quantity to fulfill the request
        if (product.getQuantity() < quantity)
        {
            System.out.println("Error: Only " + product.getQuantity() + " unit(s) of '" + productName + "' available.");
            return;
        }

        // All checks passed; add product to cart
        cart.addItem(productName, quantity);
        System.out.println(quantity + " x " + productName + " added to cart.");
    }

    /**
     * Removes a product from the shopping cart.
     * @param productName The name of the product to remove
     */
    public void cancelPurchase(String productName) 
    {
        cart.removeItem(productName);
        System.out.println(productName + " removed from cart.");
    }

    /**
     * Displays the contents of the shopping cart in numbered format.
     * Uses consistent user-friendly display style.
     */
    public void displayCart() 
    {
        if (cart.getItemCount() == 0) 
        {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("\nItems in cart:");
        java.util.List<SalableProduct> cartItems = cart.getItems();
        for (int i = 0; i < cartItems.size(); i++) 
        {
            SalableProduct product = cartItems.get(i);
            System.out.println((i + 1) + ") " + product.getName() + " x " + product.getQuantity() + 
                " - $" + String.format("%.2f", product.getPrice() * product.getQuantity()));
        }

        System.out.println("Total: $" + String.format("%.2f", cart.getTotalPrice()));
    }

    /**
     * Processes the checkout and clears the cart.
     * Displays summary and confirmation.
     */
    public void checkout() 
    {
        if (cart.getItemCount() == 0) 
        {
            System.out.println("Cannot checkout with empty cart.");
            return;
        }

        System.out.println("\n=== Checkout Summary ===");
        displayCart();

        System.out.println("\nProcessing payment of $" + String.format("%.2f", cart.getTotalPrice()));
        cart.clearCart();
        System.out.println("Checkout complete. Thank you for your purchase!");
    }

    /**
     * Gets the inventory manager.
     * @return The inventory manager
     */
    public InventoryManager getInventory() 
    {
        return inventory;
    }

    /**
     * Gets the shopping cart.
     * @return The shopping cart
     */
    public ShoppingCart getCart() 
    {
        return cart;
    }
}
