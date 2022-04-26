/**
 @author Jannine Osman <a href="mailto:jannine.osman@ucalgary.ca">jannine.osman@ucalgary.ca</a>
         Sukriti Sharma <a href="mailto:sukriti.sharma@ucalgary.ca">sukriti.sharma@ucalgary.ca</a>
         Caroline Basta <a href="mailto:caroline.basta1@ucalgary.ca">caroline.basta1@ucalgary.ca</a>
         Labib Afsar Ahmed <a href="mailto:labibafsar.ahmed@ucalgary.ca">labibafsar.ahmed@ucalgary.ca</a>
 @version       1.0
 @since         1.0
 */

package edu.ucalgary.ensf409;

import java.util.List;

public class Hamper {
    private List<FoodItem> foodItems;   //creates a list for food items to be added to

    /**
     * creating a List of food items, calling it from FoodItem class
     * @param foodItems variable is used as a setter
     */
    public Hamper(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    /**
     * getter method for food items in a list
     * @return foodItems in a list
     */
    public List<FoodItem> getFoodItems() {
        return foodItems;
    }
    
    /**
     * toString method to create a new string builder to store hamper items in
     * @return sb in string format
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (FoodItem item : foodItems) {
            sb.append(item).append("\n");
        }
        return sb.toString();
    }
}
