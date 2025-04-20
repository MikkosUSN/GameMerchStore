// Mikkos Thomas
// CST-239
// 04/17/2025
// This is my own work, with help using website: https://halo.gcu.edu/courses/CST-239-O500-20250407/announcements

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
    
    @Override
    public String toString() 
    {
        return super.toString() + "\nHeal Amount: " + healAmount;
    }
}
