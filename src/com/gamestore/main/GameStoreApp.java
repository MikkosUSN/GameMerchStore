// Mikkos Thomas
// CST-239
// 04/10/2025
// This is my own work

package com.gamestore.main;

import com.gamestore.model.StoreFront;
import java.util.Scanner;

/**
 * Game Store application to demonstrate the store functionality.
 */
public class GameStoreApp {
    public static void main(String[] args) {
        StoreFront store = new StoreFront();
        store.initialize();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the Game Merchandise Store!");
        boolean running = true;
        
        while (running) {
            System.out.println("\n=== Game Merchandise Store Menu ===");
            System.out.println("1. Browse Products");
            System.out.println("2. Purchase Product");
            System.out.println("3. Cancel Purchase");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
                continue;
            }
            
            switch (choice) {
                case 1:
                    store.displayProducts();
                    break;
                case 2:
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int qty = Integer.parseInt(scanner.nextLine());
                    store.purchaseProduct(name, qty);
                    break;
                case 3:
                    System.out.print("Enter product name to remove: ");
                    String removeName = scanner.nextLine();
                    store.cancelPurchase(removeName);
                    break;
                case 4:
                    store.displayCart();
                    break;
                case 5:
                    store.checkout();
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for visiting the Game Merchandise Store!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        
        scanner.close();
    }
}
