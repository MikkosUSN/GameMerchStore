// Mikkos Thomas
// CST-239
// 04/10/2025
// This is my own work

package com.gamestore.model;

/**
 * Represents the main interface for the game merchandise store.
 */
public class StoreFront {
    private InventoryManager inventory;
    private ShoppingCart cart;
    
    /**
     * Constructs a new StoreFront
     */
    public StoreFront() {
        this.inventory = new InventoryManager();
        this.cart = new ShoppingCart(inventory);
    }
    
    /**
     * Initializes the store with sample products
     */
    public void initialize() {
    	inventory.addProduct(new SalableProduct("Gaming Mouse", "High-precision gaming mouse", 49.99, 20));
        inventory.addProduct(new SalableProduct("Mechanical Keyboard", "RGB mechanical keyboard", 89.99, 15));
        inventory.addProduct(new SalableProduct("Gaming Headset", "Surround sound gaming headset", 79.99, 10));
        inventory.addProduct(new SalableProduct("Gaming Chair", "Ergonomic gaming chair", 199.99, 5));
        inventory.addProduct(new SalableProduct("Gaming Monitor", "144Hz gaming monitor", 299.99, 8));
    }
    
    /**
     * Displays all available products
     */
    public void displayProducts() {
        System.out.println("Available Products:");
        for (SalableProduct product : inventory.getAllProducts()) {
            System.out.println("------------------------");
            System.out.println(product);
        }
    }
    
    /**
     * Adds a product to the shopping cart
     */
    public void purchaseProduct(String productName, int quantity) {
        SalableProduct product = inventory.getProduct(productName);
        
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }
        
        if (product.getQuantity() < quantity) {
            System.out.println("Not enough inventory.");
            return;
        }
        
        cart.addItem(productName, quantity);
        System.out.println(quantity + " x " + productName + " added to cart.");
    }
    
    /**
     * Removes a product from the shopping cart
     */
    public void cancelPurchase(String productName) {
        cart.removeItem(productName);
        System.out.println(productName + " removed from cart.");
    }
    
    /**
     * Displays the contents of the shopping cart
     */
    public void displayCart() {
        if (cart.getItemCount() == 0) {
            System.out.println("Cart is empty.");
            return;
        }
        
        System.out.println("Items in cart:");
        for (SalableProduct product : cart.getItems()) {
            System.out.println(product.getName() + " x " + product.getQuantity() + 
                              " - $" + String.format("%.2f", product.getPrice() * product.getQuantity()));
        }
        System.out.println("Total: $" + String.format("%.2f", cart.getTotalPrice()));
    }
    
    /**
     * Processes the checkout
     */
    public void checkout() {
        if (cart.getItemCount() == 0) {
            System.out.println("Cannot checkout with empty cart.");
            return;
        }
        
        System.out.println("Processing payment of $" + String.format("%.2f", cart.getTotalPrice()));
        cart.clearCart();
        System.out.println("Thank you for your purchase!");
    }
    
    /**
     * Gets the inventory manager
     */
    public InventoryManager getInventory() {
        return inventory;
    }
    
    /**
     * Gets the shopping cart
     */
    public ShoppingCart getCart() {
        return cart;
    }
}
