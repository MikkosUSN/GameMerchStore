/**
 * Mikkos Thomas
 * CST-239 Milestone 6
 * 5/15/2025
 * I used my own work
 */

package com.gamestore.model;

/**
 * Represents a weapon that can be sold in the game store.
 * Extends the SalableProduct class with weapon-specific attributes
 * such as damage, rate of fire, and effective range.
 * Weapons are specialized items that players can use for combat
 * in the game world.
 */
public class Weapon extends SalableProduct {
    /** The damage value of the weapon (higher values indicate more powerful weapons) */
    private int damage;
    
    /** The rate of fire of the weapon (rounds per minute) */
    private int rateOfFire;
    
    /** The effective range of the weapon in meters */
    private int range;

    /**
     * Default constructor required for JSON deserialization.
     * Creates an empty weapon with no initialized values.
     */
    public Weapon() {
        super();
    }

    /**
     * Constructs a new Weapon with basic attributes.
     * This constructor is used when rate of fire and range are not specified.
     * @param name The name of the weapon
     * @param description The description of the weapon
     * @param price The price of the weapon in dollars
     * @param quantity The quantity available in inventory
     * @param damage The damage value of the weapon
     */
    public Weapon(String name, String description, double price, int quantity, int damage) {
        super(name, description, price, quantity);
        this.damage = damage;
    }
    
    /**
     * Constructs a new Weapon with all attributes.
     * This is the most complete constructor that initializes all weapon properties.
     * @param name The name of the weapon
     * @param description The description of the weapon
     * @param price The price of the weapon in dollars
     * @param quantity The quantity available in inventory
     * @param damage The damage value of the weapon
     * @param rateOfFire The rate of fire (rounds per minute)
     * @param range The effective range in meters
     */
    public Weapon(String name, String description, double price, int quantity,
                  int damage, int rateOfFire, int range) {
        super(name, description, price, quantity);
        this.damage = damage;
        this.rateOfFire = rateOfFire;
        this.range = range;
    }

    /**
     * Gets the damage value of the weapon.
     * Higher damage values indicate more powerful weapons.
     * @return The damage value
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets the damage value of the weapon.
     * @param damage The damage value to set (should be positive)
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }
    
    /**
     * Gets the rate of fire of the weapon.
     * This represents how quickly the weapon can be fired.
     * @return The rate of fire in rounds per minute
     */
    public int getRateOfFire() {
        return rateOfFire;
    }

    /**
     * Sets the rate of fire of the weapon.
     * @param rateOfFire The rate of fire to set in rounds per minute
     */
    public void setRateOfFire(int rateOfFire) {
        this.rateOfFire = rateOfFire;
    }
    
    /**
     * Gets the effective range of the weapon.
     * This represents the maximum distance at which the weapon remains effective.
     * @return The range in meters
     */
    public int getRange() {
        return range;
    }

    /**
     * Sets the effective range of the weapon.
     * @param range The range to set in meters
     */
    public void setRange(int range) {
        this.range = range;
    }

    /**
     * Returns a string representation of the weapon.
     * Extends the parent class toString() method by adding weapon-specific attributes.
     * @return A formatted string with weapon details including damage, rate of fire, and range
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nDamage: " + damage +
                "\nRate of Fire: " + rateOfFire + " RPM" +
                "\nRange: " + range + " meters";
    }
}
