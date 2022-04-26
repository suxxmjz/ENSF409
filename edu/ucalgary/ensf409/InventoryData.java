/**
 @author Jannine Osman <a href="mailto:jannine.osman@ucalgary.ca">jannine.osman@ucalgary.ca</a>
           Sukriti Sharma <a href="mailto:sukriti.sharma@ucalgary.ca">sukriti.sharma@ucalgary.ca</a>
           Caroline Basta <a href="mailto:caroline.basta1@ucalgary.ca">caroline.basta1@ucalgary.ca</a>
           Labib Afsar Ahmed <a href="mailto:labibafsar.ahmed@ucalgary.ca">labibafsar.ahmed@ucalgary.ca</a>
 @version       1.8
 @since         1.0
 */
package edu.ucalgary.ensf409;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryData {
    /**
     * getFoodItems method to retireve all the available food from the database
     * @catches Exception if list of food items can't be obtained
     * @return items - i.e. list of food items available in the inventory
     */
    public static List<FoodItem> getFoodItems() {
        List<FoodItem> items = new ArrayList<>();        //creates an new array list from FoodItem class
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_inventory", "student",
                    "ensf");   //ensure proper connection to the SQL database 
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `AVAILABLE_FOOD`"); //executeQuery method used to find the table with food list

            while (rs.next())
                items.add(new FoodItem(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
                        rs.getInt(6), rs.getInt(7)));  //retrieve the 7 columns in the SQL files containing item ID and food information
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return items;
    }

    /**
     * method to delete item from the inventory once it has been added to the hamper
     * needs connection to the sql database in order to update the inventory
     * this is done by creating a try-catch method to ensure correct access to the inventory
     * @param itemId the item ID of the food item to be deleted from the database 
     * @throws Exception if food item failed to be deleted and was used again in another hamper
     */
    public static void deleteFoodItem(int itemId) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_inventory", "student",
                    "ensf");

            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM `AVAILABLE_FOOD` WHERE ItemID = " + itemId); //executeUpdate where AVAILABLE_FOOD list gets updated 
                                                                                         //if itemID is equal to itemID found in hamper
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * 
     * @param column - column of items from SQL table
     * @catches Exception if sum wasn't able to be calculated correctly
     * @return total - total number of items added to the hamper 
     */
    private static long getTotal(String column) {
        long total = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_inventory", "student",
                    "ensf");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SUM(" + column + ") FROM `AVAILABLE_FOOD`");

            while (rs.next())
                total = rs.getLong(1);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return total;
    }

    /**
     * getter method for total grain
     * @return grain content column from the table
     */
    public static long getTotalGrain() {
        return getTotal("GrainContent");
    }

    /**
     * getter method for total fruits and vegetables
     * @return fruits and vegetables content column from the table
     */
    public static long getTotalFVContent() {
        return getTotal("FVContent");
    }

    /**
     * getter method for total protien
     * @return protein content column from the table
     */
    public static long getTotalProContent() {
        return getTotal("ProContent");
    }

    /**
     * getter method for total other items
     * @return other content column from the table
     */
    public static long getTotalOther() {
        return getTotal("Other");
    }

     /**
     * getter method for total calories
     * @return calories content column from the table
     */
    public static long getTotalCalories() {
        return getTotal("Calories");
    }
}
