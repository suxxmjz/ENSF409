/**
 @author Jannine Osman <a href="mailto:jannine.osman@ucalgary.ca">jannine.osman@ucalgary.ca</a>
         Sukriti Sharma <a href="mailto:sukriti.sharma@ucalgary.ca">sukriti.sharma@ucalgary.ca</a>
         Caroline Basta <a href="mailto:caroline.basta1@ucalgary.ca">caroline.basta1@ucalgary.ca</a>
         Labib Afsar Ahmed <a href="mailto:labibafsar.ahmed@ucalgary.ca">labibafsar.ahmed@ucalgary.ca</a>
 @version       1.1
 @since         1.0
 */
package edu.ucalgary.ensf409;

public class FoodItem {
    private int id;
    private String name;
    private int grainContent;
    private int fvContent;
    private int proContent;
    private int other;
    private int calories;

    /**
     * Constructor for FoodItem, this will initialize the required variables for this method
     * @param id id number of the food type
     * @param name type of food (i.e. bread, tomato, etc.)
     * @param grainContent how much grains per food item
     * @param fvContent how much fruits and vegetables
     * @param proContent how much protein per food item
     * @param other how much other types of food
     * @param calories how much calories per food item
     */

    public FoodItem(int id, String name, int grainContent, int fvContent, int proContent, int other, int calories) {
        this.id = id;
        this.name = name;
        this.grainContent = grainContent;
        this.fvContent = fvContent;
        this.proContent = proContent;
        this.other = other;
        this.calories = calories;

    }

    /**
     * getter method for food id
     * @return integer value of food id
     */
    public int getId() {
        return id;
    }

    /**
     * getter method for food name
     * @return String value of food name
     */
    public String getName() {
        return name;
    }

    /**
     * getter method for grain content in food
     * @return integer value of grain content in food
     */
    public int getGrainContent() {
        return grainContent;
    }

     /**
     * getter method for fruits and vegetables content in food
     * @return integer value of fruits and vegetables content in food
     */
    public int getFVContent() {
        return fvContent;
    }

     /**
     * getter method for protein content in food
     * @return integer value of protein content in food
     */
    public int getProContent() {
        return proContent;
    }

     /**
     * getter method for other content in food
     * @return integer value of other content in food
     */
    public int getOther() {
        return other;
    }

     /**
     * getter method for calorie content in food
     * @return integer value of calorie content in food
     */
    public int getCalories() {
        return calories;
    }

     /**
     * toString method to formalize the food item values in a string
     * @return string value of food id plus name of food item
     */

    public String toString() {
        return id + " " + name;
    }
}
