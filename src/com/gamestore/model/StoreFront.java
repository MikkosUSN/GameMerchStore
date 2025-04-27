// Mikkos Thomas
// CST-239
// 04/23/2025
// I used my own work

package com.gamestore.model;

/**
 * Represents the main interface for the game merchandise store.
 */
public class StoreFront 
{
    private InventoryManager inventory;
    private ShoppingCart cart;
    private String storeName;
    
    /**
     * Constructs a new StoreFront
     */
    public StoreFront() 
    {
        this.inventory = new InventoryManager();
        this.cart = new ShoppingCart(inventory);
        this.storeName = "Wasteland Arsenal";
    }
    
    /**
     * Gets the name of the store
     * @return The store name
     */
    public String getStoreName() 
    {
        return storeName;
    }
    
    /**
     * Initializes the store with sample products
     */
    public void initialize() 
    {
        // Add 2 different kinds of Weapons
        inventory.addProduct(new Weapon("Exotic Pulse Rifle", "A high-tech rifle with energy ammunition", 299.99, 5, 45));
        inventory.addProduct(new Weapon("Tactical Vector SMG", "Compact submachine gun with high rate of fire", 199.50, 8, 30));
        
        // Add 2 different kinds of Armor
        inventory.addProduct(new Armor("Division Agent Vest", "Bulletproof tactical vest with multiple pouches", 175.75, 10, 35));
        inventory.addProduct(new Armor("Power Armor Frame", "Exoskeleton frame that enhances strength and protection", 450.00, 3, 75));
        
        // Add Health item
        inventory.addProduct(new Health("Stimpak", "Military-grade auto-injector that rapidly heals wounds", 45.50, 20, 65));
    }
    
    /**
     * Displays all available products
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
     * Adds a product to the shopping cart
     * @param productName The name of the product to purchase
     * @param quantity The quantity to purchase
     */
    public void purchaseProduct(String productName, int quantity) 
    {
        SalableProduct product = inventory.getProduct(productName);
        
        if (product == null) 
        {
            System.out.println("Product not found.");
            return;
        }
        
        if (product.getQuantity() < quantity) 
        {
            System.out.println("Not enough inventory.");
            return;
        }
        
        cart.addItem(productName, quantity);
        System.out.println(quantity + " x " + productName + " added to cart.");
    }
    
    /**
     * Removes a product from the shopping cart
     * @param productName The name of the product to remove
     */
    public void cancelPurchase(String productName) 
    {
        cart.removeItem(productName);
        System.out.println(productName + " removed from cart.");
    }
    
    /**
     * Displays the contents of the shopping cart
     */
    public void displayCart() 
    {
        if (cart.getItemCount() == 0) 
        {
            System.out.println("Cart is empty.");
            return;
        }
        
        System.out.println("Items in cart:");
        for (SalableProduct product : cart.getItems()) 
        {
            System.out.println(product.getName() + " x " + product.getQuantity() + " - $" + 
                String.format("%.2f", product.getPrice() * product.getQuantity()));
        }
        System.out.println("Total: $" + String.format("%.2f", cart.getTotalPrice()));
    }
    
    /**
     * Processes the checkout
     */
    public void checkout() 
    {
        if (cart.getItemCount() == 0) 
        {
            System.out.println("Cannot checkout with empty cart.");
            return;
        }
        
        System.out.println("Processing payment of $" + String.format("%.2f", cart.getTotalPrice()));
        cart.clearCart();
        System.out.println("Thank you for your purchase!");
    }
    
    /**
     * Gets the inventory manager
     * @return The inventory manager
     */
    public InventoryManager getInventory() 
    {
        return inventory;
    }
    
    /**
     * Gets the shopping cart
     * @return The shopping cart
     */
    public ShoppingCart getCart() 
    {
        return cart;
    }
}
