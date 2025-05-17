/**
 * Mikkos Thomas
 * CST-239 Milestone 6
 * 5/15/2025
 * I used my own work
 */

package com.gamestore.model;

/**
 * Represents a health item that can be sold in the game store.
 * Extends the SalableProduct class with health-specific attributes
 * such as heal amount, effect duration, and potential side effects. 
 * Health items are consumable products that players can use to restore
 * health points during gameplay. Different items offer various healing
 * potencies, effect durations, and may come with side effects.
 */
public class Health extends SalableProduct {
    /** The amount of health points restored by this item */
    private int healAmount;
    
    /** The duration of the healing effect in seconds (0 for instant healing) */
    private int duration;
    
    /** Any side effects of using this health item (e.g., "Mild nausea", "Blurred vision") */
    private String sideEffects;

    /**
     * Default constructor required for JSON deserialization.
     * Creates an empty health item with no initialized values.
     */
    public Health() {
        super();
    }

    /**
     * Constructs a new Health item with basic attributes.
     * This constructor is used when duration and side effects are not specified.
     * The created item will have instant healing with no side effects.
     * @param name The name of the health item
     * @param description The description of the health item
     * @param price The price of the health item in dollars
     * @param quantity The quantity available in inventory
     * @param healAmount The amount of health points restored by this item
     */
    public Health(String name, String description, double price, int quantity, int healAmount) {
        super(name, description, price, quantity);
        this.healAmount = healAmount;
    }
    
    /**
     * Constructs a new Health item with all attributes.
     * This is the most complete constructor that initializes all health item properties.
     * @param name The name of the health item
     * @param description The description of the health item
     * @param price The price of the health item in dollars
     * @param quantity The quantity available in inventory
     * @param healAmount The amount of health points restored by this item
     * @param duration The duration of the effect in seconds (0 for instant healing)
     * @param sideEffects Any side effects of using this item (or "None" if there are no side effects)
     */
    public Health(String name, String description, double price, int quantity, int healAmount, int duration, String sideEffects) {
        super(name, description, price, quantity);
        this.healAmount = healAmount;
        this.duration = duration;
        this.sideEffects = sideEffects;
    }

    /**
     * Gets the heal amount of the health item.
     * This represents how many health points are restored when the item is used.
     * @return The heal amount in health points
     */
    public int getHealAmount() {
        return healAmount;
    }

    /**
     * Sets the heal amount of the health item.
     * @param healAmount The heal amount to set (should be positive)
     */
    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }
    
    /**
     * Gets the duration of the healing effect.
     * A duration of 0 indicates instant healing, while higher values
     * represent healing over time (HoT) effects.
     * @return The duration in seconds
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the healing effect.
     * @param duration The duration to set in seconds (0 or greater)
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    /**
     * Gets the side effects of the health item.
     * Side effects represent any negative consequences of using the item.
     * @return The side effects description, or null if none specified
     */
    public String getSideEffects() {
        return sideEffects;
    }

    /**
     * Sets the side effects of the health item.
     * @param sideEffects The side effects to set, or "None" if there are no side effects
     */
    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    /**
     * Returns a string representation of the health item.
     * Extends the parent class toString() method by adding health-specific attributes.
     * Handles null side effects by displaying "None" instead.
     * @return A formatted string with health item details including heal amount, duration, and side effects
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nHeal Amount: " + healAmount +
                "\nDuration: " + duration + " seconds" +
                "\nSide Effects: " + (sideEffects != null ? sideEffects : "None");
    }
}
