// Mikkos Thomas
// CST-239 Milestone 4
// 04/30/2025
// I used my own work

package com.gamestore.model;

/**
 * Represents a health item that can be sold in the game store.
 * Extends the SalableProduct class with health-specific attributes.
 */
public class Health extends SalableProduct 
{
    /** The amount of health restored by this item */
    private int healAmount;
    
    /**
     * Default constructor
     */
    public Health() 
    {
        super();
    }
    
    /**
     * Constructs a new Health item with specified attributes
     * @param name The name of the health item
     * @param description The description of the health item
     * @param price The price of the health item
     * @param quantity The quantity available
     * @param healAmount The amount of health restored by this item
     */
    public Health(String name, String description, double price, int quantity, int healAmount) 
    {
        super(name, description, price, quantity);
        this.healAmount = healAmount;
    }
    
    /**
     * Gets the heal amount of the health item
     * @return The heal amount
     */
    public int getHealAmount() 
    {
        return healAmount;
    }
    
    /**
     * Sets the heal amount of the health item
     * @param healAmount The heal amount to set
     */
    public void setHealAmount(int healAmount) 
    {
        this.healAmount = healAmount;
    }
    
    /**
     * Returns a string representation of the health item.
     * @return A formatted string with health item details
     */
    @Override
    public String toString() 
    {
        return super.toString() + "\nHeal Amount: " + healAmount;
    }
}
