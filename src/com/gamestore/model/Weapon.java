// Mikkos Thomas
// CST-239
// 04/17/2025
// This is my own work, with help using website: https://halo.gcu.edu/courses/CST-239-O500-20250407/announcements

package com.gamestore.model;

/**
 * Represents a weapon that can be sold in the game store.
 * Extends the SalableProduct class with weapon-specific attributes.
 */
public class Weapon extends SalableProduct 
{
    /** The damage value of the weapon */
    private int damage;
    
    /**
     * Default constructor
     */
    public Weapon() 
    {
        super();
    }
    
    /**
     * Constructs a new Weapon with specified attributes 
     * @param name The name of the weapon
     * @param description The description of the weapon
     * @param price The price of the weapon
     * @param quantity The quantity available
     * @param damage The damage value of the weapon
     */
    public Weapon(String name, String description, double price, int quantity, int damage) 
    {
        super(name, description, price, quantity);
        this.damage = damage;
    }
    
    /**
     * Gets the damage value of the weapon
     * @return The damage value
     */
    public int getDamage() 
    {
        return damage;
    }
    
    /**
     * Sets the damage value of the weapon
     * @param damage The damage value to set
     */
    public void setDamage(int damage) 
    {
        this.damage = damage;
    }
    
    @Override
    public String toString() 
    {
        return super.toString() + "\nDamage: " + damage;
    }
}
