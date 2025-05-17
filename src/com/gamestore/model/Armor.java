/**
 * Mikkos Thomas
 * CST-239 Milestone 6
 * 5/15/2025
 * I used my own work
 */

package com.gamestore.model;

/**
 * Represents armor that can be sold in the game store.
 * Extends the SalableProduct class with armor-specific attributes
 * such as defense, mobility, and durability ratings. 
 * Armor items provide protection to players in the game world,
 * with different pieces offering various tradeoffs between
 * protection, movement speed, and longevity.
 */
public class Armor extends SalableProduct {
    /** The defense value of the armor (higher values provide better protection) */
    private int defense;
    
    /** The mobility rating of the armor (0-100, higher values allow faster movement) */
    private int mobility;
    
    /** The durability rating of the armor (0-100, higher values indicate longer-lasting armor) */
    private int durability;

    /**
     * Default constructor required for JSON deserialization.
     * Creates an empty armor with no initialized values.
     */
    public Armor() {
        super();
    }

    /**
     * Constructs a new Armor with basic attributes.
     * This constructor is used when mobility and durability are not specified.
     * @param name The name of the armor
     * @param description The description of the armor
     * @param price The price of the armor in dollars
     * @param quantity The quantity available in inventory
     * @param defense The defense value of the armor
     */
    public Armor(String name, String description, double price, int quantity, int defense) {
        super(name, description, price, quantity);
        this.defense = defense;
    }
    
    /**
     * Constructs a new Armor with all attributes.
     * This is the most complete constructor that initializes all armor properties.
     * @param name The name of the armor
     * @param description The description of the armor
     * @param price The price of the armor in dollars
     * @param quantity The quantity available in inventory
     * @param defense The defense value of the armor
     * @param mobility The mobility rating (0-100, higher is better)
     * @param durability The durability rating (0-100, higher is better)
     */
    public Armor(String name, String description, double price, int quantity,
                 int defense, int mobility, int durability) {
        super(name, description, price, quantity);
        this.defense = defense;
        this.mobility = mobility;
        this.durability = durability;
    }

    /**
     * Gets the defense value of the armor.
     * Higher defense values provide better protection against damage.
     * @return The defense value
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Sets the defense value of the armor.
     * @param defense The defense value to set (should be positive)
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }
    
    /**
     * Gets the mobility rating of the armor.
     * This represents how much the armor affects movement speed.
     * Higher values (0-100) indicate better mobility and less movement restriction.
     * @return The mobility rating (0-100)
     */
    public int getMobility() {
        return mobility;
    }

    /**
     * Sets the mobility rating of the armor.
     * @param mobility The mobility rating to set (0-100)
     */
    public void setMobility(int mobility) {
        this.mobility = mobility;
    }
    
    /**
     * Gets the durability rating of the armor.
     * This represents how well the armor withstands damage before breaking.
     * Higher values (0-100) indicate more durable armor that lasts longer.
     * @return The durability rating (0-100)
     */
    public int getDurability() {
        return durability;
    }

    /**
     * Sets the durability rating of the armor.
     * @param durability The durability rating to set (0-100)
     */
    public void setDurability(int durability) {
        this.durability = durability;
    }

    /**
     * Returns a string representation of the armor.
     * Extends the parent class toString() method by adding armor-specific attributes.
     * @return A formatted string with armor details including defense, mobility, and durability
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nDefense: " + defense +
                "\nMobility: " + mobility +
                "\nDurability: " + durability;
    }
}

