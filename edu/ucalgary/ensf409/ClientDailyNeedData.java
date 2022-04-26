/**
 @author Jannine Osman <a href="mailto:jannine.osman@ucalgary.ca">jannine.osman@ucalgary.ca</a>
         Sukriti Sharma <a href="mailto:sukriti.sharma@ucalgary.ca">sukriti.sharma@ucalgary.ca</a>
         Caroline Basta <a href="mailto:caroline.basta1@ucalgary.ca">caroline.basta1@ucalgary.ca</a>
         Labib Afsar Ahmed <a href="mailto:labibafsar.ahmed@ucalgary.ca">labibafsar.ahmed@ucalgary.ca</a>
 @version       2.3
 @since         1.0
 */

package edu.ucalgary.ensf409;

import java.sql.*;

public class ClientDailyNeedData {

    /**
     * 
     * @param clientId clientId is the ID assigned to each client for retrieval purposes
     * @return dailyNeed  
     * @catches Exception catches an exception if the the SQL file can't be access
     */
    public static DailyNeed getDailyNeed(int clientId) {
        DailyNeed dailyNeed = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_inventory", "student",
                    "ensf");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `DAILY_CLIENT_NEEDS` WHERE ClientID = " + clientId);

            while (rs.next())
                dailyNeed = new DailyNeed(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),
                        rs.getInt(6), rs.getInt(7));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return dailyNeed;
    }

    /**
     * this method calculates the amount of weekly food item every family needs
     * @param familyProfile 
     * the calculations will depend on the composition of each family which is obtained from the FamilyProfile class
     * @return familyProfile after the calculation for the weekly food needs have been done
     */
    public static FamilyProfile calculateWeeklyFamilyNeeds(FamilyProfile familyProfile) {
        WeeklyNutrientProfile nutrientProfile = new WeeklyNutrientProfile(
                calculateWeeklyFamilyWholeGrains(familyProfile),
                calculateWeeklyFamilyFruitVeggies(familyProfile),
                calculateWeeklyFamilyProtein(familyProfile),
                calculateWeeklyFamilyOther(familyProfile),
                calculateWeeklyFamilyTotalCalories(familyProfile));
        familyProfile.setWeeklyNutrientProfile(nutrientProfile);
        return familyProfile;
    }

    /**
     * calculateWeeklyFamilyWholeGrains method multiplies the daily need that every type family member (male, female, children of differnet ages)
     * need by 7 to obtain the weekly whole grain needs per family
     * @param familyProfile linking to the FamilyProfile class to get every type of family member
     * @return whole grain content for the week
     */
    public static long calculateWeeklyFamilyWholeGrains(FamilyProfile familyProfile) {
        long wholeGrains = 0;
        if (familyProfile.getAdultMales() > 0) {
            DailyNeed maleDailyNeed = getDailyNeed(1);   //set adult males to have client ID #1
            wholeGrains = wholeGrains + (maleDailyNeed.getWholeGrain() * 7 * familyProfile.getAdultMales()); //multiply amount of whole grain by 7 for weekly intake for males
        }
        if (familyProfile.getAdultFemales() > 0) {
            DailyNeed femaleDailyNeed = getDailyNeed(2); //set adult females to have client ID #2
            wholeGrains = wholeGrains + (femaleDailyNeed.getWholeGrain() * 7 * familyProfile.getAdultFemales()); //multiply amount of whole grain by 7 for weekly intake for females
        }
        if (familyProfile.getChildrenOver8() > 0) {
            DailyNeed childOverDailyNeed = getDailyNeed(3); //set children over eight to have client ID #3
            wholeGrains = wholeGrains
                    + (childOverDailyNeed.getWholeGrain() * 7 * familyProfile.getChildrenOver8()); //multiply amount of whole grain by 7 for weekly intake for children over 8
        }
        if (familyProfile.getChildrenUnder8() > 0) {
            DailyNeed childUnderDailyNeed = getDailyNeed(4); //set children under eight to have client ID #4
            wholeGrains = wholeGrains 
                    + (childUnderDailyNeed.getWholeGrain() * 7 * familyProfile.getChildrenUnder8()); //multiply amount of whole grain by 7 for weekly intake for children under 8
        }
        return wholeGrains;
    }

    /**
     * calculateWeeklyFamilyFruitVeggies method multiplies the daily need that every type family member (male, female, children of differnet ages)
     * need by 7 to obtain the weekly fruit and vegetable needs per family
     * @param familyProfile linking to the FamilyProfile class to get every type of family member
     * @return fruits and veggies content for the week
     */
    public static long calculateWeeklyFamilyFruitVeggies(FamilyProfile familyProfile) {
        long fruitVeggies = 0;
        if (familyProfile.getAdultMales() > 0) {
            DailyNeed maleDailyNeed = getDailyNeed(1);   //set adult males to have client ID #1
            fruitVeggies = fruitVeggies + (maleDailyNeed.getFruitVeggies() * 7 * familyProfile.getAdultMales());
        }
        if (familyProfile.getAdultFemales() > 0) {
            DailyNeed femaleDailyNeed = getDailyNeed(2);  //set adult females to have client ID #2
            fruitVeggies = fruitVeggies + (femaleDailyNeed.getFruitVeggies() * 7 * familyProfile.getAdultFemales());
        }
        if (familyProfile.getChildrenOver8() > 0) {
            DailyNeed childOverDailyNeed = getDailyNeed(3);  //set children over eight to have client ID #3
            fruitVeggies = fruitVeggies
                    + (childOverDailyNeed.getFruitVeggies() * 7 * familyProfile.getChildrenOver8());
        }
        if (familyProfile.getChildrenUnder8() > 0) {
            DailyNeed childUnderDailyNeed = getDailyNeed(4); //set children under eight to have client ID #4
            fruitVeggies = fruitVeggies
                    + (childUnderDailyNeed.getFruitVeggies() * 7 * familyProfile.getChildrenUnder8());
        }
        return fruitVeggies;
    }

    /**
     * calculateWeeklyFamilyProtein method multiplies the daily need that every type family member (male, female, children of differnet ages)
     * need by 7 to obtain the weekly protein needs per family
     * @param familyProfile linking to the FamilyProfile class to get every type of family member
     * @return protein content for the week
     */
    public static long calculateWeeklyFamilyProtein(FamilyProfile familyProfile) {
        long protein = 0;
        if (familyProfile.getAdultMales() > 0) {
            DailyNeed maleDailyNeed = getDailyNeed(1);  //set adult males to have client ID #1
            protein = protein + (maleDailyNeed.getProtein() * 7 * familyProfile.getAdultMales());
        }
        if (familyProfile.getAdultFemales() > 0) {
            DailyNeed femaleDailyNeed = getDailyNeed(2);  //set adult females to have client ID #2
            protein = protein + (femaleDailyNeed.getProtein() * 7 * familyProfile.getAdultFemales());
        }
        if (familyProfile.getChildrenOver8() > 0) {
            DailyNeed childOverDailyNeed = getDailyNeed(3); //set children over eight to have client ID #3
            protein = protein
                    + (childOverDailyNeed.getProtein() * 7 * familyProfile.getChildrenOver8());
        }
        if (familyProfile.getChildrenUnder8() > 0) {
            DailyNeed childUnderDailyNeed = getDailyNeed(4); //set children under eight to have client ID #4
            protein = protein
                    + (childUnderDailyNeed.getProtein() * 7 * familyProfile.getChildrenUnder8());
        }
        return protein;
    }

    /**
     * calculateWeeklyFamilyOther method multiplies the daily need that every type family member (male, female, children of differnet ages)
     * need by 7 to obtain the weekly other needs per family
     * @param familyProfile linking to the FamilyProfile class to get every type of family member
     * @return other content for the week
     */
    public static long calculateWeeklyFamilyOther(FamilyProfile familyProfile) {
        long other = 0;
        if (familyProfile.getAdultMales() > 0) {
            DailyNeed maleDailyNeed = getDailyNeed(1);  //set adult males to have client ID #1
            other = other + (maleDailyNeed.getOther() * 7 * familyProfile.getAdultMales());
        }
        if (familyProfile.getAdultFemales() > 0) {
            DailyNeed femaleDailyNeed = getDailyNeed(2);  //set adult females to have client ID #2
            other = other + (femaleDailyNeed.getOther() * 7 * familyProfile.getAdultFemales());
        }
        if (familyProfile.getChildrenOver8() > 0) {
            DailyNeed childOverDailyNeed = getDailyNeed(3); //set children over eight to have client ID #3
            other = other
                    + (childOverDailyNeed.getOther() * 7 * familyProfile.getChildrenOver8());
        }
        if (familyProfile.getChildrenUnder8() > 0) {
            DailyNeed childUnderDailyNeed = getDailyNeed(4); //set children under eight to have client ID #4
            other = other
                    + (childUnderDailyNeed.getOther() * 7 * familyProfile.getChildrenUnder8());
        }
        return other;
    }
    
     /**
     * calculateWeeklyFamilyTotalCalories method multiplies the daily need that every type family member (male, female, children of differnet ages)
     * need by 7 to obtain the weekly calorie needs per family
     * @param familyProfile linking to the FamilyProfile class to get every type of family member
     * @return calories content for the week
     */

    public static int calculateWeeklyFamilyTotalCalories(FamilyProfile familyProfile) {
        int totalCalories = 0;
        if (familyProfile.getAdultMales() > 0) {
            DailyNeed maleDailyNeed = getDailyNeed(1);  //set adult males to have client ID #1
            totalCalories = totalCalories + (maleDailyNeed.getCalories() * 7 * familyProfile.getAdultMales());
        }
        if (familyProfile.getAdultFemales() > 0) {
            DailyNeed femaleDailyNeed = getDailyNeed(2);  //set adult females to have client ID #2
            totalCalories = totalCalories + (femaleDailyNeed.getCalories() * 7 * familyProfile.getAdultFemales());
        }
        if (familyProfile.getChildrenOver8() > 0) {
            DailyNeed childOverDailyNeed = getDailyNeed(3); //set children over eight to have client ID #3
            totalCalories = totalCalories
                    + (childOverDailyNeed.getCalories() * 7 * familyProfile.getChildrenOver8());
        }
        if (familyProfile.getChildrenUnder8() > 0) {
            DailyNeed childUnderDailyNeed = getDailyNeed(4); //set children under eight to have client ID #4
            totalCalories = totalCalories
                    + (childUnderDailyNeed.getCalories() * 7 * familyProfile.getChildrenUnder8());
        }
        return totalCalories;
    }

}
