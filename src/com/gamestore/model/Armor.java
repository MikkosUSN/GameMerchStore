// Mikkos Thomas
// CST-239 Milestone 4
// 04/30/2025
// I used my own work

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
    
    /**
     * Returns a string representation of the armor.
     * @return A formatted string with armor details
     */
    @Override
    public String toString() 
    {
        return super.toString() + "\nDefense: " + defense;
    }
}
