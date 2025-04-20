// Mikkos Thomas
// CST-239
// 04/17/2025
// This is my own work, with help using website: https://halo.gcu.edu/courses/CST-239-O500-20250407/announcements

package com.gamestore.model;

/**
 * Represents armor that can be sold in the game store.
 * Extends the SalableProduct class with armor-specific attributes.
 */
public class Armor extends SalableProduct 
{
    /** The defense value of the armor */
    private int defense;
    
    /**
     * Default constructor
     */
    public Armor() 
    {
        super();
    }
    
    /**
     * Constructs a new Armor with specified attributes
     * @param name The name of the armor
     * @param description The description of the armor
     * @param price The price of the armor
     * @param quantity The quantity available
     * @param defense The defense value of the armor
     */
    public Armor(String name, String description, double price, int quantity, int defense) 
    {
        super(name, description, price, quantity);
        this.defense = defense;
    }
    
    /**
     * Gets the defense value of the armor
     * @return The defense value
     */
    public int getDefense() 
    {
        return defense;
    }
    
    /**
     * Sets the defense value of the armor
     * @param defense The defense value to set
     */
    public void setDefense(int defense) 
    {
        this.defense = defense;
    }
    
    @Override
    public String toString() 
    {
        return super.toString() + "\nDefense: " + defense;
    }
}
